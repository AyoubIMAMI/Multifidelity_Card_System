package fr.polytech.controllers.dto;


import javax.validation.constraints.NotNull;

public class ItemDTO {
    private Long id;

    @NotNull(message = "quantity should not be null")
    private int quantity;

    private ProductDTO product;

    public ItemDTO(Long id, int quantity, ProductDTO product) {
        this.id = id;
        this.quantity = quantity;
        this.product = product;
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

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "ItemDTO{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", product=" + product +
                '}';
    }
}
