package fr.polytech.interfaces.discount;

import fr.polytech.exceptions.discount.DiscountNotFoundException;
import fr.polytech.entities.item.Discount;


public interface DiscountModifier {

    public Discount createDiscount(String name, Long storeId, double cashPrice, int pointPrice);

    Discount modifyPointPrice(Long id, int newPointPrice) throws DiscountNotFoundException;

    boolean deleteDiscount(Long id) throws DiscountNotFoundException;
}
