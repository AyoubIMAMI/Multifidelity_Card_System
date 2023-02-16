package fr.polytech.components.payment;

import fr.polytech.interfaces.payment.SettledPurchase;
import fr.polytech.pojo.Customer;
import fr.polytech.pojo.Payment;
import fr.polytech.pojo.structure.Store;

public class SettledPurchaseHandler implements SettledPurchase {
    @Override
    public void validatePurchase(Customer customer, Payment payment, Store store) {

    }
}
