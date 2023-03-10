package fr.polytech.interfaces.payment;

import fr.polytech.entities.Customer;
import fr.polytech.entities.Payment;
import fr.polytech.exceptions.CustomerNotFoundException;
import fr.polytech.exceptions.StoreNotFoundException;
import fr.polytech.exceptions.payment.PaymentNotFoundException;

import fr.polytech.entities.Store;

import java.util.Optional;


public interface PaymentExplorer {

    Payment findPaymentById(Long id) throws PaymentNotFoundException;
    Optional<Payment> findPaymentsByCustomer(Customer customer) throws CustomerNotFoundException, PaymentNotFoundException;
    Optional<Payment> findPaymentsByStore(Store store) throws StoreNotFoundException, PaymentNotFoundException;
}
