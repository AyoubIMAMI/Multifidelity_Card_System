package fr.univcotedazur.simpletcfs.cli.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Product.class, name = "product"),
        @JsonSubTypes.Type(value = Discount.class, name = "discount")
})
public abstract class Buyable {

    private Long id;

    private String name;

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
    public String toString() {
        return "Buyable{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", storeId=" + storeId +
                '}';
    }
}
