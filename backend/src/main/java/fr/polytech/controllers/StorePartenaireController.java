package fr.polytech.controllers;

import fr.polytech.controllers.dto.CustomerDTO;
import fr.polytech.controllers.dto.PaymentDTO;
import fr.polytech.controllers.dto.StoreDTO;
import fr.polytech.entities.Customer;
import fr.polytech.entities.Store;
import fr.polytech.exceptions.BadCredentialsException;
import fr.polytech.exceptions.CustomerNotFoundException;
import fr.polytech.exceptions.FidelityAccountNotFoundException;
import fr.polytech.exceptions.MailAlreadyUsedException;
import fr.polytech.exceptions.payment.NegativeAmountException;
import fr.polytech.exceptions.payment.PaymentInBankException;
import fr.polytech.exceptions.store.MissingInformationsException;
import fr.polytech.interfaces.customer.CustomerExplorer;
import fr.polytech.interfaces.customer.CustomerFinder;
import fr.polytech.interfaces.customer.CustomerRegistration;
import fr.polytech.interfaces.fidelity.FidelityExplorer;
import fr.polytech.interfaces.payment.RefillFidelityCard;
import fr.polytech.interfaces.store.StoreRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = fr.polytech.controllers.StorePartenaireController.BASE_URI, produces = APPLICATION_JSON_VALUE)
public class StorePartenaireController {
    public static final String BASE_URI = "/stores";

    StoreRegistration storeRegistration;

    @Autowired
    public StorePartenaireController(StoreRegistration storeRegistration) {
        this.storeRegistration=storeRegistration;
    }

    @PostMapping(path = "/registration", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Store> register(@RequestBody @Valid StoreDTO storeDTO) throws MailAlreadyUsedException {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(storeRegistration.registerNewStore(storeDTO.getName(),storeDTO.getSiret(),storeDTO.getPassword()));
        } catch (MailAlreadyUsedException | BadCredentialsException | MissingInformationsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
