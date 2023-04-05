package fr.polytech.cucumber.store;

import fr.polytech.components.store.StatManager;
import fr.polytech.entities.Customer;
import fr.polytech.entities.Payment;
import fr.polytech.entities.Store;
import fr.polytech.entities.item.Discount;
import fr.polytech.entities.item.Item;
import fr.polytech.entities.item.Product;
import fr.polytech.repository.CustomerRepository;
import fr.polytech.repository.PaymentRepository;
import fr.polytech.repository.StoreRepository;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class CheckingStatistics {
    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private StatManager statManager;

    @Before
    public void init() {
        System.out.println("Init");
        storeRepository.deleteAll();
        customerRepository.deleteAll();
        paymentRepository.deleteAll();
    }

    @Given("a store named {string} with Siret {string} and password {string}")
    public void a_store_with_name_siret_and_password(String storeName, String siret, String password) {
        storeRepository.save(new Store(storeName, siret, password));
    }

    @Given("a customer {string} with mail {string} and password {string}")
    public void a_client_with_name_mail_and_password(String name, String mail, String password) {
        customerRepository.save(new Customer(name, mail, password));
    }

    @When("the customer {string} purchase {string} with {int} points in the store {string}")
    public void theCustomerPurchaseWithPointsInTheStore(String customerName, String discountName, int point, String storeName) {
        Store store = storeRepository.findStoreByName(storeName).get();
        Customer customer = customerRepository.findCustomerByName(customerName).get();
        Set<Item> shoppingList = new HashSet<>();
        shoppingList.add(new Item(1, new Discount(discountName, store.getId(), point)));
        paymentRepository.save(new Payment(customer, store, shoppingList));

        System.out.println("Hein ? " +  paymentRepository.findAll());
    }

    @And("the customer {string} purchase {string} with {int} euros in the store {string}")
    public void theCustomerPurchaseWithEurosInTheStore(String customerName, String productName, int cash, String storeName) {
        Store store = storeRepository.findStoreByName(storeName).get();
        Customer customer = customerRepository.findCustomerByName(customerName).get();
        Set<Item> shoppingList = new HashSet<>();
        shoppingList.add(new Item(1, new Product(productName, store.getId(), cash)));
        paymentRepository.save(new Payment(customer, store, shoppingList));

        System.out.println("Hein ? " +  paymentRepository.findAll());
    }

    @Then("the total point used was {int} since the beginning")
    public void theTotalPointUsedWasSinceTheBeginning(int usedPoints) {
        
        assertEquals(usedPoints, statManager.getTotalPointUsed());
    }
}
