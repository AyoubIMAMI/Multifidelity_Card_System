package fr.polytech.pojo;

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
}
