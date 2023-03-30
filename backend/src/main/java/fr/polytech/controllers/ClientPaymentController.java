package fr.polytech.controllers;

import fr.polytech.entities.item.Item;
import fr.polytech.exceptions.BadCredentialsException;
import fr.polytech.exceptions.CustomerNotFoundException;
import fr.polytech.exceptions.NotEnoughBalanceException;
import fr.polytech.exceptions.PurchaseFailedException;
import fr.polytech.exceptions.discount.NoDiscountsFoundException;
import fr.polytech.exceptions.payment.NegativeAmountException;
import fr.polytech.exceptions.payment.PaymentAlreadyExistsException;
import fr.polytech.exceptions.payment.PaymentInBankException;
import fr.polytech.interfaces.payment.IPayment;
import fr.polytech.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = fr.polytech.controllers.ClientPaymentController.BASE_URI, produces = APPLICATION_JSON_VALUE)
public class ClientPaymentController {
    IPayment payment;
    public static final String BASE_URI ="/pay";
    public static final String PAYMENT_URI = "/store/{storeId}/customer/{customerId}";

    @Autowired
    public ClientPaymentController(IPayment iPayment) {
        this.payment = iPayment;
    }

    @PostMapping(path = PAYMENT_URI+"/settled")
    public ResponseEntity<String> processWithPaymentInStore(@PathVariable("customerId") Long customerId,@PathVariable("storeId") Long storeId,@RequestBody Set<Item> shoppingList) throws CustomerNotFoundException, NegativeAmountException, PaymentInBankException, PaymentAlreadyExistsException, NoDiscountsFoundException, NotEnoughBalanceException, PurchaseFailedException, BadCredentialsException {
        try {
            this.payment.payedProcess(customerId, storeId, shoppingList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.ok().body("Payment succeed!");
    }

    @PostMapping(path = PAYMENT_URI+"/fidelity")
    public ResponseEntity<String> processWithPaymentFidelity(@PathVariable("customerId") Long customerId,@PathVariable("storeId") Long storeId,@RequestBody Set<Item> shoppingList) throws CustomerNotFoundException, NegativeAmountException, PaymentInBankException, PaymentAlreadyExistsException, NoDiscountsFoundException, NotEnoughBalanceException, PurchaseFailedException, BadCredentialsException {
        this.payment.payWithFidelity(customerId,storeId,shoppingList);
        //TODO rajouter un check pour le payment success
        return ResponseEntity.ok().body("Payment succeed ok!  ");
    }




}
