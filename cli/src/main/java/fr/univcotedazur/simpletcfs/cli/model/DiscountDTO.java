package fr.univcotedazur.simpletcfs.cli.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class DiscountDTO {
    private Long id;

    private String name;

    private Long storeId;

    private double cashPrice;

    private int pointPrice;

    public DiscountDTO(String name, Long storeId, double cashPrice, int pointPrice) {
        this.name = name;
        this.storeId = storeId;
        this.cashPrice = cashPrice;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public double getCashPrice() {
        return cashPrice;
    }

    public void setCashPrice(double cashPrice) {
        this.cashPrice = cashPrice;
    }

    public int getPointPrice() {
        return pointPrice;
    }

    public void setPointPrice(int pointPrice) {
        this.pointPrice = pointPrice;
    }
}