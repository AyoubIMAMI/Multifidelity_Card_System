package fr.polytech.exceptions.payment;

public class PaymentAlreadyExistsException extends Exception {

    Long id;

    public PaymentAlreadyExistsException(Long id) {
        this.id = id;
    }

    public PaymentAlreadyExistsException() {}

    public Long getId() {
        return id;
    }
}
