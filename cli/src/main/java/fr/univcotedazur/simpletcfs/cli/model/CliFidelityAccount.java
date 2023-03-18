package fr.univcotedazur.simpletcfs.cli.model;

import java.util.Objects;

public class CliFidelityAccount {

    private Long clientId;
    private int points;
    private double balance;
    private boolean isVFP;
    //@OneToMany
    //private Set<Advantage> advantages;
    private String licencePlate;

    public CliFidelityAccount(Long clientId) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CliFidelityAccount that = (CliFidelityAccount) o;

        if (points != that.points) return false;
        if (Double.compare(that.balance, balance) != 0) return false;
        if (isVFP != that.isVFP) return false;
        if (!Objects.equals(clientId, that.clientId)) return false;
        return Objects.equals(licencePlate, that.licencePlate);
    }
}
