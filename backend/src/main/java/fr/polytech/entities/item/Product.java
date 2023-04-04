package fr.polytech.entities.item;

import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class Product extends Buyable {

    private double cashPrice;

    public Product(String name, Long storeId, double cashPrice) {
        super(name, storeId);
        this.cashPrice = cashPrice;
    }

    public Product() {

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
        return Double.compare(product.cashPrice, cashPrice) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cashPrice);
    }

    @Override
    public String toString() {
        return "Product{" +
                "cashPrice=" + cashPrice +
                '}';
    }
}
