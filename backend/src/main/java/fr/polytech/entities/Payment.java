package fr.polytech.entities;

import fr.polytech.entities.item.Discount;
import fr.polytech.entities.item.Item;
import fr.polytech.entities.item.Product;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Payment {

    @Id
    @GeneratedValue()
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    private boolean isSettled;

    @OneToMany(mappedBy = "payment")
    private Set<Item> shoppingList = new HashSet<>();
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
                .filter(x -> (x.getBuyable() instanceof Product))
                .map(x -> x.getQuantity() * ((Product) x.getBuyable()).getCashPrice())
                .reduce(Double::sum).orElse((double) 0);

        System.out.println("Amount du payment : " + amount);

        return (float) amount;
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

    public boolean isSettled() {
        return isSettled;
    }

    public void setSettled(boolean settled) {
        isSettled = settled;
    }

    public Set<Item> getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(Set<Item> shoppingList) {
        this.shoppingList = shoppingList;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return isSettled == payment.isSettled && Float.compare(payment.amount, amount) == 0 && Objects.equals(customer, payment.customer) && Objects.equals(store, payment.store) && Objects.equals(shoppingList, payment.shoppingList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, store, isSettled, shoppingList, amount);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", customer=" + customer +
                ", store=" + store +
                ", isSettled=" + isSettled +
                ", shoppingList=" + shoppingList +
                ", amount=" + amount +
                '}';
    }
}
