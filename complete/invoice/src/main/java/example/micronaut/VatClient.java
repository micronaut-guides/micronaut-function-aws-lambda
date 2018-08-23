package example.micronaut;
import io.micronaut.function.client.FunctionClient;
import io.reactivex.Single;

import javax.inject.Named;

@FunctionClient
public interface VatClient extends VatValidator {

    @Override
    @Named("vies-vat-validator")
    Single<VatValidation> validateVat(String memberStateCode, String vatNumberCode);
}
