package fr.polytech.entities;

public class BankTransaction {
    private Customer customer;

    private float amount;

    private String cardNumber;
    private String cardHolderName;
    private String cvv;
    private String expirationDate;

    public float getAmount() {
        return amount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public String getCvv() {
        return cvv;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
