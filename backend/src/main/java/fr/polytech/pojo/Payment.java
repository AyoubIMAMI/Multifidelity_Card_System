package fr.polytech.pojo;

import fr.polytech.pojo.item.Item;
import fr.polytech.pojo.structure.Store;

import java.util.Set;
import java.util.UUID;

public class Payment {
    private UUID id;
    private Customer customer;
    private Store store;

    private Set<Item> shoppingList;

    private boolean isSettled;

    public boolean isSettled() {
        return isSettled;
    }

    private float price;



    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Set<Item> getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(Set<Item> shoppingList) {
        this.shoppingList = shoppingList;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Store getStore() {
        return store;
    }

    public UUID getId() {
        return id;
    }
}
