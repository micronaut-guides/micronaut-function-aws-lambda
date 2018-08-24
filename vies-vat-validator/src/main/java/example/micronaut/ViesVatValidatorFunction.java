package example.micronaut;

import io.micronaut.function.FunctionBean;
import java.util.function.Supplier;

@FunctionBean("vies-vat-validator")
public class ViesVatValidatorFunction implements Supplier<String> {

    @Override
    public String get() {
        return "vies-vat-validator";
    }
}
