package fr.univcotedazur.simpletcfs.cli.model;

public class CliProduct {

    private Long id;
    private String name;
    private Long storeId;
    private double cashPrice;

    public CliProduct(String name, Long storeId, double cashPrice) {
        this.name = name;
        this.storeId = storeId;
        this.cashPrice = cashPrice;
    }

    public CliProduct() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public double getCashPrice() {
        return cashPrice;
    }

    public void setCashPrice(double cashPrice) {
        this.cashPrice = cashPrice;
    }

    @Override
    public String toString() {
        return "CliProduct{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", storeId=" + storeId +
                ", cashPrice=" + cashPrice +
                '}';
    }
}
