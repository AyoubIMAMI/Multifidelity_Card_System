package fr.polytech.entities.item;

import javax.persistence.Entity;
import java.util.Objects;
import java.util.UUID;
@Entity
public class Discount extends Product {
    private int pointPrice;

    public Discount(String name, Long storeId, double cashPrice, int pointPrice) {
        super(name, storeId, cashPrice);
        this.pointPrice = pointPrice;
    }

    public Discount() {

    }

    public int getPointPrice() {
        return pointPrice;
    }

    public void setPointPrice(int pointPrice) {
        this.pointPrice = pointPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;
        Discount discount = (Discount) o;
        return Objects.equals(getName(), discount.getName())
                && Objects.equals(getStoreId(), discount.getStoreId())
                && Objects.equals(getCashPrice(), discount.getCashPrice())
                && Objects.equals(getPointPrice(), discount.getPointPrice());
    }

}
