package example.micronaut;

import io.micronaut.function.client.FunctionClient;
import io.micronaut.http.annotation.Body;
import io.reactivex.Single;

import javax.inject.Named;

@FunctionClient
public interface ViesVatValidatorClient {

    @Named("vies-vat-validator")
    Single<VatValidation> apply(@Body VatValidationRequest request);

}
