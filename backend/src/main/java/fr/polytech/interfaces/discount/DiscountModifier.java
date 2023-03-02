package fr.polytech.interfaces.discount;

import fr.polytech.exceptions.discount.DiscountAlreadyExistsException;
import fr.polytech.exceptions.discount.DiscountNotFoundException;
import fr.polytech.pojo.item.Discount;

import java.util.UUID;

public interface DiscountModifier {

    public Discount createDiscount(String name, double cashPrice, int pointPrice) throws DiscountAlreadyExistsException;

    boolean modifyPointPrice(UUID id, int newPointPrice) throws DiscountNotFoundException;

    boolean deleteDiscount(UUID id) throws DiscountNotFoundException;
}
