package fr.polytech.interfaces.Payment;

import fr.polytech.pojo.Customer;
import fr.polytech.pojo.Payment;
import fr.polytech.pojo.structure.Store;

public interface PointPurchase {
    void buyWithPoint(Customer customer, Payment payment, Store store);
}
