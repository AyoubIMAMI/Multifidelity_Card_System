package fr.polytech.components.payment;

import fr.polytech.exceptions.payment.PaymentAlreadyExistsException;
import fr.polytech.exceptions.payment.PaymentNotFoundException;
import fr.polytech.interfaces.payment.PaymentExplorer;
import fr.polytech.interfaces.payment.PaymentModifier;
import fr.polytech.pojo.Customer;
import fr.polytech.pojo.Payment;
import fr.polytech.pojo.structure.Store;
import fr.polytech.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class PaymentManager implements PaymentExplorer, PaymentModifier {

    PaymentRepository paymentRepository;

    @Autowired
    public PaymentManager(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment findPaymentById(UUID id) throws PaymentNotFoundException {
        return paymentRepository.findById(id).orElseThrow(PaymentNotFoundException::new);
    }

    @Override
    public Iterable<Payment> findPaymentsByCustomer(Customer customer) throws PaymentNotFoundException {
        Iterable<Payment> payments = paymentRepository.findByCustomer(customer);
        if (!payments.iterator().hasNext()) {
            throw new PaymentNotFoundException();
        }
        return payments;
    }

    @Override
    public Iterable<Payment> findPaymentsByStore(Store store) throws PaymentNotFoundException {
        Iterable<Payment> payments = paymentRepository.findByStore(store);
        if (!payments.iterator().hasNext()) {
            throw new PaymentNotFoundException();
        }
        return payments;
    }

    @Override
    public void savePayment(Payment payment) throws PaymentAlreadyExistsException {
        UUID paymentID = payment.getId();
        if (paymentRepository.existsById(paymentID)) {
            throw new PaymentAlreadyExistsException();
        }
        paymentRepository.save(paymentID, payment);
    }
}
