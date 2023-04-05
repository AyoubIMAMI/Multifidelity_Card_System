package fr.polytech.cucumber.payment;

import fr.polytech.connectors.externaldto.BankTransactionDTO;
import fr.polytech.entities.Customer;
import fr.polytech.entities.Payment;
import fr.polytech.entities.Store;
import fr.polytech.entities.item.Discount;
import fr.polytech.entities.item.Item;
import fr.polytech.entities.item.Product;
import fr.polytech.exceptions.BadCredentialsException;
import fr.polytech.exceptions.CustomerNotFoundException;
import fr.polytech.exceptions.NotEnoughBalanceException;
import fr.polytech.exceptions.PurchaseFailedException;
import fr.polytech.exceptions.discount.NoDiscountsFoundException;
import fr.polytech.exceptions.payment.NegativeAmountException;
import fr.polytech.exceptions.payment.PaymentAlreadyExistsException;
import fr.polytech.exceptions.payment.PaymentInBankException;
import fr.polytech.exceptions.store.StoreNotFoundException;
import fr.polytech.interfaces.payment.IPayment;
import fr.polytech.interfaces.payment.RefillFidelityCard;
import fr.polytech.repository.CustomerRepository;
import fr.polytech.repository.PaymentRepository;
import fr.polytech.repository.StoreRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PaymentDef {
    Store store;
    Customer customer;

    Set<Item> shoppingList;
    @Autowired
    StoreRepository storeRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    IPayment payment;
    @Autowired
    RefillFidelityCard refillFidelityCard;

    @Autowired
    PaymentRepository paymentRepository;


    @Given("a user")
    public void aUser() {
        customerRepository.deleteAll();
        String name = "Pierre";
        String mail = "pierre@mail.com";
        String password = "myPassword";
        customerRepository.save(new Customer(name,mail,password));
        customer=customerRepository.findCustomerByName(name).get();
    }

    @And("a store")
    public void aStore() {
        storeRepository.deleteAll();
        String name = "PierreJardinage";
        String password = "myPassword";
        String siret="numSiret";
        storeRepository.save(new Store(name, siret, password));
        store=storeRepository.findStoreByName(name).get();
    }

    @When("he want to buy items")
    public void heWantToBuyItems() {
        shoppingList=new HashSet<>();
        shoppingList.add(new Item(2,new Product("Coffee", store.getId(), 5)));
        shoppingList.add(new Item(2,new Discount("Cake", store.getId(),  10, 7)));
    }

    @And("he has enough cash")
    public void heHasEnoughCash() throws NegativeAmountException, PaymentInBankException {
        refillFidelityCard.refill(customer,new BankTransactionDTO("896983", 100));
    }

    @And("he pay")
    public void hePay() throws PaymentAlreadyExistsException, NoDiscountsFoundException, BadCredentialsException, CustomerNotFoundException, NotEnoughBalanceException, PurchaseFailedException, StoreNotFoundException {
        payment.payWithFidelity(customer.getId(),store.getId(),shoppingList);
    }

    @Then("the payment works")
    public void thePaymentWorks() {
        ArrayList<Payment> payments=new ArrayList<>();
        paymentRepository.findAllByCustomer(customer).forEach(pay->payments.add(pay));
        assertEquals(0,payments.size());
    }
}
