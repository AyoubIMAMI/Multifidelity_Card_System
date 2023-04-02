package fr.polytech.controllers.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class DiscountDTO {
    private Long id;

    @NotBlank(message = "name should not be blank")
    private String name;

    @NotNull(message = "storeId should not be null")
    private Long storeId;

    @NotNull(message = "cashPrice should not be null")
    private double cashPrice;

    @NotNull(message = "pointPrice should not be null")
    private int pointPrice;

    public DiscountDTO(Long id, String name, Long storeId, double cashPrice, int pointPrice) {
        this.id = id;
        this.name = name;
        this.storeId = storeId;
        this.cashPrice = cashPrice;
        this.pointPrice = pointPrice;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getStoreId() {
        return storeId;
    }

    public double getCashPrice() {
        return cashPrice;
    }

    public int getPointPrice() {
        return pointPrice;
    }

    @Override
    public String toString() {
        return "DiscountDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", storeId=" + storeId +
                ", cashPrice=" + cashPrice +
                ", pointPrice=" + pointPrice +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public void setCashPrice(double cashPrice) {
        this.cashPrice = cashPrice;
    }

    public void setPointPrice(int pointPrice) {
        this.pointPrice = pointPrice;
    }
}
