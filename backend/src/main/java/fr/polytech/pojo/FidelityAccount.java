package fr.polytech.pojo;

import java.util.Set;
import java.util.UUID;

public class FidelityAccount {
    private final UUID clientId;
    private int points;
    private double balance;
    private boolean isVFP;
    private Set<Advantage> advantages;
    private String licencePlate;

    public FidelityAccount(UUID clientId) {
        this.clientId = clientId;
        points = 0;
        balance = 0;
        isVFP = false;
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

    public UUID getClientId() {
        return clientId;
    }
}
