package fr.polytech.entities.item;

import fr.polytech.entities.Store;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Discount extends Buyable {

    @NotNull(message = "point price should not be null")
    private Integer pointPrice;


    public Discount(String name,Store store, int pointPrice) {
        super(store,name);
        this.pointPrice = pointPrice;
    }
    public Discount(Long id,String name, int pointPrice) {
        super(id,name);
        this.pointPrice = pointPrice;
    }

    public Discount() {

    }


    public int getPointPrice() {
        return pointPrice;
    }

    public void setPointPrice(int pointPrice) {
        this.pointPrice = pointPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Discount discount = (Discount) o;
        return pointPrice == discount.pointPrice;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), pointPrice);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CliDiscount{");
        if (getId() != null) {
            sb.append("id=").append(getId());
            sb.append(", ");
        }
        sb.append("name='").append(getName()).append('\'');
        sb.append(", store=").append(getStore());
        sb.append(", pointPrice=").append(pointPrice);
        sb.append('}');
        return sb.toString();
    }
}
