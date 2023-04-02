package fr.univcotedazur.simpletcfs.cli.model;

public class CliDiscount extends CliProduct {
    private int pointPrice;

    public CliDiscount(String name, Long storeId, double cashPrice, int pointPrice) {
        super(name, storeId, cashPrice);
        this.pointPrice = pointPrice;
    }

    @Override
    public String toString() {
        return "DiscountDTO{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", storeId=" + getStoreId() +
                ", cashPrice=" + getCashPrice() +
                ", pointPrice=" + pointPrice +
                '}';
    }

    public int getPointPrice() {
        return pointPrice;
    }

    public void setPointPrice(int pointPrice) {
        this.pointPrice = pointPrice;
    }
}