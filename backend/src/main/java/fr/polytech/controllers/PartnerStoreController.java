package fr.polytech.controllers;

import fr.polytech.controllers.dto.StoreDTO;
import fr.polytech.entities.Store;
import fr.polytech.exceptions.BadCredentialsException;
import fr.polytech.exceptions.store.MissingInformationsException;
import fr.polytech.exceptions.store.StoreSiretAlreadyUsedException;
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
    public ResponseEntity<StoreDTO> register(@RequestBody @Valid StoreDTO storeDTO) throws StoreSiretAlreadyUsedException {
        return ResponseEntity.status(HttpStatus.CREATED)
                    .body(convertStoreToDto(storeRegistration.registerNewStore(storeDTO.getName(), storeDTO.getSiret(), storeDTO.getPassword())));
    }

    private StoreDTO convertStoreToDto(Store store) {
        return new StoreDTO(store.getId(), store.getSiret(), store.getName(), store.getPassword());
    }
}
