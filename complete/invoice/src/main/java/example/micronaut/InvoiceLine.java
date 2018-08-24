package example.micronaut;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Objects;

public class InvoiceLine {
    @NotNull
    @NotBlank
    private String productId;

    @Positive
    private Integer count;

    @NotNull
    @Positive
    private BigDecimal price;

    public InvoiceLine() {}

    public InvoiceLine(String productId, Integer count, BigDecimal price) {
        this.productId = productId;
        this.count = count;
        this.price = price;
    }

    public BigDecimal vatPrice(BigDecimal vatPercentage) {
        return getPrice().multiply(BigDecimal.valueOf(getCount()).multiply(vatPercentage));
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceLine that = (InvoiceLine) o;
        return Objects.equals(productId, that.productId) &&
                Objects.equals(count, that.count) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, count, price);
    }
}
