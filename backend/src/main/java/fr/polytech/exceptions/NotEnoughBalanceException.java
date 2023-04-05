package fr.polytech.exceptions;

public class NotEnoughBalanceException extends Exception {

    private double balance;

    private double amount;

    public NotEnoughBalanceException(double balance, double amount) {
        this.balance = balance;
        this.amount = amount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
