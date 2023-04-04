package fr.polytech.controllers.dto;

import fr.polytech.entities.item.Product;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductDTO {
    private Long id;

    @NotBlank(message = "name should not be blank")
    private String name;

    @NotNull(message = "storeId should not be null")
    private Long storeId;

    @NotNull(message = "cashPrice should not be null")
    private double cashPrice;

    public ProductDTO(Long id, String name, Long storeId, double cashPrice) {
        this.id = id;
        this.name = name;
        this.storeId = storeId;
        this.cashPrice = cashPrice;
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

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", storeId=" + storeId +
                ", cashPrice=" + cashPrice +
                '}';
    }
}
