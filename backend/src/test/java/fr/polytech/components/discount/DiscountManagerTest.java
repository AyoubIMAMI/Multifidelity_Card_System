package fr.polytech.components.discount;

import fr.polytech.entities.item.Discount;
import fr.polytech.exceptions.discount.DiscountNotFoundException;
import fr.polytech.exceptions.discount.NoDiscountsFoundException;
import fr.polytech.exceptions.payment.NegativeAmountException;
import fr.polytech.interfaces.discount.DiscountExplorer;
import fr.polytech.interfaces.discount.DiscountModifier;
import fr.polytech.repository.DiscountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class DiscountManagerTest {

    private static final String PRODUCT_NAME = "Cake";
    private static final Long STORE_ID = 1234567889L;
    private static final int CASH_PRICE = 10;
    private static final int POINT_PRICE = 7;

    @Autowired
    private DiscountModifier discountModifier;

    @Autowired
    private DiscountExplorer discountExplorer;

    @Autowired
    private DiscountRepository discountRepository;

    Discount discount;

    @BeforeEach
    void setUp() {
        discountRepository.deleteAll();
        discount = new Discount(PRODUCT_NAME, STORE_ID, POINT_PRICE);
    }

    @Test
    void givenADiscountSavedInRepo_whenFindDiscountByName_thenShouldReturnTheSameDiscount() throws DiscountNotFoundException {
        // Given
        discountRepository.save(discount);

        // When
        Discount found = discountExplorer.findDiscountByName(discount.getName());

        // Then
        assertTrue(discount.equals(found));
    }

    @Test
    void givenDiscountsFromDifferentStores_whenFindDiscountsByStore_thenShouldReturnsOnlyTheStoreDiscount() throws DiscountNotFoundException {
        // Given
        Long storeId = 456789L;

        Discount storeDiscount = new Discount("Candy", storeId, 2);

        discountRepository.save(discount);
        discountRepository.save(storeDiscount);

        // When
        Iterable<Discount> discountsFound = discountExplorer.findDiscountsByStore(storeId);

        // Then
        assertEquals(1, StreamSupport.stream(discountsFound.spliterator(), false).count());
        assertEquals(storeDiscount, discountsFound.iterator().next());
    }

    @Test
    void whenFindDiscountsOfStoreWithoutDiscounts_thenShouldThrowsNoDiscountsFoundException() {
        // When
        Executable find = () -> discountExplorer.findDiscountsByStore(1234L);

        // Then
        assertThrows(DiscountNotFoundException.class, find);
    }

    @Test
    void givenMultipleDiscountsSavedInRepo_whenFindAll_thenShouldReturnsAllDiscounts() throws NoDiscountsFoundException {
        // Given
        Long storeId = 987654321L;

        Discount storeDiscount = new Discount("Candy", storeId, 2);

        discountRepository.save(discount);
        discountRepository.save(storeDiscount);

        // When
        Iterable<Discount> discountsFound = discountExplorer.findAllDiscounts();

        // Then
        assertEquals(2, StreamSupport.stream(discountsFound.spliterator(), false).count());
    }

    @Test
    void givenAnEmptyRepo_whenFindAll_thenShouldThrowsNoDiscountsFoundException(){
        // Given
        assertEquals(0, discountRepository.count());

        // When
        Executable find = () -> discountExplorer.findAllDiscounts();

        // Then
        assertThrows(NoDiscountsFoundException.class, find);
    }

    @Test
    void givenAnEmptyRepo_whenCreateDiscount_thenDiscountShouldBeSavedInRepo() throws NegativeAmountException {
        // Given
        assertEquals(0, discountRepository.count());

        // When
        discountModifier.createDiscount(PRODUCT_NAME, STORE_ID, POINT_PRICE);

        // Then
        assertEquals(1, discountRepository.count());
    }

    @Test
    void givenDiscountSavedInRepo_whenModifyPoint_thenDiscountUpdatedInRepo() throws DiscountNotFoundException {
        // Given
        discountRepository.save(discount);

        // When
        int newPointPrice = 6;
        Discount fromRepo = discountExplorer.findDiscountByName(PRODUCT_NAME);
        discountModifier.modifyPointPrice(fromRepo.getId(), newPointPrice);

        // Then
        fromRepo = discountExplorer.findDiscountByName(PRODUCT_NAME);
        assertTrue(fromRepo.getPointPrice() == newPointPrice);
    }

    @Test
    void givenWithOneDiscount_whenDeleteDiscount_thenRepoShouldBeEmpty() throws DiscountNotFoundException {
        // Given
        discountRepository.save(discount);
        assertEquals(1, discountRepository.count());


        Discount found = discountExplorer.findDiscountByName(discount.getName());
        // When
        discountModifier.deleteDiscount(found.getId());

        // Then
        assertEquals(0, discountRepository.count());
    }
}
