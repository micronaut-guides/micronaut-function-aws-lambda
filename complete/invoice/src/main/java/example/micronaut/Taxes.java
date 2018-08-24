package example.micronaut;

import java.math.BigDecimal;

public class Taxes {
    BigDecimal vat;

    public Taxes() {}

    public Taxes(BigDecimal vat) {
        this.vat = vat;
    }

    public BigDecimal getVat() {
        return vat;
    }

    public void setVat(BigDecimal vat) {
        this.vat = vat;
    }
}
