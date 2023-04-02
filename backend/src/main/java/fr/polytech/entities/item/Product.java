package fr.polytech.entities.item;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Long storeId;
    private double cashPrice;

    public Product(String name, Long storeId, double cashPrice) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(cashPrice, product.cashPrice) && Objects.equals(name, product.name) && Objects.equals(storeId, product.storeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, storeId, cashPrice);
    }
}
