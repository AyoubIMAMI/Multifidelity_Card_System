package fr.polytech.components.store;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import fr.polytech.entities.Customer;
import fr.polytech.entities.Payment;
import fr.polytech.entities.Store;
import fr.polytech.entities.item.Discount;
import fr.polytech.entities.item.Item;
import fr.polytech.entities.item.Product;
import fr.polytech.exceptions.IllegalDateException;
import fr.polytech.interfaces.store.StatsExplorer;
import fr.polytech.repository.CustomerRepository;
import fr.polytech.repository.DiscountRepository;
import fr.polytech.repository.PaymentRepository;
import fr.polytech.repository.ProductRepository;
import fr.polytech.repository.StoreRepository;

@SpringBootTest
@Transactional
public class StatManagerTest {
    @Autowired
    private StatsExplorer statsExplorer;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DiscountRepository discountRepository;

    @Autowired
    private ProductRepository productRepository;

    private Customer mourad;
    private Customer leo;

    private Store polyStore;
    private Store polyGone ;

    private Set<Item> li1;
    private Set<Item> li2;

    @BeforeEach
    public void init() {
        // Managing stores
        storeRepository.deleteAll();
        polyStore = storeRepository.save(new Store("Polystore", "101112", "password"));
        polyGone = storeRepository.save(new Store("Polygone", "121314", "password"));

        // Managing payment
        paymentRepository.deleteAll();

        // Managing customers
        mourad = customerRepository.save(new Customer("Mourad", "mourad@mail.com", "password"));
        leo = customerRepository.save(new Customer("Leo", "leo@mail.com", "password"));

        // Setting up sets
        li1 = new HashSet<>();
        li1.add(new Item(1, discountRepository.save(new Discount("Table trop cool", polyStore.getId(), 1000))));
        li1.add(new Item(4, discountRepository.save(new Discount("Chaises du swag", polyStore.getId(), 200))));
        li1.add(new Item(4, productRepository.save(new Product("Set de table", polyStore.getId(), 10))));

        li2 = new HashSet<>();
        li2.add(new Item(1, discountRepository.save(new Discount("Bandana jaune", polyGone.getId(), 50))));
        li2.add(new Item(1, productRepository.save(new Product("Television 4k oled", polyGone.getId(), 1320))));
        li2.add(new Item(1, discountRepository.save(new Discount("Table", polyGone.getId(), 500))));
    }

    @Test
    public void noPaymentInDataTest() {
        assertEquals(0, statsExplorer.getOperationCost());
    }

    @Test
    public void getAllPaymentInDataTest() {
        paymentRepository.save(new Payment(mourad, polyStore, li1));
        paymentRepository.save(new Payment(leo, polyGone, li2));
    
        assertEquals(1000 + 4 * 200 + 50 + 500, statsExplorer.getUsedPoints());
        assertEquals((1000 + 4 * 200 + 50 + 500) / (double) 10, statsExplorer.getOperationCost());
    }

    @Test
    public void getPaymentAfterDate() throws IllegalDateException {
        // This payment is from 01/01/2022
        Payment p1 = new Payment(mourad, polyStore, li1);
        Calendar transactionDate = Calendar.getInstance();
        transactionDate.set(Calendar.YEAR, 2022);
        transactionDate.set(Calendar.MONTH, 0);
        transactionDate.set(Calendar.DATE, 1);
        p1.setTransactionDate(transactionDate.getTime());
        paymentRepository.save(p1);

        // This payment is from now
        paymentRepository.save(new Payment(leo, polyGone, li2));

        // Threshold is set to 01/01/2023
        Calendar thresholdDate = Calendar.getInstance();
        thresholdDate.set(Calendar.YEAR, 2023);
        thresholdDate.set(Calendar.MONTH, 0);
        thresholdDate.set(Calendar.DATE, 1);

        assertEquals(500 + 50, statsExplorer.getUsedPoints(thresholdDate.getTime()));
        assertEquals((500 + 50) / (double) 10, statsExplorer.getOperationCost(thresholdDate.getTime()));
    }
}
