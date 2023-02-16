package fr.polytech.controllers;

import fr.polytech.exceptions.*;
import fr.polytech.exceptions.discount.NoDiscountsFoundException;
import fr.polytech.exceptions.paiment.PaymentException;
import fr.polytech.interfaces.payment.IPayment;
import fr.polytech.pojo.BankTransaction;
import fr.polytech.pojo.Customer;
import fr.polytech.pojo.Payment;
import fr.polytech.pojo.structure.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = fr.polytech.controllers.CustomerAccountController.BASE_URI, produces = APPLICATION_JSON_VALUE)
public class ClientPaymentController {
    IPayment iPayment;
    public static final String BASE_URI = "/payment";

    @Autowired
    public ClientPaymentController(IPayment iPayment) {
        this.iPayment = iPayment;
    }

    @PostMapping(path = "/pay")
    public ResponseEntity<String> refillAccount(@RequestBody Payment payment) throws CustomerNotFoundException, MalformedBankInformationException, PaymentException, NotEnoughPermissionException, PaymentAlreadyExistsException, NoDiscountsFoundException, NotEnoughBalanceException, PurchaseFailedException {
        iPayment.pay(payment);
        return ResponseEntity.ok().body("Payment succeed ok!  " + payment.toString());
    }
}
