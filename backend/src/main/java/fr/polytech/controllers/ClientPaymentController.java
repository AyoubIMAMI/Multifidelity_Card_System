package fr.polytech.controllers;

import fr.polytech.exceptions.*;
import fr.polytech.exceptions.discount.NoDiscountsFoundException;
import fr.polytech.exceptions.payment.PaymentAlreadyExistsException;
import fr.polytech.exceptions.payment.PaymentException;
import fr.polytech.interfaces.payment.IPayment;
import fr.polytech.pojo.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = fr.polytech.controllers.CustomerAccountController.BASE_URI, produces = APPLICATION_JSON_VALUE)
public class ClientPaymentController {
    IPayment payment;
    public static final String BASE_URI = "/payment";

    @Autowired
    public ClientPaymentController(IPayment iPayment) {
        this.payment = iPayment;
    }

    @PostMapping(path = "/pay")
    public ResponseEntity<String> refillAccount(@RequestBody Payment payment) throws CustomerNotFoundException, NegativeAmountException, PaymentException, NotEnoughPermissionException, PaymentAlreadyExistsException, NoDiscountsFoundException, NotEnoughBalanceException, PurchaseFailedException {
        this.payment.pay(payment);
        return ResponseEntity.ok().body("Payment succeed ok!  " + payment.toString());
    }
}
