package fr.polytech.components.payment;

import fr.polytech.exceptions.NotEnoughBalanceException;
import fr.polytech.interfaces.payment.PointPurchase;
import fr.polytech.pojo.Customer;
import fr.polytech.pojo.Payment;
import fr.polytech.pojo.structure.Store;

public class PointPurchaseHandler implements PointPurchase {
    @Override
    public void buyWithPoint(Customer customer, Payment payment, Store store) throws NotEnoughBalanceException {

    }
}
