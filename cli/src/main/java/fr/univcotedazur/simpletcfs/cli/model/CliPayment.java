package fr.univcotedazur.simpletcfs.cli.model;

import java.util.Set;

public class CliPayment {
    private Long id;

    private CliCustomer customer;

    private CliStore store;

    private Set<CliItem> shoppingList;

    private boolean isSettled;

    private float amount;

    public CliPayment() {

    }

    public CliPayment(CliCustomer customer, CliStore store, Set<CliItem> shoppingList, boolean isSettled, float amount) {
        this.customer = customer;
        this.store = store;
        this.shoppingList = shoppingList;
        this.isSettled = isSettled;
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

    public boolean isSettled() {
        return isSettled;
    }

    public void setSettled(boolean settled) {
        isSettled = settled;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "CliPayment{" +
                "id=" + id +
                ", customer=" + customer +
                ", store=" + store +
                ", shoppingList=" + shoppingList +
                ", isSettled=" + isSettled +
                ", amount=" + amount +
                '}';
    }
}