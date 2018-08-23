package example.micronaut;

import io.micronaut.function.client.FunctionClient;
import io.reactivex.Single;

import javax.inject.Named;

@FunctionClient
public interface ViesVatValidatorClient {

    @Named("vies-vat-validator")
    VatValidation apply(VatValidationRequest request);

}
