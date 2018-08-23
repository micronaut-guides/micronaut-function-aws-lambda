package example.micronaut;

import io.micronaut.context.annotation.Value;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;
import io.reactivex.Single;

import javax.validation.Valid;
import java.math.BigDecimal;

@Validated
@Controller("/invoice")
public class InvoiceController {

    private final BigDecimal vatPercentage;

    private final VatValidator vatValidator;

    public InvoiceController(@Value("${vat}") BigDecimal vatPercentage,
                             VatValidator vatValidator) {
        this.vatPercentage = vatPercentage;
        this.vatValidator = vatValidator;
    }

    @Post("/vat")
    Single<BigDecimal> calculateVat(@Valid @Body Invoice invoice) {
        return vatValidator.validateVat(invoice.getCountryCode(), invoice.getVatNumber())
                .map(vatValidation -> {
                        BigDecimal percentage = vatValidation.getResult() ? vatPercentage : new BigDecimal("0");
                    return invoice.getLines().stream()
                            .map(line -> line.getPrice().multiply(BigDecimal.valueOf(line.getCount()).multiply(percentage)))
                            .reduce(BigDecimal.ZERO, BigDecimal::add);
                        });
    }
}
