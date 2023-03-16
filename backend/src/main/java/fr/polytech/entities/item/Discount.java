package fr.polytech.entities.item;


public class Discount extends Product {
    private int pointPrice;

    public Discount(String name, Long storeId, double cashPrice, int pointPrice) {
        super(name, storeId, cashPrice);
        this.pointPrice = pointPrice;
    }

    public int getPointPrice() {
        return pointPrice;
    }

    public void setPointPrice(int pointPrice) {
        this.pointPrice = pointPrice;
    }
}
