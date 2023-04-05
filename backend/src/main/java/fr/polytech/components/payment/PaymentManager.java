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
    public Iterable<Payment> findPaymentsByCustomer(Customer customer) throws PaymentNotFoundException {
        Iterable<Payment> payments = paymentRepository.findAllByCustomer(customer);
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
    public Payment savePayment(Payment payment) throws PaymentAlreadyExistsException {
        Long paymentID = payment.getId();
        System.out.println("Payment a save :" + payment);
        System.out.println("Id du payment a check : " + paymentID);

        if(paymentAlreadyExists(paymentID)) {
            System.out.println("Ce payment existe deja");
            throw new PaymentAlreadyExistsException(paymentID);
        }

        System.out.println("Aucun payment avec cet id dans la DB donc on peut le save");
        Payment newPayment = paymentRepository.save(payment);
        System.out.println("New payment created with id : " + newPayment);
        return newPayment;
    }

    private boolean paymentAlreadyExists(Long paymentID) {
        System.out.println("On check");
        if (paymentID == null) return false;
        return paymentRepository.findById(paymentID).isPresent();
    }
}
