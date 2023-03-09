package fr.polytech.interfaces.discount;

import fr.polytech.entities.item.Discount;
import fr.polytech.exceptions.discount.DiscountNotFoundException;
import fr.polytech.exceptions.discount.NoDiscountsFoundException;

public interface DiscountExplorer {
    Discount findDiscountById(Long id) throws DiscountNotFoundException;

    Iterable<Discount> findDiscountsByStore(Long storeId) throws NoDiscountsFoundException;

    Iterable<Discount> findAllDiscounts() throws NoDiscountsFoundException ;
}
