package fr.polytech.interfaces.payment;

import fr.polytech.entities.Customer;
import fr.polytech.entities.Payment;
import fr.polytech.exceptions.CustomerNotFoundException;
import fr.polytech.exceptions.store.StoreNotFoundException;
import fr.polytech.exceptions.payment.PaymentNotFoundException;

import fr.polytech.entities.Store;

import java.util.Date;
import java.util.List;


public interface PaymentExplorer {


    boolean customerReached10Payments(Customer customer);

    List<Payment> findAllPayments();

    List<Payment> findAllByTransactionDateAfter(Date date);
}
