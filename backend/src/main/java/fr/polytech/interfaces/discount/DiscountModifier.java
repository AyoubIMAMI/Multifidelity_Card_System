package fr.polytech.interfaces.discount;

import fr.polytech.entities.item.Discount;
import fr.polytech.exceptions.discount.DiscountNotFoundException;
import fr.polytech.exceptions.payment.NegativeAmountException;


public interface DiscountModifier {

    Discount createDiscount(String name, Long storeId, int pointPrice) throws NegativeAmountException;

    Discount modifyPointPrice(Long id, int newPointPrice) throws DiscountNotFoundException;

    boolean deleteDiscount(Long id) throws DiscountNotFoundException;
}
