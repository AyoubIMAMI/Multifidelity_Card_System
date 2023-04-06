package fr.polytech.cucumber.payment;

import fr.polytech.components.discount.DiscountManager;
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
import fr.polytech.exceptions.discount.NoDiscountsFoundException;
import fr.polytech.exceptions.payment.NegativeAmountException;
import fr.polytech.exceptions.payment.PaymentAlreadyExistsException;
import fr.polytech.exceptions.payment.PaymentInBankException;
import fr.polytech.exceptions.store.StoreNotFoundException;
import fr.polytech.interfaces.fidelity.PointModifier;
import fr.polytech.interfaces.payment.Bank;
import fr.polytech.interfaces.payment.IPayment;
import fr.polytech.interfaces.payment.RefillFidelityCard;
import fr.polytech.repository.CustomerRepository;
import fr.polytech.repository.PaymentRepository;
import fr.polytech.repository.StoreRepository;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PaymentDef {
    Exception catchedExeption;
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
    private Bank bankMock;
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    PointModifier pointModifier;
    @Autowired
    DiscountManager discountManager;

    @Before
    public void aWorkingBank() throws Exception {
        customerRepository.deleteAll();
        // Mocking the bank proxy
        when(bankMock.refill(any(BankTransactionDTO.class))).thenAnswer((Answer<Boolean>) invocation -> {
            return true;
        });

    }


    @Given("a user")
    public void aUser() {
        customerRepository.deleteAll();
        String name = "Pierre";
        String mail = "pierre@mail.com";
        String password = "myPassword";
        customer =customerRepository.save(new Customer(name,mail,password));
        shoppingList=new HashSet<>();
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

    @And("he has enough cash")
    public void heHasEnoughCash() throws NegativeAmountException, PaymentInBankException {
        refillFidelityCard.refill(customer,new BankTransactionDTO("896983", 100));
    }

    @And("he pay")
    public void hePay() throws PaymentAlreadyExistsException, NoDiscountsFoundException, BadCredentialsException, CustomerNotFoundException, NotEnoughBalanceException,  StoreNotFoundException {
        pointModifier.incrementPoints(customer, 1000);

        try{
            payment.payWithFidelity(customer.getId(),store.getId(),shoppingList);
        }catch(Exception e){
            catchedExeption = e;
        }
    }

    @Then("the payment works")
    public void thePaymentWorks() {
        ArrayList<Payment> payments=new ArrayList<>();
        paymentRepository.findAllByCustomer(customer).forEach(pay->payments.add(pay));
        assertEquals(1,payments.size());
    }

    @When("he want to buy an item names {string}, it cost {int}, in quantity {int}")
    public void heWantToBuyAnItemNamesItCostInQuantity(String name, int price, int quantity) {
        shoppingList.add(new Item(quantity,new Product(name, store.getId(), price)));
    }

    @When("he want to buy an discount names {string}, it cost {int} and {int} points, in quantity {int}")
    public void heWantToBuyAnDiscountNamesItCostInQuantity(String name, int price, int points, int quantity) throws NegativeAmountException {
        Discount discount = discountManager.createDiscount(name,store.getId(),points);
        shoppingList.add(new Item(quantity,discount));
    }

    @And("he has not enough cash")
    public void heHasNotEnoughCash() throws NegativeAmountException, PaymentInBankException {
        refillFidelityCard.refill(customer,new BankTransactionDTO("896983", 1));
    }

    @Then("the payment doesn't work")
    public void thePaymentDoesnTWork() {
        assertTrue(catchedExeption instanceof NotEnoughBalanceException);
        ArrayList<Payment> payments=new ArrayList<Payment>();
        paymentRepository.findAllByCustomer(customer).forEach(pay->payments.add(pay));
        assertEquals(0,payments.size());
    }

    @And("a empty fidelity card")
    public void aEmptyFidelityCard() {
        customer.getFidelityAccount().setBalance(0);
        customerRepository.save(customer);
    }

    @When("the user refill his account with {int} with the credit-card {string}")
    public void theUserRefillHisAccountWith(int amount, String creditCard) throws PaymentInBankException {
        try{
            refillFidelityCard.refill(customer, new BankTransactionDTO(creditCard, amount));
        }catch (NegativeAmountException e){
            catchedExeption = e;
        }
    }

    @Then("the fidelity card contain {int}")
    public void theFidelityCardContain(int amount) {
        double customerAmount = -1;
        Optional<Customer> optionalCustomer = customerRepository.findCustomerByName(customer.getName());
        if(optionalCustomer.isPresent()){
            Customer c = optionalCustomer.get();
            customerAmount = c.getFidelityAccount().getBalance();
        }
        assertEquals(amount, customerAmount);
    }

    @Then("the system has refuse the payment")
    public void theSystemHasRefuseThePayment() {
        assertTrue(catchedExeption instanceof NegativeAmountException);
        double customerAmount = -1;
        Optional<Customer> optionalCustomer = customerRepository.findCustomerByName(customer.getName());
        if(optionalCustomer.isPresent()){
            Customer c = optionalCustomer.get();
            customerAmount = c.getFidelityAccount().getBalance();
        }
        assertEquals(0, customerAmount);
    }
}
