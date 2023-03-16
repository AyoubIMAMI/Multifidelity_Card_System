package fr.polytech.entities;

import fr.polytech.entities.item.Item;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import java.util.Set;

@Entity
public class Payment {


    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private Customer customer;

    @OneToOne
    private Store store;

    @OneToMany
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


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
