package fr.polytech.controllers;

import fr.polytech.exceptions.discount.DiscountNotFoundException;
import fr.polytech.exceptions.discount.NoDiscountsFoundException;
import fr.polytech.interfaces.discount.DiscountExplorer;
import fr.polytech.interfaces.discount.DiscountModifier;
import fr.polytech.pojo.item.Discount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = fr.polytech.controllers.CatalogController.BASE_URI, produces = APPLICATION_JSON_VALUE)
public class CatalogController {
    public static final String BASE_URI = "/catalog";
    public static final String DISCOUNT_URI = "/{discountId}/";

    private final DiscountModifier discountModifier;
    private final DiscountExplorer discountExplorer;

    @Autowired
    public CatalogController(DiscountModifier discountModifier, DiscountExplorer discountExplorer) {
        this.discountModifier = discountModifier;
        this.discountExplorer = discountExplorer;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Discount> create(String name, UUID storeId, double cashPrice, int pointPrice) {
        return ResponseEntity.status(HttpStatus.CREATED)
                    .body(discountModifier.createDiscount(name, storeId, cashPrice, pointPrice));
    }

    @GetMapping()
    public ResponseEntity<String> getDiscountsCatalog() {
        try {
            return ResponseEntity.ok().body(discountExplorer.findAllDiscounts().toString());
        } catch (NoDiscountsFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getStoreCatalog(UUID storeId) {
        try {
            return ResponseEntity.ok().body(discountExplorer.findDiscountsByStore(storeId).toString());
        } catch (NoDiscountsFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getDiscount(UUID id) {
        try {
            return ResponseEntity.ok().body(discountExplorer.findDiscountById(id).toString());
        } catch (DiscountNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping(path = DISCOUNT_URI, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updatePointPrice(@PathVariable("discountId") UUID discountId, int newPointPrice) {
        try {
            discountModifier.modifyPointPrice(discountId, newPointPrice);
            return ResponseEntity.ok()
                    .body("Discount successfully updated with new price: " + newPointPrice);
        } catch (DiscountNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping(path = DISCOUNT_URI)
    public ResponseEntity<String> deleteDiscount(@PathVariable("discountId") UUID discountId) {
        try {
            discountModifier.deleteDiscount(discountId);
            return ResponseEntity.ok().body("Discount successfully deleted");
        } catch (DiscountNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
