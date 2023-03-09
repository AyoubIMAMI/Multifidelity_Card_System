package fr.polytech.interfaces.discount;

import fr.polytech.entities.item.Discount;
import fr.polytech.exceptions.discount.DiscountNotFoundException;
import fr.polytech.exceptions.discount.NoDiscountsFoundException;

import java.util.UUID;

public interface DiscountExplorer {
    Discount findDiscountById(UUID id) throws DiscountNotFoundException;

    Iterable<Discount> findDiscountsByStore(UUID storeId) throws NoDiscountsFoundException;

    Iterable<Discount> findAllDiscounts() throws NoDiscountsFoundException ;
}
