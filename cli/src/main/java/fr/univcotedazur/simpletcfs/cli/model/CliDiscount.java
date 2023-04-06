package fr.univcotedazur.simpletcfs.cli.model;

public class CliDiscount extends CliBuyable {

    private int pointPrice;

    public CliDiscount(String name, Long storeId, int pointPrice) {
        super(name, storeId);
        this.pointPrice = pointPrice;
    }

    public CliDiscount() {

    }


    public int getPointPrice() {
        return pointPrice;
    }

    public void setPointPrice(int pointPrice) {
        this.pointPrice = pointPrice;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CliDiscount{");
        if (getId() != null) {
            sb.append("id=").append(getId());
            sb.append(", ");
        }
        sb.append("name='").append(getName()).append('\'');
        sb.append(", storeId=").append(getStoreId());
        sb.append(", ").append("\u001B[35m").append("pointPrice=").append(pointPrice).append("\u001B[0m");
        sb.append('}');
        return sb.toString();
    }
}
