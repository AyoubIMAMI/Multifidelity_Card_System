package fr.polytech.Repository;

import fr.polytech.Repository.Rep.BasicRepositoryImpl;
import fr.polytech.pojo.Customer;
import fr.polytech.pojo.FidelityAccount;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class FidelityAccountRepository extends BasicRepositoryImpl<FidelityAccount, UUID> {

}
