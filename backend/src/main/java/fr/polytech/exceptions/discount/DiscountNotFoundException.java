package fr.polytech.exceptions.discount;

public class DiscountNotFoundException extends Exception{

    Long id;
    String name;

    public DiscountNotFoundException(Long id) {
        this.id = id;
    }

    public DiscountNotFoundException(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
