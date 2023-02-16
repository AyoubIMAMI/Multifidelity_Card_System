package fr.polytech.controllers;

import fr.polytech.exceptions.*;
import fr.polytech.exceptions.paiment.PaymentException;
import fr.polytech.interfaces.customer.CustomerExplorer;
import fr.polytech.interfaces.customer.CustomerFinder;
import fr.polytech.interfaces.customer.CustomerRegistration;
import fr.polytech.interfaces.payment.RefillFidelityCard;
import fr.polytech.pojo.BankTransaction;
import fr.polytech.pojo.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = fr.polytech.controllers.CustomerAccountController.BASE_URI, produces = APPLICATION_JSON_VALUE)
// referencing the same BASE_URI as Customer care to extend it hierarchically
public class CustomerAccountController {
    public static final String BASE_URI = "/customers";
    public static final String CUSTOMER_URI = "/{customerId}/";

    @Autowired
    private RefillFidelityCard refillFidelityCard;

    @Autowired
    private CustomerFinder customerFinder;

    @Autowired
    private CustomerRegistration customerRegistration;

    @Autowired
    private CustomerExplorer customerExplorer;

    @PostMapping(path = CUSTOMER_URI + "/refill")
    public ResponseEntity<String> refillAccount(@PathVariable("customerId") UUID customerId, @RequestBody BankTransaction transaction) throws CustomerNotFoundException, MalformedBankInformationException, PaymentException {
        Date refillTime = refillFidelityCard.refill(customerFinder.findCustomerById(customerId), transaction);
        return ResponseEntity.ok().body("Transaction ok! At: " + refillTime.toString() + " . Transaction amount: " + transaction.getAmount());
    }

    @PostMapping(path = "/registration", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> register(String name, String mail, String password) throws MailAlreadyUsedException {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(customerRegistration.register(name, mail, password));
        } catch (MailAlreadyUsedException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
    @PostMapping(path = "/login", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<UUID> login(String mail, String password) throws MailAlreadyUsedException {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(customerExplorer.checkCredentials(mail, password));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
