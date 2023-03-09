package fr.polytech.pojo.item;

import java.util.UUID;

public class Product {

    private final UUID id;
    private String name;

    private final UUID storeId;
    private double cashPrice;

    public Product(String name, UUID storeId, double cashPrice) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.storeId = storeId;
        this.cashPrice = cashPrice;
    }

    public UUID getStoreId() {
        return storeId;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getCashPrice() {
        return cashPrice;
    }
}
