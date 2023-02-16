package fr.polytech.components.payment;

import fr.polytech.interfaces.payment.PointPurchase;
import fr.polytech.pojo.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PointPurchaseHandlerTest {

    Customer customer;

    @Autowired
    private PointPurchase pointPurchaseHandler;

    @BeforeEach
    void setUp() {
        customer = new Customer("John", "john@doe.com", "pwd");
    }

    @Test
    void givenCustomerWithPointsOnFidelityAccount_whenBuyingWithPoint_thenPointsAreModified() {
        // Given
        customer.getFidelityAccount().setPoints(100);


    }

}
