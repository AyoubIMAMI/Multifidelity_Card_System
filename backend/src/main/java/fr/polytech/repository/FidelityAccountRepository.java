package fr.polytech.repository;

import fr.polytech.entities.FidelityAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FidelityAccountRepository extends JpaRepository<FidelityAccount, Long> {

}
