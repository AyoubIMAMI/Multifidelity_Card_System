package fr.polytech.entities;

import fr.polytech.entities.item.Item;
import fr.polytech.entities.item.Product;

import javax.persistence.*;

import java.util.Date;
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

    @ElementCollection
    private Set<Item> shoppingList = new HashSet<>();
    
    private float amount;

    private Date transactionDate;

    public Payment(Customer customer, Store store, Set<Item> shoppingList) {
        this.customer = customer;
        this.store = store;
        this.shoppingList = shoppingList;
        this.amount = computeShoppingListPrice(shoppingList);
        this.transactionDate = new Date();
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

    public Date getTransactionDate(){
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
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

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", customer=" + customer +
                ", store=" + store +
                ", shoppingList=" + shoppingList +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Float.compare(payment.amount, amount) == 0 && Objects.equals(customer, payment.customer) && Objects.equals(store, payment.store) && Objects.equals(shoppingList, payment.shoppingList) && Objects.equals(transactionDate, payment.transactionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, store, shoppingList, amount, transactionDate);
    }
}
