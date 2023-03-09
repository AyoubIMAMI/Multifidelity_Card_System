package fr.polytech.interfaces.discount;

import fr.polytech.exceptions.discount.DiscountNotFoundException;
import fr.polytech.pojo.item.Discount;

import java.util.UUID;

public interface DiscountModifier {

    public Discount createDiscount(String name, UUID storeId, double cashPrice, int pointPrice);

    void modifyPointPrice(UUID id, int newPointPrice) throws DiscountNotFoundException;

    boolean deleteDiscount(UUID id) throws DiscountNotFoundException;
}
