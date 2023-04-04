package fr.polytech.entities.item;

import javax.persistence.Entity;

@Entity
public class Discount extends Buyable {

    private int pointPrice;

    public Discount(String name, Long storeId, int pointPrice) {
        super(name, storeId);
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
    public String toString() {
        return "Discount{" +
                "pointPrice=" + pointPrice +
                '}';
    }
}
