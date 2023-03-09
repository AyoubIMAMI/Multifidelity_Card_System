package fr.polytech.entities.item;


public class Product {

    private final Long id;
    private String name;

    private final Long storeId;
    private double cashPrice;

    public Product(String name, Long storeId, double cashPrice) {
        this.id;
        this.name = name;
        this.storeId = storeId;
        this.cashPrice = cashPrice;
    }

    public Long getStoreId() {
        return storeId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getCashPrice() {
        return cashPrice;
    }
}
