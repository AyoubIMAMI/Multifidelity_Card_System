package fr.polytech.components.payment;

import fr.polytech.unit.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PointPurchaseHandlerTest {

    Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer();
    }

    @Test
    void givenCustomerWithPointsOnFidelityAccount_whenBuyingWithPoint_thenPointsAreModified() {
        // Given


    }

}
