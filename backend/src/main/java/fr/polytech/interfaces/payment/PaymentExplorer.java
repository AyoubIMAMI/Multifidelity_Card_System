package fr.polytech.interfaces.payment;

import fr.polytech.entities.Customer;
import fr.polytech.entities.Payment;
import java.util.Date;
import java.util.List;


public interface PaymentExplorer {

    /**
     * Verify if the customer reached 10 Payments
     * @param customer involved
     * @return true if the customer reached 10 Payments
     */
    boolean customerReached10Payments(Customer customer);

    /**
     * Find all Payments
     * @return Payments list
     */
    List<Payment> findAllPayments();

    /**
     * Find all the transactions since a Date
     * @param date after which the transactions are found
     * @return Payments list
     */
    List<Payment> findAllByTransactionDateAfter(Date date);
}
