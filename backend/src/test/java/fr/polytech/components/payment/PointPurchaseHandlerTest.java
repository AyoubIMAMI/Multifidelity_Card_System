package fr.polytech.components.payment;

import fr.polytech.entities.Customer;
import fr.polytech.entities.Payment;
import fr.polytech.entities.item.Discount;
import fr.polytech.entities.item.Product;
import fr.polytech.exceptions.NotEnoughBalanceException;
import fr.polytech.exceptions.discount.NoDiscountsFoundException;
import fr.polytech.interfaces.payment.PointPurchase;
import fr.polytech.entities.item.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
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
        product = new Product("Coffee", Long.parseLong("0"), 5);
        discountedProduct = new Discount("Cake", Long.parseLong("1"), 7);
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
        Executable pay = () -> pointPurchaseHandler.buyWithPoint(customer, shoppingList);
        assertDoesNotThrow(pay);

        // Then
        int requiredPoints = discountItemQuantity * discountedProduct.getPointPrice();

        assertEquals(initialFidelityPoint-requiredPoints,customer.getFidelityAccount().getPoints());
    }

    @Test
    void givenCustomerWithNoPointsOnFidelityAccount_whenBuyingWithPoint_thenShouldThrowsNotEnoughBalanceException() {
        // Given
        customer.getFidelityAccount().setPoints(0);

        Set<Item> shoppingList = new HashSet<>();
        Item item = new Item(1, discountedProduct);
        shoppingList.add(item);

        payment.setShoppingList(shoppingList);

        // When
        Executable pay = () -> pointPurchaseHandler.buyWithPoint(customer, shoppingList);
        assertThrows(NotEnoughBalanceException.class, pay);
    }

    @Test
    void givenCustomerWithPointsOnFidelityAccountButPaymentWithoutDiscountedItems_thenShouldThrowsNoDiscountsFoundException() {
        // Given
        customer.getFidelityAccount().setPoints(100);

        Set<Item> shoppingList = new HashSet<>();
        Item item = new Item(1, product);
        shoppingList.add(item);

        payment.setShoppingList(shoppingList);

        // When
        Executable pay = () -> pointPurchaseHandler.buyWithPoint(customer, shoppingList);
        assertThrows(NoDiscountsFoundException.class, pay);
    }
}
