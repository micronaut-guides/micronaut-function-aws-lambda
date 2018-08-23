package example.micronaut;

import java.io.Serializable;
import java.util.Objects;

public class VatValidation implements Serializable {
    private String memberStateCode;
    private String vatNumberCode;
    private Boolean result;

    public VatValidation() {

    }

    public VatValidation(String memberStateCode, String vatNumberCode, Boolean result) {
        this.memberStateCode = memberStateCode;
        this.vatNumberCode = vatNumberCode;
        this.result = result;
    }

    public String getMemberStateCode() {
        return memberStateCode;
    }

    public void setMemberStateCode(String memberStateCode) {
        this.memberStateCode = memberStateCode;
    }

    public String getVatNumberCode() {
        return vatNumberCode;
    }

    public void setVatNumberCode(String vatNumberCode) {
        this.vatNumberCode = vatNumberCode;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VatValidation that = (VatValidation) o;
        return Objects.equals(memberStateCode, that.memberStateCode) &&
                Objects.equals(vatNumberCode, that.vatNumberCode) &&
                Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberStateCode, vatNumberCode, result);
    }
}
