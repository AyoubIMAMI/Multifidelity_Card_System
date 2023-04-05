package fr.polytech.entities;

import javax.persistence.*;
import java.util.Objects;

@Embeddable
public class FidelityAccount {

    private int points;
    private double balance;
    private boolean isVFP;
    //TODO
    //private Set<Advantage> advantages;
    private String licencePlate;

    public FidelityAccount() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FidelityAccount that = (FidelityAccount) o;

        if (points != that.points) return false;
        if (Double.compare(that.balance, balance) != 0) return false;
        if (isVFP != that.isVFP) return false;
        return Objects.equals(licencePlate, that.licencePlate);
    }

    public boolean isVFP() {
        return isVFP;
    }

    public void setVFP(boolean VFP) {
        isVFP = VFP;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(points, balance, isVFP, licencePlate);
    }

    @Override
    public String toString() {
        return "FidelityAccount{" +
                "points=" + points +
                ", balance=" + balance +
                ", isVFP=" + isVFP +
                ", licencePlate='" + licencePlate + '\'' +
                '}';
    }
}
