package fr.polytech.controllers.dto;

import java.util.UUID;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class DiscountDTO {

    private UUID id;

    @NotBlank(message = "name should not be blank")
    private String name;

    @NotNull(message = "storeId should not be null")
    private UUID storeId;

    @NotNull(message = "cashPrice should not be null")
    private double cashPrice;

    @NotNull(message = "pointPrice should not be null")
    private int pointPrice;

    public DiscountDTO(UUID id, String name, UUID storeId, double cashPrice, int pointPrice) {
        this.id = id;
        this.name = name;
        this.storeId = storeId;
        this.cashPrice = cashPrice;
        this.pointPrice = pointPrice;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public UUID getStoreId() {
        return storeId;
    }

    public double getCashPrice() {
        return cashPrice;
    }

    public int getPointPrice() {
        return pointPrice;
    }
}
