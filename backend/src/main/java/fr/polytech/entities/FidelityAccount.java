package fr.polytech.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;
@Entity
public class FidelityAccount {
    @Id
    private Long clientId;
    private int points;
    private double balance;
    private boolean isVFP;
    private Set<Advantage> advantages;
    private String licencePlate;

    public FidelityAccount(Long clientId) {
        this.clientId = clientId;
        points = 0;
        balance = 0;
        isVFP = false;
    }

    public FidelityAccount() {
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
