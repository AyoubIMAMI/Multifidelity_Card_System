package fr.polytech.entities.test;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Test1.class, name = "test1"),
        @JsonSubTypes.Type(value = Test2.class, name = "test2")
})
public abstract class Test {

    private String name;

    private Long storeId;

    public Test(String name, Long storeId) {
        this.name = name;
        this.storeId = storeId;
    }

    @Override
    public String toString() {
        return "Test{" +
                ", name='" + name + '\'' +
                ", storeId=" + storeId +
                '}';
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
}
