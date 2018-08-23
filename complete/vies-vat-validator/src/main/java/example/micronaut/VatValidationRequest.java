package example.micronaut;

import java.util.Objects;

public class VatValidationRequest {
    private String memberStateCode;
    private String vatNumberCode;
    private Boolean result;

    public VatValidationRequest() {
    }

    public VatValidationRequest(String memberStateCode, String vatNumberCode) {
        this.memberStateCode = memberStateCode;
        this.vatNumberCode = vatNumberCode;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VatValidationRequest that = (VatValidationRequest) o;
        return Objects.equals(memberStateCode, that.memberStateCode) &&
                Objects.equals(vatNumberCode, that.vatNumberCode) &&
                Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberStateCode, vatNumberCode, result);
    }
}
