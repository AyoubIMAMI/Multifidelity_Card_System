package fr.polytech.repository;

import fr.polytech.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store,Long> {
    boolean existsStoreBySiret(String storeSiret);
    Optional<Store> findStoreByName(String name);
    Optional<Store> findStoreById(Long ID);

}
