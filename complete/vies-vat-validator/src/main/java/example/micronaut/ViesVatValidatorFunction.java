package example.micronaut;

import io.micronaut.function.FunctionBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.function.Function;

@FunctionBean("vies-vat-validator")
public class ViesVatValidatorFunction implements Function<VatValidationRequest, VatValidation> {
    private static final Logger LOG = LoggerFactory.getLogger(ViesVatValidatorFunction.class);

    private final VatService vatService;

    public ViesVatValidatorFunction(VatService vatService) {
        this.vatService = vatService;
    }

    @Override
    public VatValidation apply(VatValidationRequest request) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("validate country: {} vat number: {}", request.getMemberStateCode(), request.getVatNumberCode());
        }
        Boolean result = false;
        try {
            result = vatService.validateVat(request.getMemberStateCode(), request.getVatNumberCode()).get();
        } catch (InterruptedException e) {

        } catch (ExecutionException e) {

        }
        return new VatValidation(request.getMemberStateCode(), request.getVatNumberCode(), result);
    }
}
