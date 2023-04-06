package fr.polytech.interfaces.store;

import fr.polytech.entities.Store;
import fr.polytech.exceptions.store.StoreSiretAlreadyUsedException;

public interface StoreRegistration {
    Store registerNewStore(String storeName, String storeSiret, String password) throws StoreSiretAlreadyUsedException;
}
