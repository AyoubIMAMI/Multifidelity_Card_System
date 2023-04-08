package fr.polytech.entities.item;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import fr.polytech.entities.Store;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    @GeneratedValue
    private Long id;

    @NotBlank(message = "name should not be blank")
    private String name;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    public Buyable(Store store,String name) {
        this.store=store;
        this.name = name;
    }
    public Buyable(Long id,String name) {
        this.id=id;
        this.name = name;
    }

    public Buyable() {

    }

    public void setStore(Store store) {
        this.store = store;
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

    public Store getStore() {
        return store;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Buyable buyable = (Buyable) o;

        if (!Objects.equals(id, buyable.id)) return false;
        if (!Objects.equals(name, buyable.name)) return false;
        return Objects.equals(store, buyable.store);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (store != null ? store.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Buyable{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", store=" + store +
                '}';
    }
}
