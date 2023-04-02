package fr.univcotedazur.simpletcfs.cli.model;

public class CliPayment {
    private Long id;

    private CliCustomer customer;

    //private CliStore store;

    //private Set<CliItem> shoppingList;

    //private boolean isSettled;

    //private float amount;

    public CliPayment(CliCustomer customer) {
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CliCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(CliCustomer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "CliPayment{" +
                "id=" + id +
                ", customer=" + customer +
                '}';
    }
}