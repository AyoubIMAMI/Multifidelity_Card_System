package fr.polytech.entities.test;

import java.util.Objects;

public class Test2 extends Test {

    private int pointPrice;

    public Test2(String name, Long storeId, int pointPrice) {
        super(name, storeId);
        this.pointPrice = pointPrice;
    }


    public int getPointPrice() {
        return pointPrice;
    }

    public void setPointPrice(int pointPrice) {
        this.pointPrice = pointPrice;
    }

    @Override
    public String toString() {
        return "Test2{" +
                "pointPrice=" + pointPrice +
                '}';
    }
}
