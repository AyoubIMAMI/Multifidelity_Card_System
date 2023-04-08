package fr.polytech.controllers.dto.item;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class ProductDTO extends BuyableDTO {

    @NotNull(message = "point price should not be null")
    private double cashPrice;

    public ProductDTO(String name, Long storeId, double cashPrice) {
        super(name, storeId);
        this.cashPrice = cashPrice;
    }

    public ProductDTO() {

    }

    public double getCashPrice() {
        return cashPrice;
    }

    public void setCashPrice(double cashPrice) {
        this.cashPrice = cashPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ProductDTO productDTO = (ProductDTO) o;
        return Double.compare(productDTO.cashPrice, cashPrice) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cashPrice);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CliProduct{");
        if (getId() != null) {
            sb.append("id=").append(getId());
            sb.append(", ");
        }
        sb.append("name='").append(getName()).append('\'');
        sb.append(", storeId=").append(getStoreId());
        sb.append(", cashPrice=").append(cashPrice);
        sb.append('}');
        return sb.toString();
    }
}
