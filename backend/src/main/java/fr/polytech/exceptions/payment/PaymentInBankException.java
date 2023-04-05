package fr.polytech.exceptions.payment;

public class PaymentInBankException extends Exception {

    double amount;

    public PaymentInBankException(double amount) {
        this.amount = amount;
    }

    public PaymentInBankException() {}

    public double getAmount() {
        return amount;
    }
}
