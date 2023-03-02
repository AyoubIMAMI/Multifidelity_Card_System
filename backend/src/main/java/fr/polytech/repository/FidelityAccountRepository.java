package fr.polytech.repository;

import fr.polytech.pojo.FidelityAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FidelityAccountRepository extends JpaRepository<FidelityAccount, UUID> {

}
