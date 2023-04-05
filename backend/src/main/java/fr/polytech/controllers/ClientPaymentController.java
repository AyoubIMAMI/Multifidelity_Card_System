package fr.polytech.controllers;

import fr.polytech.controllers.dto.*;
import fr.polytech.entities.Customer;
import fr.polytech.entities.Store;
import fr.polytech.entities.item.Item;
import fr.polytech.exceptions.BadCredentialsException;
import fr.polytech.exceptions.CustomerNotFoundException;
import fr.polytech.exceptions.NotEnoughBalanceException;
import fr.polytech.exceptions.discount.NoDiscountsFoundException;
import fr.polytech.exceptions.payment.PaymentAlreadyExistsException;
import fr.polytech.exceptions.store.StoreNotFoundException;
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
    public ResponseEntity<PaymentDTO> processWithPaymentInStore(@PathVariable("customerId") Long customerId, @PathVariable("storeId") Long storeId, @RequestBody Set<Item> shoppingList) throws NoDiscountsFoundException, StoreNotFoundException, PaymentAlreadyExistsException, BadCredentialsException, CustomerNotFoundException, NotEnoughBalanceException {
        System.out.println("Shopping List received : " + shoppingList);
        return ResponseEntity.ok().body(convertPaymentToDto(this.payment.payedProcess(customerId, storeId, shoppingList)));
    }

    @PostMapping(path = PAYMENT_URI+"/fidelity")
    public ResponseEntity<PaymentDTO> processWithPaymentFidelity(@PathVariable("customerId") Long customerId, @PathVariable("storeId") Long storeId, @RequestBody Set<Item> shoppingList) throws StoreNotFoundException, PaymentAlreadyExistsException, NoDiscountsFoundException, BadCredentialsException, CustomerNotFoundException, NotEnoughBalanceException {
        return ResponseEntity.ok().body(convertPaymentToDto(this.payment.payWithFidelity(customerId, storeId, shoppingList)));
    }

    private PaymentDTO convertPaymentToDto(Payment payment) {
        System.out.println("Payment received in converter : " + payment);
        PaymentDTO paymentDTO = new PaymentDTO(payment.getId(), convertCustomerToDto(payment.getCustomer()), convertStoreToDto(payment.getStore()), payment.getShoppingList(), payment.getAmount());
        System.out.println("Payment DTO created : " + paymentDTO);
        return paymentDTO;
    }

    private CustomerDTO convertCustomerToDto(Customer customer) {
        return new CustomerDTO(customer.getId(), customer.getName(), customer.getEmail(), customer.getPassword(), customer.getFidelityAccount());
    }

    private StoreDTO convertStoreToDto(Store store) {
        return new StoreDTO(store.getId(), store.getName(), store.getSiret(), store.getPassword());
    }
}
