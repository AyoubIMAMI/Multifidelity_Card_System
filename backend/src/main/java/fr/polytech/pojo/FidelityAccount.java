package fr.polytech.pojo;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class FidelityAccount {
    private UUID clientId;
    private int points;
    private float balance;
    private boolean isVFP;
    private Set<Advantage> advantages;
    private String licencePlate;

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public UUID getClientId() {
        return clientId;
    }
}
