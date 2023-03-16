package fr.polytech.entities.item;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    private Long storeId;
    private double cashPrice;

    public Product(String name, Long storeId, double cashPrice) {
        this.id = storeId; //TODO A changer
        this.name = name;
        this.storeId = storeId;
        this.cashPrice = cashPrice;
    }

    public Product() {

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
