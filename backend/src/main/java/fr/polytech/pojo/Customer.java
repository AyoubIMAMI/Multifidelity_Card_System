package fr.polytech.pojo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Customer {
    private final UUID id;
    private String name;
    private String password;
    private String email;
    private FidelityAccount fidelityAccount;
    private List<Payment> history;

    public Customer(String name, String email, String password){
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.password = password;
        this.fidelityAccount = new FidelityAccount(id);
        this.history = new ArrayList<>();
    }

    public UUID getId() {
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
