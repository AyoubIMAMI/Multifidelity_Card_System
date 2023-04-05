package fr.polytech.exceptions;

public class NotEnoughBalanceException extends Exception {

    double amountRequired;
    double amountOwned;

    public NotEnoughBalanceException(double amountRequired, double amountOwned) {
        this.amountRequired = amountRequired;
        this.amountOwned = amountOwned;
    }

    public  NotEnoughBalanceException() {}

    public double getAmountRequired() {
        return amountRequired;
    }

    public double getAmountOwned() {
        return amountOwned;
    }
}
