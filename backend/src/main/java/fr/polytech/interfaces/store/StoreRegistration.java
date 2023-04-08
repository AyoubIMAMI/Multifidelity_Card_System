package fr.polytech.interfaces.store;

import fr.polytech.entities.Store;
import fr.polytech.exceptions.store.StoreSiretAlreadyUsedException;

public interface StoreRegistration {

    /**
     * Registration of a new store
     * @param storeName store name
     * @param storeSiret store siret number
     * @param password store password
     * @return the new registered Store
     * @throws StoreSiretAlreadyUsedException threw if the siret number has already been used
     */
    Store registerNewStore(String storeName, String storeSiret, String password) throws StoreSiretAlreadyUsedException;
}
