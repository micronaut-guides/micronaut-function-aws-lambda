package example.micronaut;

import io.micronaut.context.annotation.Primary;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.retry.annotation.Retryable;
import io.reactivex.Single;

@Primary // <1>
@Client("viesvatvalidator") // <2>
@Requires(notEnv = Environment.TEST) // <3>
public interface AwsApiGatewayVatClient extends VatValidator {

    @Override
    @Retryable(attempts = "${vat.retry.attempts:3}", delay = "${vat.retry.delay:1s}")
    @Post("/${micronaut.http.services.viesvatvalidator.stage}") // <4>
    Single<VatValidation> validateVat(@Body VatValidationRequest req);
}
