package fr.polytech.controllers;

import fr.polytech.controllers.dto.PaymentDTO;
import fr.polytech.entities.item.Item;
import fr.polytech.exceptions.discount.NoDiscountsFoundException;
import fr.polytech.interfaces.advantage.CustomerAdvantageExplorer;
import fr.polytech.interfaces.advantage.VFPTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

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
    public ResponseEntity<String> processWithPaymentFidelity(@PathVariable("customerID") Long customerId, Long advantageID) throws NoDiscountsFoundException {
        try {
            vfpTransaction.tryUseAdvantage(customerId,advantageID);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        finally{
            return ResponseEntity.ok().body("Advantage OK");
        }
    }

}
