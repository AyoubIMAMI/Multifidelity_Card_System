package fr.polytech.cucumber.store;

import fr.polytech.components.store.StatManager;
import fr.polytech.entities.Customer;
import fr.polytech.entities.Payment;
import fr.polytech.entities.Store;
import fr.polytech.entities.item.Discount;
import fr.polytech.entities.item.Item;
import fr.polytech.entities.item.Product;
import fr.polytech.exceptions.IllegalDateException;
import fr.polytech.repository.CustomerRepository;
import fr.polytech.repository.DiscountRepository;
import fr.polytech.repository.PaymentRepository;
import fr.polytech.repository.ProductRepository;
import fr.polytech.repository.StoreRepository;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
public class CheckingStatistics {
    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private StatManager statManager;

    @Autowired
    private DiscountRepository discountRepository;

    @Autowired
    private ProductRepository productRepository;

    @Before
    public void init() {
        storeRepository.deleteAll();
        customerRepository.deleteAll();
        paymentRepository.deleteAll();
        discountRepository.deleteAll();
    }

    @Given("a store named {string} with Siret {string} and password {string}")
    public void a_store_with_name_siret_and_password(String storeName, String siret, String password) {
        storeRepository.save(new Store(storeName, siret, password));
    }

    @Given("a customer {string} with mail {string} and password {string}")
    public void a_client_with_name_mail_and_password(String name, String mail, String password) {
        customerRepository.save(new Customer(name, mail, password));
    }

    @When("the customer {string} purchase {string} with {int} points in the store {string} in {int}")
    public void theCustomerPurchaseWithPointsInTheStore(String customerName, String discountName, int point, String storeName, int year) {
        Store store = storeRepository.findStoreByName(storeName).get();
        Customer customer = customerRepository.findCustomerByName(customerName).get();

        Set<Item> shoppingList = new HashSet<>();
        Discount discount = discountRepository.save(new Discount(discountName, store.getId(), point));
        shoppingList.add(new Item(1, discount));

        Payment payment = new Payment(customer, store, shoppingList);
        Calendar transactionDate = Calendar.getInstance();
        transactionDate.set(Calendar.YEAR, year);
        transactionDate.set(Calendar.MONTH, 0);
        transactionDate.set(Calendar.DATE, 1);
        payment.setTransactionDate(transactionDate.getTime());

        paymentRepository.save(payment);
    }

    @And("the customer {string} purchase {string} with {int} euros in the store {string} in {int}")
    public void theCustomerPurchaseWithEurosInTheStore(String customerName, String productName, int cash, String storeName, int year) {
        Store store = storeRepository.findStoreByName(storeName).get();
        Customer customer = customerRepository.findCustomerByName(customerName).get();
        Set<Item> shoppingList = new HashSet<>();
        Product product = productRepository.save(new Product(productName, store.getId(), cash));
        shoppingList.add(new Item(1, product));

        Payment payment = new Payment(customer, store, shoppingList);
        Calendar transactionDate = Calendar.getInstance();
        transactionDate.set(Calendar.YEAR, year);
        transactionDate.set(Calendar.MONTH, 0);
        transactionDate.set(Calendar.DATE, 1);
        payment.setTransactionDate(transactionDate.getTime());

        paymentRepository.save(payment);
    }

    @Then("the total point used was {int} since the beginning")
    public void theTotalPointUsedWasSinceTheBeginning(int usedPoints) {
        assertEquals(usedPoints, statManager.getUsedPoints());
    }

    @Then("the total cost of the operation is up to {double} euros since the beginning")
    public void theTotalCostOfTheOperationIsUpToEurosSinceTheBeginning(double amount) {
        assertEquals(amount, statManager.getOperationCost());
    }

    @Then("the total point used was {int} since {int}")
    public void theTotalPointUsedWasSince(int points, int year) throws IllegalDateException {
        Calendar thresholdDate = Calendar.getInstance();
        thresholdDate.set(Calendar.YEAR, year);
        thresholdDate.set(Calendar.MONTH, 0);
        thresholdDate.set(Calendar.DATE, 1);

        assertEquals(points, statManager.getUsedPoints(thresholdDate.getTime()));
    }

    @Then("the total cost of the operation is up to {double} euros since the {int}")
    public void theTotalCostOfTheOperationIsUpToEurosSinceThe(double amount, int year) throws IllegalDateException {
        Calendar thresholdDate = Calendar.getInstance();
        thresholdDate.set(Calendar.YEAR, year);
        thresholdDate.set(Calendar.MONTH, 0);
        thresholdDate.set(Calendar.DATE, 1);

        assertEquals(amount, statManager.getOperationCost(thresholdDate.getTime()));
    }

    @Then("the user can not query the total cost of the operation from {int} \\(IllegalDateException)")
    public void theUserCanNotQueryTheTotalCostOfTheOperationFromIllegalDateException(int year) {
        Calendar thresholdDate = Calendar.getInstance();
        thresholdDate.set(Calendar.YEAR, year);
        thresholdDate.set(Calendar.MONTH, 0);
        thresholdDate.set(Calendar.DATE, 1);

        assertThrows(IllegalDateException.class ,() -> statManager.getOperationCost(thresholdDate.getTime()));
    }
}
