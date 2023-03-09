package fr.polytech.entities;

import javax.persistence.*;
import java.util.Set;

@Embeddable
public class FidelityAccount {

    private Long clientId;
    private int points;
    private double balance;
    private boolean isVFP;
    //@OneToMany
    //private Set<Advantage> advantages;
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
}
