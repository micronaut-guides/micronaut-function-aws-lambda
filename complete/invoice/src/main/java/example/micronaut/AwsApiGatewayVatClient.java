package example.micronaut;

import io.micronaut.context.annotation.Primary;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.Client;
import io.reactivex.Single;

@Primary // <1>
@Client("${awsapigateway.vatinvokeurl}") // <2>
@Requires(notEnv = Environment.TEST) // <3>
public interface AwsApiGatewayVatClient extends VatValidator {

    @Override
    @Post("/${awsapigateway.stage}") // <4>
    Single<VatValidation> validateVat(@Body VatValidationRequest req);
}
