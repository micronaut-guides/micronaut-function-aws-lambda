package example.micronaut;

import io.micronaut.context.annotation.Primary;
import io.micronaut.context.annotation.Replaces;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import io.reactivex.Single;

import javax.inject.Singleton;
import java.util.Collections;
import java.util.List;

@Primary // remove when solved https://github.com/micronaut-projects/micronaut-core/issues/524
@Replaces(VatClient.class)
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
