package example.micronaut;

import io.micronaut.function.client.FunctionClient;
import io.micronaut.http.annotation.Body;
import io.micronaut.retry.annotation.Retryable;
import io.reactivex.Single;

import javax.inject.Named;

@FunctionClient // <1>
public interface VatClient extends VatValidator {

    @Override
    @Named("vies-vat-validator") // <2>
    @Retryable(attempts = "${vat.retry.attempts:3}", delay = "${vat.retry.delay:1s}") // <3>
    Single<VatValidation> validateVat(@Body VatValidationRequest req);
}
