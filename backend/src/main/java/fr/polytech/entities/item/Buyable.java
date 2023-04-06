package fr.polytech.entities.item;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Product.class, name = "product"),
        @JsonSubTypes.Type(value = Discount.class, name = "discount")
})

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "buyable_type")
public abstract class Buyable {

    @Id
    @GeneratedValue()
    private Long id;

    @NotBlank(message = "name should not be blank")
    private String name;

    @NotNull(message = "storeId should not be null")
    private Long storeId;

    public Buyable(String name, Long storeId) {
        this.name = name;
        this.storeId = storeId;
    }

    public Buyable() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Buyable buyable = (Buyable) o;
        return Objects.equals(name, buyable.name) && Objects.equals(storeId, buyable.storeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, storeId);
    }

    @Override
    public String toString() {
        return "Buyable{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", storeId=" + storeId +
                '}';
    }
}
