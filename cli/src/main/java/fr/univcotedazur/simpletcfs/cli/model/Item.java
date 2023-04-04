package fr.univcotedazur.simpletcfs.cli.model;


public class Item {

    private Long id;

    private int quantity;

    private Buyable buyable;

    private Payment payment;

    public Item(int quantity, Buyable buyable) {
        this.quantity = quantity;
        this.buyable = buyable;
    }

    public Item() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Buyable getBuyable() {
        return buyable;
    }

    public void setBuyable(Buyable buyable) {
        this.buyable = buyable;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }


    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", buyable=" + buyable +
                ", payment=" + payment +
                '}';
    }
}
