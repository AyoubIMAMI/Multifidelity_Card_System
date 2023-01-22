package fr.polytech.interfaces.payment;

import fr.polytech.exceptions.CustomerNotFoundException;
import fr.polytech.exceptions.NoPaymentFoundException;
import fr.polytech.exceptions.StoreNotFoundException;
import fr.polytech.pojo.Customer;
import fr.polytech.pojo.Payment;
import fr.polytech.pojo.structure.Store;

public interface PaymentExplorer {
    Payment getPaymentByCustomer(Customer customer) throws CustomerNotFoundException, NoPaymentFoundException;
    Payment getPaymentByStore(Store store) throws StoreNotFoundException, NoPaymentFoundException;
}
