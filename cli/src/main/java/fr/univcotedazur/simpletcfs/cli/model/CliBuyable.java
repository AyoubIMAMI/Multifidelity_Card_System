package fr.univcotedazur.simpletcfs.cli.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CliProduct.class, name = "product"),
        @JsonSubTypes.Type(value = CliDiscount.class, name = "discount")
})
public abstract class CliBuyable {

    private Long id;

    private String name;

    private Long storeId;

    public CliBuyable(String name, Long storeId) {
        this.name = name;
        this.storeId = storeId;
    }

    public CliBuyable() {

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
        return "CliBuyable{" +
                "\u001B[32m" + "id=" + id + "\u001B[0m" +
                ", name='" + name + '\'' +
                ", storeId=" + storeId +
                '}';
    }
}
