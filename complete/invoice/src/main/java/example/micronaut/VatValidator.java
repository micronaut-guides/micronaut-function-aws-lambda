package example.micronaut;

import io.reactivex.Single;

public interface VatValidator {

    Single<VatValidation> validateVat(String memberStateCode, String vatNumber);
}
