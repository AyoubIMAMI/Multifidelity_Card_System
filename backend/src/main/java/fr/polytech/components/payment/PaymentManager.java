package fr.polytech.components.payment;

import fr.polytech.exceptions.CustomerNotFoundException;
import fr.polytech.exceptions.NoPaymentFoundException;
import fr.polytech.exceptions.PaymentAlreadyExistsException;
import fr.polytech.exceptions.StoreNotFoundException;
import fr.polytech.interfaces.payment.PaymentExplorer;
import fr.polytech.interfaces.payment.PaymentModifier;
import fr.polytech.entities.Customer;
import fr.polytech.entities.Payment;
import fr.polytech.entities.structure.Store;
import org.springframework.stereotype.Component;

@Component
public class PaymentManager implements PaymentExplorer, PaymentModifier {

    @Override
    public Payment getPaymentByCustomer(Customer customer) throws CustomerNotFoundException, NoPaymentFoundException {
        return null;
    }

    @Override
    public Payment getPaymentByStore(Store store) throws StoreNotFoundException, NoPaymentFoundException {
        return null;
    }

    @Override
    public void savePayment(Payment payment) throws PaymentAlreadyExistsException {
    }
}
