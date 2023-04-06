package fr.polytech.entities.item;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Embeddable
public class Item {

    @NotNull(message = "quantity should not be null")
    private int quantity;

    @ManyToOne
    private Buyable buyable;

    public Item(int quantity, Buyable buyable) {
        this.quantity = quantity;
        this.buyable = buyable;
    }

    public Item() {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return quantity == item.quantity && Objects.equals(buyable, item.buyable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity, buyable);
    }

    @Override
    public String toString() {
        return "Item{" +
                "quantity=" + quantity +
                ", buyable=" + buyable +
                '}';
    }
}
