package fr.polytech.repository;

import fr.polytech.entities.Advantage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdvantageRepository extends JpaRepository<Advantage, Long> {
    Optional<Advantage> findById(Long id);
}
