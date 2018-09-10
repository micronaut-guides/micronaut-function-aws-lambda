package example.micronaut;

import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import io.reactivex.Single;

import javax.inject.Singleton;
import java.util.Collections;
import java.util.List;

@Requires(env = Environment.TEST)
@Singleton
public class VatValidatorMock implements VatValidator {

    @Override
    public Single<VatValidation> validateVat(VatValidationRequest request) {
        String memberStateCode = request.getMemberStateCode();
        String vatNumber = request.getVatNumber();
        List<String> validVatNumbers = Collections.singletonList("B84965375");
        return Single.just(new VatValidation(memberStateCode,
                vatNumber,
                validVatNumbers.contains(vatNumber)));
    }
}
