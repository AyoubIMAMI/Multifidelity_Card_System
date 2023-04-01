package fr.polytech.connectors.externaldto;

// External DTO (Data Transfer Object) to POST payment to the external Bank system
public class BankTransactionDTO {

    private String creditCard;

    private double amount;

    public BankTransactionDTO() {
    }

    public BankTransactionDTO(String creditCard, double amount) {
        this.creditCard = creditCard;
        this.amount = amount;
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
}
