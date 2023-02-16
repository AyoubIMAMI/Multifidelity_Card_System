package fr.polytech.components.payment;

import fr.polytech.components.customer.CustomerManagerTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PointPurchaseHandlerTest {

    CustomerManagerTest customer;

    @BeforeEach
    void setUp() {
        customer = new CustomerManagerTest();
    }

    @Test
    void givenCustomerWithPointsOnFidelityAccount_whenBuyingWithPoint_thenPointsAreModified() {
        // Given


    }

}
