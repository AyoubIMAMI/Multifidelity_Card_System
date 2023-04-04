package fr.polytech.entities.test;

import java.util.Objects;

public class Test1 extends Test {

    private double cashPrice;

    public Test1(String name, Long storeId, double cashPrice) {
        super(name, storeId);
        this.cashPrice = cashPrice;
    }

    public double getCashPrice() {
        return cashPrice;
    }

    public void setCashPrice(double cashPrice) {
        this.cashPrice = cashPrice;
    }

    @Override
    public String toString() {
        return "Test1{" +
                "cashPrice=" + cashPrice +
                '}';
    }
}
