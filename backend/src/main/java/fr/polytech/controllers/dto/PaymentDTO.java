package fr.polytech.controllers.dto;

import fr.polytech.entities.item.Item;

import java.util.Set;

public class PaymentDTO {

    private Long id;

    private CustomerDTO customer;

    private StoreDTO store;

    private Set<Item> shoppingList;

    private boolean isSettled;

    private float amount;

    public PaymentDTO(Long id, CustomerDTO customer, StoreDTO store, Set<Item> shoppingList, boolean isSettled, float amount) {
        this.id = id;
        this.customer = customer;
        this.store = store;
        this.shoppingList = shoppingList;
        this.isSettled = isSettled;
        this.amount = amount;
    }


    @Override
    public String toString() {
        return "PaymentDTO{" +
                "id=" + id +
                ", customer=" + customer +
                ", store=" + store +
                ", shoppingList=" + shoppingList +
                ", isSettled=" + isSettled +
                ", amount=" + amount +
                '}';
    }

    public Long getId() {
        return id;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public StoreDTO getStore() {
        return store;
    }

    public Set<Item> getShoppingList() {
        return shoppingList;
    }

    public boolean isSettled() {
        return isSettled;
    }

    public float getAmount() {
        return amount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public void setStore(StoreDTO store) {
        this.store = store;
    }

    public void setShoppingList(Set<Item> shoppingList) {
        this.shoppingList = shoppingList;
    }

    public void setSettled(boolean settled) {
        isSettled = settled;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
