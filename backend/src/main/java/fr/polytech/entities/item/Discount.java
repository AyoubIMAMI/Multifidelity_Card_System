package fr.polytech.entities.item;

public class Discount extends Product {
    private int pointPrice;

    public Discount(String name, double cashPrice, int pointPrice) {
        super(name, cashPrice);
        this.pointPrice = pointPrice;
    }

    public int getPointPrice() {
        return pointPrice;
    }

    public void setPointPrice(int pointPrice) {
        this.pointPrice = pointPrice;
    }
}
