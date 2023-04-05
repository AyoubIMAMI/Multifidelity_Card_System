package fr.polytech.exceptions.payment;

public class NegativeAmountException extends Exception {

    double amount;

    public NegativeAmountException(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }
}
