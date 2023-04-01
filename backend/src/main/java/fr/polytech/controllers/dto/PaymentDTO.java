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
}
