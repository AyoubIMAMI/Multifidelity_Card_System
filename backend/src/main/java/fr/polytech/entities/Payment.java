package fr.polytech.entities;

import fr.polytech.entities.item.Discount;
import fr.polytech.entities.item.Item;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Payment {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private Customer customer;

    @ManyToOne
    private Store store;

    @OneToMany
    private Set<Item> shoppingList;
    private boolean isSettled;
    private float amount;

    public Payment(Customer customer, Store store, Set<Item> shoppingList, boolean isSettled) {
        this.customer = customer;
        this.store = store;
        this.shoppingList = shoppingList;
        this.isSettled = isSettled;
        this.amount = computeShoppingListPrice(shoppingList);
    }

    public Payment() {

    }

    private float computeShoppingListPrice(Set<Item> shoppingList) {
        double amount = shoppingList.stream()
                .filter(x -> !(x.getProduct() instanceof Discount))
                .map(x -> x.getQuantity() * x.getProduct().getCashPrice())
                .reduce(Double::sum).orElse((double) 0);
        return (float) amount;
    }

    public boolean isSettled() {
        return isSettled;
    }



    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(isSettled, payment.isSettled) && Objects.equals(amount, payment.amount) && Objects.equals(customer, payment.customer) && Objects.equals(store, payment.store) && Objects.equals(shoppingList, payment.shoppingList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, store, shoppingList, isSettled, amount);
    }
}
