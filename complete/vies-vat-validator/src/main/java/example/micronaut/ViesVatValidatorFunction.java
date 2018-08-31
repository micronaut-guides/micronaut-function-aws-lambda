package example.micronaut;

import io.micronaut.function.FunctionBean;
import io.reactivex.Flowable;
import io.reactivex.Single;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.function.Function;

@FunctionBean("vies-vat-validator") // <1>
public class ViesVatValidatorFunction
        implements Function<VatValidationRequest, VatValidation> { // <2>
    private static final Logger LOG = LoggerFactory.getLogger(ViesVatValidatorFunction.class); // <3>

    private final VatService vatService;

    public ViesVatValidatorFunction(VatService vatService) { // <4>
        this.vatService = vatService;
    }

    @Override
    public VatValidation apply(VatValidationRequest request) {
        final String memberStateCode = request.getMemberStateCode();
        final String vatNumber = request.getVatNumber();
        if (LOG.isDebugEnabled()) {
            LOG.debug("validate country: {} vat number: {}", memberStateCode, vatNumber);
        }
        return vatService.validateVat(memberStateCode, vatNumber)
                    .map(valid -> new VatValidation(memberStateCode, vatNumber, valid))
                    .blockingGet();
    }
}
