package fr.polytech.controllers.dto.item;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class ItemDTO {

    @NotNull(message = "quantity should not be null")
    private int quantity;

    private BuyableDTO buyableDTO;

    public ItemDTO(int quantity, BuyableDTO buyableDTO) {
        this.quantity = quantity;
        this.buyableDTO = buyableDTO;
    }

    public ItemDTO() {

    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BuyableDTO getBuyable() {
        return buyableDTO;
    }

    public void setBuyable(BuyableDTO buyableDTO) {
        this.buyableDTO = buyableDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemDTO itemDTO = (ItemDTO) o;
        return quantity == itemDTO.quantity && Objects.equals(buyableDTO, itemDTO.buyableDTO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity, buyableDTO);
    }

    @Override
    public String toString() {
        return "Item{" +
                "quantity=" + quantity +
                ", buyable=" + buyableDTO +
                '}';
    }
}
