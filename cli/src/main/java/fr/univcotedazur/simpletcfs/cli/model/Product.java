package fr.univcotedazur.simpletcfs.cli.model;

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
    public String toString() {
        return "Product{" +
                "cashPrice=" + cashPrice +
                '}';
    }
}
