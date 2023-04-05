package fr.polytech.entities.item;

import fr.polytech.entities.Payment;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Item {

    @Id
    @GeneratedValue()
    private Long id;

    @NotNull(message = "quantity should not be null")
    private int quantity;

    @ManyToOne
    private Buyable buyable;

    @ManyToOne
    private Payment payment;

    public Item(int quantity, Buyable buyable) {
        this.quantity = quantity;
        this.buyable = buyable;
    }

    public Item() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Buyable getBuyable() {
        return buyable;
    }

    public void setBuyable(Buyable buyable) {
        this.buyable = buyable;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return quantity == item.quantity && Objects.equals(buyable, item.buyable) && Objects.equals(payment, item.payment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity, buyable, payment);
    }

    @Override
    public String toString() {
        return "Item{" +
                "quantity=" + quantity +
                ", buyable=" + buyable +
                '}';
    }
}
