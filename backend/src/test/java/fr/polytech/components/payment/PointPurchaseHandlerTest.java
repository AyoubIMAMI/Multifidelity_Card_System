package fr.polytech.components.payment;

import fr.polytech.exceptions.discount.NoDiscountsFoundException;
import fr.polytech.interfaces.payment.PointPurchase;
import fr.polytech.pojo.Customer;
import fr.polytech.pojo.Payment;
import fr.polytech.pojo.item.Discount;
import fr.polytech.pojo.item.Item;
import fr.polytech.pojo.item.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PointPurchaseHandlerTest {

    @Autowired
    private PointPurchase pointPurchaseHandler;

    Customer customer;
    Payment payment;

    Product product;
    Discount discountedProduct;

    int initialFidelityPoint;

    @BeforeEach
    void setUp() {
        customer = new Customer("John", "john@doe.com", "pwd");
        payment = new Payment();
        product = new Product("Coffee", 5);
        discountedProduct = new Discount("Cake", 10, 7);
        initialFidelityPoint = 100;
    }

    @Test
    void givenCustomerWithPointsOnFidelityAccountAndPaymentWithDiscountItems_whenBuyingWithPoint_thenPointsAreModified() {
        // Given
        customer.getFidelityAccount().setPoints(initialFidelityPoint);

        Set<Item> shoppingList = new HashSet<>();
        Item firstItem = new Item(2, product);
        int discountItemQuantity = 3;
        Item secondItem = new Item(discountItemQuantity, discountedProduct);
        shoppingList.add(firstItem);
        shoppingList.add(secondItem);

        payment.setShoppingList(shoppingList);

        // When
        assertDoesNotThrow(() -> pointPurchaseHandler.buyWithPoint(customer, payment));

        // Then
        int requiredPoints = discountItemQuantity * discountedProduct.getPointPrice();

        assertEquals(initialFidelityPoint-requiredPoints,customer.getFidelityAccount().getPoints());
    }
}
