package fr.polytech.entities.test;

import java.util.Objects;

public class ItemTest {

    private int quantity;

    private Test test;

    public ItemTest(int quantity, Test test) {
        this.quantity = quantity;
        this.test = test;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    @Override
    public String toString() {
        return "ItemTest{" +
                "quantity=" + quantity +
                ", test=" + test +
                '}';
    }
}
