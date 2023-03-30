package fr.univcotedazur.simpletcfs.cli.model;

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

    public DiscountDTO(String name, Long storeId, double cashPrice, int pointPrice) {
        this.name = name;
        this.storeId = storeId;
        this.cashPrice = cashPrice;
        this.pointPrice = pointPrice;
    }

    public DiscountDTO(Long id, String name, Long storeId, double cashPrice, int pointPrice) {
        this.id = id;
        this.name = name;
        this.storeId = storeId;
        this.cashPrice = cashPrice;
        this.pointPrice = pointPrice;
    }

    public DiscountDTO(){}

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

    public void setPointPrice(int pointPrice) {
        this.pointPrice = pointPrice;
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
}