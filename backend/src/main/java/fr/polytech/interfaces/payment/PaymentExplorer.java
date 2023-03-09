package fr.polytech.interfaces.payment;

import fr.polytech.entities.Customer;
import fr.polytech.entities.Payment;
import fr.polytech.exceptions.CustomerNotFoundException;
import fr.polytech.exceptions.StoreNotFoundException;
import fr.polytech.exceptions.payment.PaymentNotFoundException;

import fr.polytech.entities.Store;

import java.util.UUID;

public interface PaymentExplorer {

    Payment findPaymentById(UUID id) throws PaymentNotFoundException;
    Iterable<Payment> findPaymentsByCustomer(Customer customer) throws CustomerNotFoundException, PaymentNotFoundException;
    Iterable<Payment> findPaymentsByStore(Store store) throws StoreNotFoundException, PaymentNotFoundException;
}
