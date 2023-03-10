package fr.polytech.components.payment;

import fr.polytech.entities.Customer;
import fr.polytech.entities.Payment;
import fr.polytech.exceptions.payment.PaymentAlreadyExistsException;
import fr.polytech.exceptions.payment.PaymentNotFoundException;
import fr.polytech.interfaces.payment.PaymentExplorer;
import fr.polytech.interfaces.payment.PaymentModifier;
import fr.polytech.entities.Store;
import fr.polytech.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.Optional;

@Component
public class PaymentManager implements PaymentExplorer, PaymentModifier {

    PaymentRepository paymentRepository;

    @Autowired
    public PaymentManager(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment findPaymentById(Long id) throws PaymentNotFoundException {
        return paymentRepository.findById(id).orElseThrow(PaymentNotFoundException::new);
    }

    @Override
    public Optional<Payment> findPaymentsByCustomer(Customer customer) throws PaymentNotFoundException {
        Optional<Payment> payments = paymentRepository.findByCustomer(customer);
        if (payments.isEmpty()) {
            throw new PaymentNotFoundException();
        }
        return payments;
    }

    @Override
    public Optional<Payment> findPaymentsByStore(Store store) throws PaymentNotFoundException {
        Optional<Payment> payments = paymentRepository.findByStore(store);
        if (payments.isEmpty()) {
            throw new PaymentNotFoundException();
        }
        return payments;
    }

    @Override
    public void savePayment(Payment payment) throws PaymentAlreadyExistsException {
        Long paymentID = payment.getId();
        if (paymentRepository.existsById(paymentID)) {
            throw new PaymentAlreadyExistsException();
        }
        paymentRepository.save(payment);
    }
}
