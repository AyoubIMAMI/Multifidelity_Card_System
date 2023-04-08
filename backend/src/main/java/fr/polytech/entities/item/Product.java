package fr.polytech.entities.item;

import fr.polytech.entities.Store;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Product extends Buyable {

    @NotNull(message = "point price should not be null")
    private double cashPrice;

    public Product(Long id,String name, double cashPrice) {
        super(id,name);
        this.cashPrice = cashPrice;
    }
    public Product(String name,Store store, double cashPrice) {
        super(store,name);
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
        if (!super.equals(o)) return false;
        Product product = (Product) o;
        return Double.compare(product.cashPrice, cashPrice) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cashPrice);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CliProduct{");
        if (getId() != null) {
            sb.append("id=").append(getId());
            sb.append(", ");
        }
        sb.append("name='").append(getName()).append('\'');
        sb.append(", store=").append(getStore());
        sb.append(", cashPrice=").append(cashPrice);
        sb.append('}');
        return sb.toString();
    }
}
