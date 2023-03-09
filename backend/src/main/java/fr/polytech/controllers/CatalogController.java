package fr.polytech.controllers;

import fr.polytech.controllers.dto.DiscountDTO;
import fr.polytech.exceptions.discount.DiscountNotFoundException;
import fr.polytech.exceptions.discount.NoDiscountsFoundException;
import fr.polytech.interfaces.discount.DiscountExplorer;
import fr.polytech.interfaces.discount.DiscountModifier;
import fr.polytech.pojo.item.Discount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    public ResponseEntity<DiscountDTO> create(@RequestBody @Valid DiscountDTO discountDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                    .body(
                            convertDiscountToDto(discountModifier.createDiscount(
                                    discountDTO.getName(),
                                    discountDTO.getStoreId(),
                                    discountDTO.getCashPrice(),
                                    discountDTO.getPointPrice())));
    }

    @GetMapping(path = DISCOUNTS_URI, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DiscountDTO>> getDiscountsCatalog() {
        try {
            return ResponseEntity.ok().body(convertDiscountsToDtoList(discountExplorer.findAllDiscounts()));
        } catch (NoDiscountsFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping(path = DISCOUNTS_URI + STORE_URI, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DiscountDTO>> getStoreCatalog(@PathVariable("storeId") UUID storeId) {
        try {
            return ResponseEntity.ok().body(convertDiscountsToDtoList(discountExplorer.findDiscountsByStore(storeId)));
        } catch (NoDiscountsFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping(path = DISCOUNTS_URI + "/{discountId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<DiscountDTO> getDiscount(@PathVariable("discountId") UUID discountId) {
        try {
            return ResponseEntity.ok().body(convertDiscountToDto(discountExplorer.findDiscountById(discountId)));
        } catch (DiscountNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping(path = DISCOUNTS_URI + "/{discountId}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updatePointPrice(@PathVariable("discountId") UUID discountId, @RequestBody int pointPrice) {
        try {
            discountModifier.modifyPointPrice(discountId, pointPrice);
            return ResponseEntity.ok()
                    .body("Discount successfully updated with new price: " + pointPrice);
        } catch (DiscountNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping(DISCOUNTS_URI + "/{discountId}")
    public ResponseEntity<String> deleteDiscount(@PathVariable("discountId") UUID discountId) {
        try {
            discountModifier.deleteDiscount(discountId);
            return ResponseEntity.ok().body("Discount successfully deleted");
        } catch (DiscountNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    private DiscountDTO convertDiscountToDto(Discount discount) {
        return new DiscountDTO(discount.getId(), discount.getName(), discount.getStoreId(), discount.getCashPrice(), discount.getPointPrice());
    }

    private List<DiscountDTO> convertDiscountsToDtoList(Iterable<Discount> discounts) {
        List<DiscountDTO> discountsDTO = new ArrayList<>();

        discounts.forEach(d -> discountsDTO.add(new DiscountDTO(d.getId(), d.getName(), d.getStoreId(), d.getCashPrice(), d.getPointPrice())));

        return discountsDTO;
    }
}
