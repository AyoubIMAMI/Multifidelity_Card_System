package fr.univcotedazur.simpletcfs.cli.model;

public class FidelityAccount {
    private int points;
    private double balance;
    private boolean isVFP;
    private String licencePlate;

    public FidelityAccount(int points, double balance, boolean isVFP, String licencePlate) {
        this.points = points;
        this.balance = balance;
        this.isVFP = isVFP;
        this.licencePlate = licencePlate;
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
    public String toString() {
        return "CliFidelityAccount{" +
                "points=" + points +
                ", balance=" + balance +
                ", isVFP=" + isVFP +
                ", licencePlate='" + licencePlate + '\'' +
                '}';
    }
}
