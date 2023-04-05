package fr.polytech.controllers;

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

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping(path = VFPController.BASE_URI, produces = APPLICATION_JSON_VALUE)
public class VFPController {

    public static final String BASE_URI = "/VFP";
    public static final String VFP_URI = "/customerID/{customerID}/";


    VFPTransaction vfpTransaction;

    public VFPController(VFPTransaction vfpTransaction) {
        this.vfpTransaction = vfpTransaction;
    }
    @PostMapping(path = VFP_URI+"/advantage")
    public ResponseEntity<String> processWithPaymentFidelity(@PathVariable("customerID") Long customerId, Long advantageID,Long parkingID) throws NoAdvantageFoundException, VFPNotFoundException, CustomerNotFoundException, AdvantageAlreadyConsumedException, ParkingNotPossibleException {
        vfpTransaction.tryUseAdvantage(customerId,advantageID,parkingID);
        return ResponseEntity.ok().body("Advantage OK");
    }

}
