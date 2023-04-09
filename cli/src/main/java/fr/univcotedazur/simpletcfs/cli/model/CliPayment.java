package fr.univcotedazur.simpletcfs.cli.model;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

public class CliPayment {
    private Long id;

    private CliCustomer customer;

    private CliStore store;

    private Set<CliItem> shoppingList;

    private float amount;

    private Date transactionDate;

    public CliPayment() {

    }

    public CliPayment(CliCustomer customer, CliStore store, Set<CliItem> shoppingList, Date transactionDate, float amount) {
        this.customer = customer;
        this.store = store;
        this.shoppingList = shoppingList;
        this.transactionDate = transactionDate;
        this.amount = amount;
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

    public CliStore getStore() {
        return store;
    }

    public void setStore(CliStore store) {
        this.store = store;
    }

    public Set<CliItem> getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(Set<CliItem> shoppingList) {
        this.shoppingList = shoppingList;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CliPayment that = (CliPayment) o;
        return Float.compare(that.amount, amount) == 0 && Objects.equals(customer, that.customer) && Objects.equals(store, that.store) && Objects.equals(shoppingList, that.shoppingList) && Objects.equals(transactionDate, that.transactionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, store, shoppingList, amount, transactionDate);
    }

    @Override
    public String toString() {
        return "CliPayment{" +
                "id=" + id +
                ", customer=" + customer +
                ", store=" + store +
                ", shoppingList=" + shoppingList +
                ", amount=" + amount +
                ", transactionDate=" + transactionDate +
                '}';
    }
}
