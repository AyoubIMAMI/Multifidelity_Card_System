package fr.polytech.interfaces.payment;

import fr.polytech.exceptions.CustomerNotFoundException;
import fr.polytech.exceptions.NoPaymentFoundException;
import fr.polytech.exceptions.StoreNotFoundException;
import fr.polytech.entities.Customer;
import fr.polytech.entities.Payment;
import fr.polytech.entities.structure.Store;

public interface PaymentExplorer {
    Payment getPaymentByCustomer(Customer customer) throws CustomerNotFoundException, NoPaymentFoundException;
    Payment getPaymentByStore(Store store) throws StoreNotFoundException, NoPaymentFoundException;
}
