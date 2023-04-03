package fr.polytech.entities.item;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Product {

    @Id
    @GeneratedValue()
    private Long id;

    private String name;

    private Long storeId;

    private double cashPrice;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", storeId=" + storeId +
                ", cashPrice=" + cashPrice +
                '}';
    }

    public Product(String name, Long storeId, double cashPrice) {
        this.name = name;
        this.storeId = storeId;
        this.cashPrice = cashPrice;
    }

    public Product() {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.cashPrice, cashPrice) == 0 && Objects.equals(name, product.name) && Objects.equals(storeId, product.storeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, storeId, cashPrice);
    }
}
