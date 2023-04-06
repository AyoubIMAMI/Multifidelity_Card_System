package fr.polytech.controllers;

import fr.polytech.controllers.dto.CustomerDTO;
import fr.polytech.connectors.externaldto.BankTransactionDTO;
import fr.polytech.exceptions.*;
import fr.polytech.exceptions.payment.NegativeAmountException;
import fr.polytech.exceptions.payment.PaymentInBankException;
import fr.polytech.interfaces.customer.CustomerExplorer;
import fr.polytech.interfaces.customer.CustomerFinder;
import fr.polytech.interfaces.customer.CustomerRegistration;
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

    @Autowired
    public CustomerAccountController(RefillFidelityCard refillFidelityCard,CustomerFinder customerFinder, CustomerRegistration customerRegistration, CustomerExplorer customerExplorer) {
        this.refillFidelityCard = refillFidelityCard;
        this.customerRegistration = customerRegistration;
        this.customerExplorer = customerExplorer;
        this.customerFinder = customerFinder;
    }

    @PostMapping(path = "/refill/{customerId}")
    public ResponseEntity<String> refillAccount(@PathVariable("customerId") Long customerId, @RequestBody BankTransactionDTO transaction) throws CustomerNotFoundException, NegativeAmountException, PaymentInBankException {
        Customer customer = customerFinder.findCustomerById(customerId);
        Date refillTime = refillFidelityCard.refill(customer, transaction);
        return ResponseEntity.ok().body("Transaction ok! At: " + refillTime.toString() + ". Transaction amount: " + transaction.getAmount());
    }

    @PostMapping(path = "/plate/{customerId}")
    public ResponseEntity<String> setLicensePlate(@PathVariable("customerId") Long customerId, @RequestBody String licensePlate) throws CustomerNotFoundException {
        Customer customer=customerRegistration.registerNewPlate(customerId,licensePlate);
        return ResponseEntity.ok().body("New license plates saved: "+customer.getFidelityAccount().getLicencePlate());
    }

    @PostMapping(path = "/registration", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDTO> register(@RequestBody @Valid CustomerDTO customerDTO) throws MailAlreadyUsedException {
        return ResponseEntity.status(HttpStatus.CREATED)
                    .body(convertCustomerToDto(customerRegistration.register(customerDTO.getName(), customerDTO.getEmail(), customerDTO.getPassword())));
    }

    @PostMapping(path = "/login", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> login(@RequestBody @Valid CustomerDTO customerDTO) throws BadCredentialsException {
        return ResponseEntity.status(HttpStatus.OK)
                    .body(customerExplorer.checkCredentials(customerDTO.getEmail(), customerDTO.getPassword()));
    }

    private CustomerDTO convertCustomerToDto(Customer customer) {
        return new CustomerDTO(customer.getId(), customer.getName(), customer.getEmail(), customer.getPassword(), customer.getFidelityAccount());
    }
}
