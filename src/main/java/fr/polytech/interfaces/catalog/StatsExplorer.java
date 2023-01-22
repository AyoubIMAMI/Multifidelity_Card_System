package fr.polytech.interfaces.catalog;

public interface StatsExplorer {
    //return the ratio between the total of people payment and the total of the advantage unlocked
    double advantageRatio();
    //return the ratio between the total of people payment and the total of the discount unlocked
    double discountRatio();
    //return the ratio between the total of people payment and the total of the advantage unlocked for a given store
    double advantageRatioByStore(String storeName, String myName, String myPassword);
    //return the ratio between the total of people payment and the total of the discount unlocked for a given store
    double discountRatioByStore(String storeName, String myName, String myPassword);
}
