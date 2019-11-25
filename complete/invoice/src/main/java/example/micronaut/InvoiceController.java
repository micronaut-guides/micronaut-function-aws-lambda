package example.micronaut;

import io.micronaut.context.annotation.Value;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.reactivex.Single;

import javax.validation.Valid;
import java.math.BigDecimal;

@Controller("/invoice") // <1>
public class InvoiceController {

    private final BigDecimal vatPercentage;

    private final VatValidator vatValidator;

    private final int scale;

    public InvoiceController(@Value("${vat.percentage}") BigDecimal vatPercentage, // <2>
                             @Value("${vat.scale}") int scale,
                             VatValidator vatValidator) { // <3>
        this.vatPercentage = vatPercentage;
        this.scale = scale;
        this.vatValidator = vatValidator;
    }

    @Post("/vat") // <4>
    Single<Taxes> calculateVat(@Valid @Body Invoice invoice) {  // <5>
        return vatValidator.validateVat(new VatValidationRequest(invoice.getCountryCode(), invoice.getVatNumber())) // <6>
                .map(vatValidation -> {
                        BigDecimal percentage = vatValidation.isValid() ? vatPercentage : new BigDecimal("0");
                    return new Taxes(invoice.getLines().stream()
                            .map(line -> line.vatPrice(percentage))
                            .reduce(BigDecimal.ZERO, BigDecimal::add)
                            .setScale(scale, BigDecimal.ROUND_HALF_EVEN));
                        });
    }
}
