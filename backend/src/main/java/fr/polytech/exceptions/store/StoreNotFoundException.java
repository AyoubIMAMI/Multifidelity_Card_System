package fr.polytech.exceptions.store;

public class StoreNotFoundException extends Exception {

    Long id;

    public StoreNotFoundException(Long id) {
        this.id = id;
    }

    public StoreNotFoundException() {}

    public Long getId() {
        return id;
    }
}
