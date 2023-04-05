package fr.polytech.components.discount;

import fr.polytech.entities.item.Discount;
import fr.polytech.exceptions.discount.DiscountNotFoundException;
import fr.polytech.exceptions.discount.NoDiscountsFoundException;
import fr.polytech.exceptions.payment.NegativeAmountException;
import fr.polytech.interfaces.discount.DiscountExplorer;
import fr.polytech.interfaces.discount.DiscountModifier;
import fr.polytech.repository.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Component
public class DiscountManager implements DiscountModifier, DiscountExplorer {

    DiscountRepository discountRepository;

    @Autowired
    public DiscountManager(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    @Override
    public Discount findDiscountById(Long id) throws DiscountNotFoundException {
        Optional<Discount> discount = discountRepository.findById(id);
        if (discount.isEmpty()) throw new DiscountNotFoundException(id);
        return discount.get();
    }

    @Override
    public Discount findDiscountByName(String name) throws DiscountNotFoundException {
        Optional<Discount> discount = discountRepository.findDiscountByName(name);
        if (discount.isEmpty()) throw new DiscountNotFoundException(name);
        return discount.get();
    }

    @Override
    public List<Discount> findDiscountsByStore(Long storeId) throws DiscountNotFoundException {
        List<Discount> discounts = discountRepository.findByStoreId(storeId);
        if(discounts.isEmpty()) {
            throw new DiscountNotFoundException(storeId);
        }
        return discounts;
    }

    @Override
    public List<Discount> findAllDiscounts() throws NoDiscountsFoundException {
        List<Discount> discounts = discountRepository.findAll();
        if(discounts.isEmpty()) {
            throw new NoDiscountsFoundException();
        }
        return discounts;
    }

    @Override
    public Discount createDiscount(String name, Long storeId, int pointPrice) throws NegativeAmountException {

        if (pointPrice <= 0) throw new NegativeAmountException(pointPrice);

        Discount discount = new Discount(name, storeId, pointPrice);
        return discountRepository.save(discount);
    }

    @Override
    public Discount modifyPointPrice(Long id, int newPointPrice) throws DiscountNotFoundException {
        Discount discount = findDiscountById(id);
        discount.setPointPrice(newPointPrice);
        return discountRepository.save(discount);
    }

    @Override
    public boolean deleteDiscount(Long id) throws DiscountNotFoundException {
        if(discountRepository.findById(id).isEmpty()) {
            throw new DiscountNotFoundException(id);
        }
        discountRepository.deleteById(id);
        return discountRepository.existsById(id);
    }
}
