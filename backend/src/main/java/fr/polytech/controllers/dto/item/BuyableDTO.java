package fr.polytech.controllers.dto.item;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ProductDTO.class, name = "product"),
        @JsonSubTypes.Type(value = DiscountDTO.class, name = "discount")
})
public abstract class BuyableDTO {

    private Long id;

    @NotBlank(message = "name should not be blank")
    private String name;

    @NotNull(message = "storeId should not be null")
    private Long storeId;

    public BuyableDTO(String name, Long storeId) {
        this.name = name;
        this.storeId = storeId;
    }

    public BuyableDTO() {

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
        BuyableDTO buyableDTO = (BuyableDTO) o;
        return Objects.equals(name, buyableDTO.name) && Objects.equals(storeId, buyableDTO.storeId);
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
