package fr.polytech.interfaces.discount;

import fr.polytech.entities.item.Discount;
import fr.polytech.exceptions.discount.DiscountNotFoundException;
import fr.polytech.exceptions.discount.NoDiscountsFoundException;

import java.util.List;
import java.util.Optional;

public interface DiscountExplorer {
    Discount findDiscountById(Long id) throws DiscountNotFoundException;

    Discount findDiscountByName(String name) throws DiscountNotFoundException;

    List<Discount> findDiscountsByStore(Long storeId) throws NoDiscountsFoundException;

    List<Discount> findAllDiscounts() throws NoDiscountsFoundException ;
}
