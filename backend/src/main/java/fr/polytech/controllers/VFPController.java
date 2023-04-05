package fr.polytech.controllers;

import fr.polytech.controllers.dto.StoreDTO;
import fr.polytech.exceptions.CustomerNotFoundException;
import fr.polytech.exceptions.ParkingNotPossibleException;
import fr.polytech.exceptions.advantage.AdvantageAlreadyConsumedException;
import fr.polytech.exceptions.advantage.NoAdvantageFoundException;
import fr.polytech.exceptions.advantage.VFPNotFoundException;
import fr.polytech.exceptions.discount.NoDiscountsFoundException;
import fr.polytech.interfaces.advantage.VFPTransaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping(path = VFPController.BASE_URI, produces = APPLICATION_JSON_VALUE)
public class VFPController {

    public static final String BASE_URI = "/VFP";
    public static final String VFP_URI = "/{customerID}";


    VFPTransaction vfpTransaction;

    public VFPController(VFPTransaction vfpTransaction) {
        this.vfpTransaction = vfpTransaction;
    }
    @PostMapping(path = VFP_URI+"/advantage")
    public ResponseEntity<String> useAdvantageVFP(@PathVariable("customerID") Long customerId, Long advantageId) throws NoAdvantageFoundException, VFPNotFoundException, CustomerNotFoundException, AdvantageAlreadyConsumedException, ParkingNotPossibleException {
        vfpTransaction.tryUseAdvantage(customerId, advantageId);
        return ResponseEntity.ok().body("Advantage OK");
    }

    @PostMapping(path = VFP_URI+"/advantage/parking")
    public ResponseEntity<String> useParkingAdvantageVFP(@PathVariable("customerID") Long customerId, Long advantageId, Long parkingId) throws NoAdvantageFoundException, VFPNotFoundException, CustomerNotFoundException, AdvantageAlreadyConsumedException, ParkingNotPossibleException {
        vfpTransaction.tryUsParkingAdvantage(customerId, advantageId, parkingId);
        return ResponseEntity.ok().body("Advantage OK");
    }

}
