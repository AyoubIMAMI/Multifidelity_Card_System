package fr.polytech.repository;

import fr.polytech.entities.Store;
import fr.polytech.entities.item.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {
    List<Discount> findByStore(Store store);

    Optional<Discount> findDiscountByName(String name);
}
