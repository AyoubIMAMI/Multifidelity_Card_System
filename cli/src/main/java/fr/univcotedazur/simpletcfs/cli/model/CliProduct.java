package fr.univcotedazur.simpletcfs.cli.model;

public class CliProduct extends CliBuyable {

    private double cashPrice;

    public CliProduct(String name, Long storeId, double cashPrice) {
        super(name, storeId);
        this.cashPrice = cashPrice;
    }

    public CliProduct() {

    }

    public double getCashPrice() {
        return cashPrice;
    }

    public void setCashPrice(double cashPrice) {
        this.cashPrice = cashPrice;
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
        sb.append(", storeId=").append(getStoreId());
        sb.append(", ").append("\u001B[35m").append("cashPrice=").append(cashPrice).append("\u001B[0m");
        sb.append('}');
        return sb.toString();
    }
}
