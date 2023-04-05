package fr.polytech.entities;

import java.util.Objects;

public class BankTransaction {

    private Customer customer;
    private float amount;
    private String cardNumber;
    private String cardHolderName;
    private String cvv;
    private String expirationDate;

    public BankTransaction(Customer customer, float amount, String cardNumber, String cardHolderName, String cvv, String expirationDate) {
        this.customer = customer;
        this.amount = amount;
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.cvv = cvv;
        this.expirationDate = expirationDate;
    }

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

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankTransaction that = (BankTransaction) o;
        return Float.compare(that.amount, amount) == 0 && Objects.equals(customer, that.customer) && Objects.equals(cardNumber, that.cardNumber) && Objects.equals(cardHolderName, that.cardHolderName) && Objects.equals(cvv, that.cvv) && Objects.equals(expirationDate, that.expirationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, amount, cardNumber, cardHolderName, cvv, expirationDate);
    }

    @Override
    public String toString() {
        return "BankTransaction{" +
                "customer=" + customer +
                ", amount=" + amount +
                ", cardNumber='" + cardNumber + '\'' +
                ", cardHolderName='" + cardHolderName + '\'' +
                ", cvv='" + cvv + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                '}';
    }
}
