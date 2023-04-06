package fr.polytech.controllers;

import fr.polytech.entities.Advantage;
import fr.polytech.entities.item.Discount;
import fr.polytech.exceptions.advantage.AdvantageNotFoundException;
import fr.polytech.exceptions.discount.DiscountNotFoundException;
import fr.polytech.exceptions.discount.NoDiscountsFoundException;
import fr.polytech.exceptions.payment.NegativeAmountException;
import fr.polytech.interfaces.advantage.AdvantageExplorer;
import fr.polytech.interfaces.advantage.AdvantageModifier;
import fr.polytech.interfaces.discount.DiscountExplorer;
import fr.polytech.interfaces.discount.DiscountModifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = fr.polytech.controllers.CatalogController.BASE_URI, produces = APPLICATION_JSON_VALUE)
public class CatalogController {
    public static final String BASE_URI = "/catalog";
    public static final String DISCOUNTS_URI = "/discounts";

    public static final String ADVANTAGE_URI = "/advantage";
    public static final String STORE_URI = "/store/{storeId}";

    private final DiscountModifier discountModifier;
    private final DiscountExplorer discountExplorer;

    private final AdvantageModifier advantageModifier;
    private final AdvantageExplorer advantageExplorer;

    @Autowired
    public CatalogController(DiscountModifier discountModifier, DiscountExplorer discountExplorer, AdvantageModifier advantageModifier, AdvantageExplorer advantageExplorer) {
        this.discountModifier = discountModifier;
        this.discountExplorer = discountExplorer;
        this.advantageModifier = advantageModifier;
        this.advantageExplorer = advantageExplorer;
    }

    @PostMapping(path = DISCOUNTS_URI, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Discount> create(@RequestBody @Valid Discount discount) throws NegativeAmountException {
        return ResponseEntity.status(HttpStatus.CREATED)
                    .body(discountModifier.createDiscount(
                            discount.getName(),
                            discount.getStoreId(),
                            discount.getPointPrice()));
    }

    @GetMapping(path = DISCOUNTS_URI, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Discount>> getDiscountsCatalog() throws NoDiscountsFoundException {
        return ResponseEntity.ok().body(discountExplorer.findAllDiscounts());
    }

    @PostMapping(path = ADVANTAGE_URI, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Advantage> createAdvantage(@RequestBody String advantageName) {
        System.out.println("Name received from cli: " + advantageName);
        Advantage advantage = advantageModifier.createAdvantage(advantageName);
        System.out.println("Name before sending to cli: " + advantage.getAdvantageName());
        return ResponseEntity.ok().body(advantage);
    }

    @DeleteMapping(path = ADVANTAGE_URI + "/{advantageID}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteAdvantage(@PathVariable("advantageID") Long advantageID) throws AdvantageNotFoundException {
        advantageModifier.deleteAdvantage(advantageID);
        return ResponseEntity.ok().body("Advantage successfully deleted");
    }

    @GetMapping(path = ADVANTAGE_URI + "/{advantageId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Advantage> getAdvantageById(@PathVariable("advantageId") Long advantageId) throws AdvantageNotFoundException {
        return ResponseEntity.ok().body(advantageExplorer.findAdvantageById(advantageId));
    }

    @GetMapping(path = DISCOUNTS_URI+"/{discountId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Discount> getDiscountById(@PathVariable("discountId") Long discountId) throws DiscountNotFoundException {
        return ResponseEntity.ok().body(discountExplorer.findDiscountById(discountId));
    }

    @GetMapping(path = DISCOUNTS_URI+STORE_URI, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Discount>> getDiscountsByStore(@PathVariable("storeId") Long storeId) throws DiscountNotFoundException {
        return ResponseEntity.ok().body(discountExplorer.findDiscountsByStore(storeId));
    }

    @PutMapping(path =DISCOUNTS_URI+"/{discountId}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Discount> updatePointPrice(@PathVariable("discountId") Long discountId, @RequestBody int pointPrice) throws DiscountNotFoundException {
        return ResponseEntity.ok().body(discountModifier.modifyPointPrice(discountId, pointPrice));
    }

    @DeleteMapping(DISCOUNTS_URI + "/{discountId}")
    public ResponseEntity<String> deleteDiscount(@PathVariable("discountId") Long discountId) throws DiscountNotFoundException {
        discountModifier.deleteDiscount(discountId);
        return ResponseEntity.ok().body("Discount successfully deleted");
    }

}
