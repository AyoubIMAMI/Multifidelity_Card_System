package fr.univcotedazur.simpletcfs.cli.model;

import java.util.Set;

public class Payment {
    private Long id;

    private Customer customer;

    private Store store;

    private Set<Item> shoppingList;

    private boolean isSettled;

    private float amount;

    public Payment() {

    }

    public Payment(Customer customer, Store store, Set<Item> shoppingList, boolean isSettled, float amount) {
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Set<Item> getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(Set<Item> shoppingList) {
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
                ", storeId=" + store.getId() +
                ", shoppingList=" + shoppingList +
                ", amount=" + amount +
                '}';
    }
}