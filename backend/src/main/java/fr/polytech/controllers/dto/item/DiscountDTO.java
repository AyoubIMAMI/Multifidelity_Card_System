package fr.polytech.controllers.dto.item;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class DiscountDTO extends BuyableDTO {

    @NotNull(message = "point price should not be null")
    private int pointPrice;

    public DiscountDTO(String name, Long storeId, int pointPrice) {
        super(name, storeId);
        this.pointPrice = pointPrice;
    }

    public DiscountDTO() {

    }


    public int getPointPrice() {
        return pointPrice;
    }

    public void setPointPrice(int pointPrice) {
        this.pointPrice = pointPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DiscountDTO discountDTO = (DiscountDTO) o;
        return pointPrice == discountDTO.pointPrice;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), pointPrice);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CliDiscount{");
        if (getId() != null) {
            sb.append("id=").append(getId());
            sb.append(", ");
        }
        sb.append("name='").append(getName()).append('\'');
        sb.append(", storeId=").append(getStoreId());
        sb.append(", pointPrice=").append(pointPrice);
        sb.append('}');
        return sb.toString();
    }
}
