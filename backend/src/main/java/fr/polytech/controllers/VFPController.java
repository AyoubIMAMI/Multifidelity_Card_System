package fr.polytech.controllers;

import fr.polytech.exceptions.CustomerNotFoundException;
import fr.polytech.exceptions.ParkingUnavailableException;
import fr.polytech.exceptions.advantage.AdvantageAlreadyConsumedException;
import fr.polytech.exceptions.advantage.NoAdvantageFoundException;
import fr.polytech.exceptions.advantage.VFPNotFoundException;
import fr.polytech.interfaces.advantage.VFPTransaction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping(path = VFPController.BASE_URI, produces = APPLICATION_JSON_VALUE)
public class VFPController {

    public static final String BASE_URI = "/VFP";
    public static final String ADVANTAGE_URI = "/{customerID}/{advantageId}";

    public static final String PARKING_ADVANTAGE_URI = "/{customerID}/{advantageId}/{parkingId}";


    VFPTransaction vfpTransaction;

    public VFPController(VFPTransaction vfpTransaction) {
        this.vfpTransaction = vfpTransaction;
    }
    @GetMapping(path = ADVANTAGE_URI)
    public ResponseEntity<String> useAdvantageVFP(@PathVariable("customerID") Long customerId, @PathVariable("advantageId") Long advantageId) throws NoAdvantageFoundException, VFPNotFoundException, CustomerNotFoundException, AdvantageAlreadyConsumedException {
        vfpTransaction.tryUseAdvantage(customerId, advantageId);
        return ResponseEntity.ok().body("Advantage OK");
    }

    @GetMapping(path = PARKING_ADVANTAGE_URI)
    public ResponseEntity<String> useParkingAdvantageVFP(@PathVariable("customerID") Long customerId, @PathVariable("advantageId") Long advantageId, @PathVariable("parkingId") Long parkingId) throws NoAdvantageFoundException, VFPNotFoundException, CustomerNotFoundException, AdvantageAlreadyConsumedException, ParkingUnavailableException {
        vfpTransaction.tryUseParkingAdvantage(customerId, advantageId, parkingId);
        return ResponseEntity.ok().body("Advantage OK");
    }

}
