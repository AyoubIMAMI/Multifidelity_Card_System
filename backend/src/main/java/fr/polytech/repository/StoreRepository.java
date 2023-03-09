package fr.polytech.repository;

import fr.polytech.entities.Customer;
import fr.polytech.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StoreRepository extends JpaRepository<Store,Long> {
    Optional<Store> existsStoreBySiret(String storeSiret);
}
