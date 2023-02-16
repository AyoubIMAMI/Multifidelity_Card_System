package fr.polytech.components.payment;

import fr.polytech.exceptions.CustomerNotFoundException;
import fr.polytech.exceptions.NoPaymentFoundException;
import fr.polytech.exceptions.StoreNotFoundException;
import fr.polytech.interfaces.payment.PaymentExplorer;
import fr.polytech.pojo.Customer;
import fr.polytech.pojo.Payment;
import fr.polytech.pojo.structure.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentManager implements PaymentExplorer {

    @Override
    public Payment getPaymentByCustomer(Customer customer) throws CustomerNotFoundException, NoPaymentFoundException {
        return null;
    }

    @Override
    public Payment getPaymentByStore(Store store) throws StoreNotFoundException, NoPaymentFoundException {
        return null;
    }
}
