package fr.polytech.controllers.dto;

import fr.polytech.entities.item.Item;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

public class PaymentDTO {

    private Long id;

    private CustomerDTO customer;

    private StoreDTO store;

    private Set<Item> shoppingList;

    private float amount;

    private Date transactionDate;

    public PaymentDTO(Long id, CustomerDTO customer, StoreDTO store, Set<Item> shoppingList, Date transactionDate, float amount) {
        this.id = id;
        this.customer = customer;
        this.store = store;
        this.shoppingList = shoppingList;
        this.transactionDate = transactionDate;
        this.amount = amount;
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

    public Date getTransactionDate() {
        return transactionDate;
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

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentDTO that = (PaymentDTO) o;
        return Float.compare(that.amount, amount) == 0 && Objects.equals(customer, that.customer) && Objects.equals(store, that.store) && Objects.equals(shoppingList, that.shoppingList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, store, shoppingList, amount);
    }

    @Override
    public String toString() {
        return "PaymentDTO{" +
                "id=" + id +
                ", customer=" + customer +
                ", store=" + store +
                ", shoppingList=" + shoppingList +
                ", amount=" + amount +
                '}';
    }
}
