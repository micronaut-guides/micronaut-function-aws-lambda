package example.micronaut;

import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Introspected
public class Invoice {

    @NotNull
    @NotBlank
    private String vatNumber;

    @NotNull
    @NotBlank
    private String countryCode;

    @NotEmpty
    private List<InvoiceLine> lines;

    public Invoice() {
    }

    public Invoice(@NotNull @NotBlank String vatNumber,
                   @NotNull @NotBlank String countryCode,
                   @NotEmpty List<InvoiceLine> lines) {
        this.vatNumber = vatNumber;
        this.countryCode = countryCode;
        this.lines = lines;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public List<InvoiceLine> getLines() {
        return lines;
    }

    public void setLines(List<InvoiceLine> lines) {
        this.lines = lines;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return Objects.equals(vatNumber, invoice.vatNumber) &&
                Objects.equals(countryCode, invoice.countryCode) &&
                Objects.equals(lines, invoice.lines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vatNumber, countryCode, lines);
    }
}
