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
import fr.polytech.interfaces.catalog.StatsExplorer;
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

    private Customer mourad = new Customer("Mourad", "mourad@mail.com", "password");;
    private Customer leo = new Customer("Leo", "leo@mail.com", "password");;

    private Store polyStore;
    private Store polygone ;

    @BeforeEach
    public void init() {
        // Managing stores
        polyStore = new Store("Polystore", "101112", "password");
        polygone = new Store("Polygone", "121314", "password");
        storeRepository.deleteAll();
        polyStore = storeRepository.save(polyStore);
        polygone = storeRepository.save(polygone);

        // Managing payment
        paymentRepository.deleteAll();
    }

    @Test
    public void noPaymentInDataTest() {
        assertEquals(0, statsExplorer.getOperationCost());
    }

    @Test
    public void getAllPaymentInDataTest() {
        Set<Item> li1 = new HashSet<>();
        li1.add(new Item(2, new Discount("Table trop cool", polyStore.getId(), 0, 100)));
        Payment p1 = new Payment(mourad, polyStore, li1);
    }
}
