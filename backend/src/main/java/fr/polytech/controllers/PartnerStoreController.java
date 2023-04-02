package fr.polytech.controllers;

import fr.polytech.controllers.dto.StoreDTO;
import fr.polytech.entities.Store;
import fr.polytech.exceptions.BadCredentialsException;
import fr.polytech.exceptions.MailAlreadyUsedException;
import fr.polytech.exceptions.store.MissingInformationsException;
import fr.polytech.interfaces.store.StoreRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = PartnerStoreController.BASE_URI, produces = APPLICATION_JSON_VALUE)
public class PartnerStoreController {
    public static final String BASE_URI = "/stores";

    StoreRegistration storeRegistration;

    @Autowired
    public PartnerStoreController(StoreRegistration storeRegistration) {
        this.storeRegistration=storeRegistration;
    }

    @PostMapping(path = "/registration", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Store> register(@RequestBody @Valid StoreDTO storeDTO){
        //TODO return un StoreDTO comme les autre controller
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(storeRegistration.registerNewStore(storeDTO.getName(), storeDTO.getSiret(), storeDTO.getPassword()));
        } catch (MailAlreadyUsedException | BadCredentialsException | MissingInformationsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
