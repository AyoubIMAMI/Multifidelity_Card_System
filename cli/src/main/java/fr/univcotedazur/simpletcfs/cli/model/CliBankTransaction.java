package fr.univcotedazur.simpletcfs.cli.model;

// External DTO (Data Transfer Object) to POST payment to the external Bank system
public class CliBankTransaction {

    private String creditCard;

    private double amount;

    public CliBankTransaction(String creditCard, double amount) {
        this.creditCard = creditCard;
        this.amount = amount;
    }

    public CliBankTransaction() {
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "CliBankTransaction{" +
                "creditCard='" + creditCard + '\'' +
                ", amount=" + amount +
                '}';
    }
}
