package fr.polytech.controllers;

import fr.polytech.controllers.dto.CustomerDTO;
import fr.polytech.controllers.dto.DiscountDTO;
import fr.polytech.controllers.dto.PaymentDTO;
import fr.polytech.exceptions.*;
import fr.polytech.exceptions.payment.NegativeAmountException;
import fr.polytech.exceptions.payment.PaymentException;
import fr.polytech.interfaces.customer.CustomerExplorer;
import fr.polytech.interfaces.customer.CustomerFinder;
import fr.polytech.interfaces.customer.CustomerRegistration;
import fr.polytech.interfaces.fidelity.FidelityExplorer;
import fr.polytech.interfaces.payment.RefillFidelityCard;
import fr.polytech.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = fr.polytech.controllers.CustomerAccountController.BASE_URI, produces = APPLICATION_JSON_VALUE)
// referencing the same BASE_URI as Customer care to extend it hierarchically
public class CustomerAccountController {
    public static final String BASE_URI = "/customers";
    public static final String CUSTOMER_URI = "/{customerId}/";
    private final RefillFidelityCard refillFidelityCard;
    private final CustomerRegistration customerRegistration;
    private final CustomerExplorer customerExplorer;
    private final CustomerFinder customerFinder;
    private final FidelityExplorer fidelityExplorer;

    @Autowired
    public CustomerAccountController(RefillFidelityCard refillFidelityCard,CustomerFinder customerFinder, FidelityExplorer fidelityExplorer, CustomerRegistration customerRegistration, CustomerExplorer customerExplorer) {
        this.refillFidelityCard = refillFidelityCard;
        this.customerRegistration = customerRegistration;
        this.customerExplorer = customerExplorer;
        this.fidelityExplorer = fidelityExplorer;
        this.customerFinder=customerFinder;
    }

    @PostMapping(path = CUSTOMER_URI + "/refill")
    public ResponseEntity<String> refillAccount(@PathVariable("customerId") Long customerId, @RequestBody PaymentDTO transaction) throws CustomerNotFoundException, NegativeAmountException, PaymentException, FidelityAccountNotFoundException, NegativeAmountException, PaymentException {
        Date refillTime = refillFidelityCard.refill(customerFinder.findCustomerById(customerId), transaction);
        return ResponseEntity.ok().body("Transaction ok! At: " + refillTime.toString() + " . Transaction amount: " + transaction.getAmount());
    }

    @PostMapping(path = "/registration", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> register(@RequestBody @Valid CustomerDTO customerDTO) throws MailAlreadyUsedException {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(customerRegistration.register(customerDTO.getName(), customerDTO.getEmail(), customerDTO.getPassword()));
        } catch (MailAlreadyUsedException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
    @PostMapping(path = "/login", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> login(String mail, String password) throws MailAlreadyUsedException {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(customerExplorer.checkCredentials(mail, password));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
