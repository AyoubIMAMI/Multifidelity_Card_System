package fr.polytech.pojo;

import java.util.List;

public class Customer {
    private int id;
    private String name;
    private String password;
    private String email;

    private FidelityAccount fidelityAccount;
    private List<Payment> history;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public FidelityAccount getFidelityAccount() {
        return fidelityAccount;
    }

    public List<Payment> getHistory() {
        return history;
    }
}
