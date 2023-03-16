package fr.polytech.entities.item;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Item {
    private int quantity;
    @OneToOne
    private Product product;
    @Id
    @GeneratedValue
    private Long id;

    public Item(int quantity, Product product) {
        this.quantity = quantity;
        //this.product = product;
    }

    public Item() {

    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }**/

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }
}
