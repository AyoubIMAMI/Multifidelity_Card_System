package fr.univcotedazur.simpletcfs.cli.model;


public class CliItem {

    private Long id;

    private int quantity;

    private CliBuyable buyable;

    public CliItem(int quantity, CliBuyable buyable) {
        this.quantity = quantity;
        this.buyable = buyable;
    }

    public CliItem() {

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

    public CliBuyable getBuyable() {
        return buyable;
    }

    public void setBuyable(CliBuyable buyable) {
        this.buyable = buyable;
    }


    @Override
    public String toString() {
        return "CliItem{" +
                "quantity=" + quantity +
                ", buyable=" + buyable +
                '}';
    }
}
