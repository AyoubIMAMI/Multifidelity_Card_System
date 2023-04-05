package fr.polytech.components.catalog;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.polytech.entities.Customer;
import fr.polytech.entities.Payment;
import fr.polytech.entities.Store;
import fr.polytech.entities.item.Discount;
import fr.polytech.entities.item.Item;
import fr.polytech.entities.item.Product;
import fr.polytech.interfaces.catalog.StatsExplorer;
import fr.polytech.repository.CustomerRepository;
import fr.polytech.repository.PaymentRepository;
import fr.polytech.repository.StoreRepository;


@SpringBootTest
public class StatManagerTest {
    @Autowired
    private StatsExplorer statsExplorer;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private CustomerRepository customerRepository;

    private Customer mourad;
    private Customer leo;

    private Store polyStore;
    private Store polyGone ;

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
    }

    @Test
    public void noPaymentInDataTest() {
        assertEquals(0, statsExplorer.getOperationCost());
    }

    @Test
    public void getAllPaymentInDataTest() {
        Set<Item> li1 = new HashSet<>();
        li1.add(new Item(1, new Discount("Table trop cool", polyStore.getId(), 1000)));
        li1.add(new Item(4, new Discount("Chaises du swag", polyStore.getId(), 200)));
        li1.add(new Item(4, new Product("Set de table", polyStore.getId(), 10)));
        Payment p1 = new Payment(mourad, polyStore, li1);
        paymentRepository.save(p1);
        
        assertEquals(1 * 1000 + 4 * 200, statsExplorer.getTotalPointUsed());
    }
}
