package fr.polytech.repository;

import fr.polytech.repository.Rep.BasicRepositoryImpl;
import fr.polytech.pojo.FidelityAccount;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class FidelityAccountRepository extends BasicRepositoryImpl<FidelityAccount, UUID> {

}
