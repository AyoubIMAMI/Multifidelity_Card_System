package fr.polytech.components.payment;

import fr.polytech.entities.Customer;
import fr.polytech.entities.Payment;
import fr.polytech.exceptions.payment.PaymentAlreadyExistsException;
import fr.polytech.exceptions.payment.PaymentNotFoundException;
import fr.polytech.interfaces.payment.PaymentExplorer;
import fr.polytech.interfaces.payment.PaymentModifier;
import fr.polytech.entities.Store;
import fr.polytech.entities.item.Discount;
import fr.polytech.entities.item.Item;
import fr.polytech.entities.item.Product;
import fr.polytech.repository.DiscountRepository;
import fr.polytech.repository.PaymentRepository;
import fr.polytech.repository.ProductRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

@Component
public class PaymentManager implements PaymentExplorer, PaymentModifier {

    private final PaymentRepository paymentRepository;
    private final ProductRepository productRepository;
    private final DiscountRepository discountRepository;

    @Autowired
    public PaymentManager(PaymentRepository paymentRepository, ProductRepository productRepository, DiscountRepository discountRepository) {
        this.paymentRepository = paymentRepository;
        this.productRepository = productRepository;
        this.discountRepository = discountRepository;
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
    public boolean customerReached10Payments(Customer customer) {
        return(paymentRepository.findAllByCustomer(customer).size()==10);
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
        saveNewProduct(payment.getShoppingList());

        Payment newPayment = paymentRepository.save(payment);
        System.out.println("New payment created with id : " + newPayment);
        return newPayment;
    }

    private boolean paymentAlreadyExists(Long paymentID) {
        System.out.println("On check");
        if (paymentID == null) return false;
        return paymentRepository.findById(paymentID).isPresent();
    }

    /**
     * For a given set of items, check if the Products are registry, if no : save them. 
     * @param items The given set of item to check
     */
    private void saveNewProduct(Set<Item> items) {
        for(Item item : items)
            if(item.getBuyable() instanceof Product product){
                Optional<Product> productOptional=productRepository.findByNameAndStoreIdAndCashPrice(product.getName(),product.getStoreId(),product.getCashPrice());
                if(productOptional.isEmpty()) // Check if the product is already in registry
                {
                    item.setBuyable(productRepository.save(product));
                }
                else{
                    item.setBuyable(productOptional.get());
                }
            } else if (item.getBuyable() instanceof Discount discount) {
                if(!discountRepository.exists(Example.of(discount))) // Check if the discount is already in registry
                    discountRepository.save(discount);
            }
    }

    @Override
    public List<Payment> findAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public List<Payment> findAllByTransactionDateAfter(Date date) {
        return paymentRepository.findAllByTransactionDateAfter(date);
    }
}
