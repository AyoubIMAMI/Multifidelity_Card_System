package fr.polytech.interfaces.payment;

import fr.polytech.exceptions.CustomerNotFoundException;
import fr.polytech.exceptions.StoreNotFoundException;
import fr.polytech.exceptions.payment.PaymentNotFoundException;
import fr.polytech.pojo.Customer;
import fr.polytech.pojo.Payment;
import fr.polytech.pojo.structure.Store;

import java.util.UUID;

public interface PaymentExplorer {

    Payment findPaymentById(UUID id) throws PaymentNotFoundException;
    Iterable<Payment> findPaymentsByCustomer(Customer customer) throws CustomerNotFoundException, PaymentNotFoundException;
    Iterable<Payment> findPaymentsByStore(Store store) throws StoreNotFoundException, PaymentNotFoundException;
}
