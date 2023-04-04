package fr.polytech.controllers.dto;


import javax.validation.constraints.NotNull;

public class DiscountDTO extends ProductDTO {

    @NotNull(message = "pointPrice should not be null")
    private int pointPrice;

    public DiscountDTO(Long id, String name, Long storeId, double cashPrice, int pointPrice) {
        super(id, name, storeId, cashPrice);
        this.pointPrice = pointPrice;
    }

    public int getPointPrice() {
        return pointPrice;
    }

    public void setPointPrice(int pointPrice) {
        this.pointPrice = pointPrice;
    }

    @Override
    public String toString() {
        return "DiscountDTO{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", storeId=" + getStoreId() +
                ", cashPrice=" + getCashPrice() +
                ", pointPrice=" + pointPrice +
                '}';
    }
}
