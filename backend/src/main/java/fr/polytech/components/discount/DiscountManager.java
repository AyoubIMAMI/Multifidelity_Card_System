package fr.polytech.components.discount;

import fr.polytech.exceptions.discount.DiscountAlreadyExistsException;
import fr.polytech.exceptions.discount.DiscountNotFoundException;
import fr.polytech.exceptions.discount.NoDiscountsFoundException;
import fr.polytech.interfaces.discount.DiscountExplorer;
import fr.polytech.interfaces.discount.DiscountModifier;
import fr.polytech.pojo.item.Discount;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DiscountManager implements DiscountModifier, DiscountExplorer {

    @Override
    public Discount findDiscountById(UUID id) throws DiscountNotFoundException {
        return null;
    }

    @Override
    public Iterable<Discount> findDiscountsByStore(UUID storeId) throws NoDiscountsFoundException {
        return null;
    }

    @Override
    public Iterable<Discount> getAllDiscount() throws NoDiscountsFoundException {
        return null;
    }

    @Override
    public Discount createDiscount(String name, double cashPrice, int pointPrice) throws DiscountAlreadyExistsException {
        return null;
    }

    @Override
    public boolean modifyPointPrice(UUID id, int newPointPrice) throws DiscountNotFoundException {
        return true;
    }

    @Override
    public boolean deleteDiscount(UUID id) throws DiscountNotFoundException {
        return false;
    }
}
