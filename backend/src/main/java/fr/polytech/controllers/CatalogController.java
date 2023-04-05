package fr.polytech.controllers;

import fr.polytech.entities.item.Discount;
import fr.polytech.exceptions.discount.DiscountNotFoundException;
import fr.polytech.exceptions.discount.NoDiscountsFoundException;
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
    public static final String STORE_URI = "/store/{storeId}";

    private final DiscountModifier discountModifier;
    private final DiscountExplorer discountExplorer;

    @Autowired
    public CatalogController(DiscountModifier discountModifier, DiscountExplorer discountExplorer) {
        this.discountModifier = discountModifier;
        this.discountExplorer = discountExplorer;
    }

    @PostMapping(path = DISCOUNTS_URI, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Discount> create(@RequestBody @Valid Discount discount) {
        return ResponseEntity.status(HttpStatus.CREATED)
                    .body(discountModifier.createDiscount(
                            discount.getName(),
                            discount.getStoreId(),
                            discount.getPointPrice()));
    }

    @GetMapping(path = DISCOUNTS_URI, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Discount>> getDiscountsCatalog() {
        try {
            return ResponseEntity.ok().body(discountExplorer.findAllDiscounts());
        } catch (NoDiscountsFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping(path = DISCOUNTS_URI+"/{discountId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Discount> getDiscountById(@PathVariable("discountId") Long discountId) {
        try {
            return ResponseEntity.ok().body(discountExplorer.findDiscountById(discountId));
        } catch (DiscountNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping(path = DISCOUNTS_URI+STORE_URI, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Discount>> getDiscountsByStore(@PathVariable("storeId") Long storeId) {
        try {
            return ResponseEntity.ok().body(discountExplorer.findDiscountsByStore(storeId));
        } catch (NoDiscountsFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping(path =DISCOUNTS_URI+"/{discountId}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Discount> updatePointPrice(@PathVariable("discountId") Long discountId, @RequestBody int pointPrice) {
        try {
            return ResponseEntity.ok()
                    .body(discountModifier.modifyPointPrice(discountId, pointPrice));
        } catch (DiscountNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping(DISCOUNTS_URI + "/{discountId}")
    public ResponseEntity<String> deleteDiscount(@PathVariable("discountId") Long discountId) {
        try {
            discountModifier.deleteDiscount(discountId);
            return ResponseEntity.ok().body("Discount successfully deleted");
        } catch (DiscountNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
