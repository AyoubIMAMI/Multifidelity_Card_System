package fr.polytech.repository;

import fr.polytech.pojo.FidelityAccount;
import fr.polytech.pojo.structure.Store;
import fr.polytech.repository.Rep.BasicRepositoryImpl;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class StoreRepository extends BasicRepositoryImpl<UUID, Store> {

    public boolean isStoreSiretAlreadyUsed(String storeSiret) {
        return false;
    }
}
