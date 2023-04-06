package fr.polytech.controllers;

import fr.polytech.controllers.dto.StoreDTO;
import fr.polytech.entities.Store;
import fr.polytech.exceptions.IllegalDateException;
import fr.polytech.exceptions.store.StoreSiretAlreadyUsedException;
import fr.polytech.interfaces.store.StatsExplorer;
import fr.polytech.interfaces.store.StoreRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Date;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = PartnerStoreController.BASE_URI, produces = APPLICATION_JSON_VALUE)
public class PartnerStoreController {
    public static final String BASE_URI = "/stores";

    StoreRegistration storeRegistration;
    StatsExplorer statsExplorer;

    @Autowired
    public PartnerStoreController(StoreRegistration storeRegistration, StatsExplorer statsExplorer) {
        this.storeRegistration=storeRegistration;
        this.statsExplorer = statsExplorer;
    }

    @PostMapping(path = "/registration", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<StoreDTO> register(@RequestBody @Valid StoreDTO storeDTO) throws StoreSiretAlreadyUsedException {
        return ResponseEntity.status(HttpStatus.CREATED)
                    .body(convertStoreToDto(storeRegistration.registerNewStore(storeDTO.getName(), storeDTO.getSiret(), storeDTO.getPassword())));

    }

    @GetMapping(path = "/statistics/cost", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Double> getTotalCostFromBeginning() {
        return ResponseEntity.ok().body(statsExplorer.getOperationCost());
    }

    @PostMapping(path = "/statistics/cost", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Double> getTotalCostFromDate(@RequestBody Date date) throws IllegalDateException {
        return ResponseEntity.ok().body(statsExplorer.getOperationCost(date));
    }

    @GetMapping(path = "/statistics/points", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> getTotalPointsUsedFromBeginning() {
        return ResponseEntity.ok().body(statsExplorer.getUsedPoints());
    }

    @PostMapping(path = "/statistics/points", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> getTotalPointUsedFromDate(@RequestBody Date date) throws IllegalDateException {
        return ResponseEntity.ok().body(statsExplorer.getUsedPoints(date));
    }

    private StoreDTO convertStoreToDto(Store store) {
        return new StoreDTO(store.getId(), store.getSiret(), store.getName(), store.getPassword());
    }

}
