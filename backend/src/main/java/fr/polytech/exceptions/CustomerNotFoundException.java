package fr.polytech.exceptions;

public class CustomerNotFoundException extends Exception {

    Long id;

    public CustomerNotFoundException(Long id) {
        this.id = id;
    }

    public CustomerNotFoundException() {
    }

    public Long getMail() {
        return id;
    }
}
