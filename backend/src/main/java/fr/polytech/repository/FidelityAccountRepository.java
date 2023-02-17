package fr.polytech.repository;

import fr.polytech.pojo.FidelityAccount;
import fr.polytech.repository.Rep.BasicRepositoryImpl;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class FidelityAccountRepository extends BasicRepositoryImpl<FidelityAccount, UUID> {

}
