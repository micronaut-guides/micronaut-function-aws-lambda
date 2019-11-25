package example.micronaut;

import io.micronaut.core.annotation.Introspected;

import java.io.Serializable;

@Introspected
public class VatValidation extends VatValidationRequest implements Serializable {
    private Boolean valid;

    public VatValidation() {

    }

    public VatValidation(String memberStateCode, String vatNumber, Boolean valid) {
        super(memberStateCode, vatNumber);
        this.valid = valid;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public Boolean isValid() {
        return valid;
    }
}
