package fr.univcotedazur.simpletcfs.cli.model;

public class CliItem {

    private Long id;
    private int quantity;
    private CliProduct cliProduct;

    public CliItem(int quantity, CliProduct cliProduct) {
        this.quantity = quantity;
        this.cliProduct = cliProduct;
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

    public CliProduct getProduct() {
        return cliProduct;
    }

    public void setProduct(CliProduct cliProduct) {
        this.cliProduct = cliProduct;
    }
}
