package fr.polytech.pojo;

import java.util.Optional;
import java.util.Set;

public class FidelityAccount {
    private int clientId;
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
}
