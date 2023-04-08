package fr.polytech.interfaces.store;

import fr.polytech.entities.Store;
import fr.polytech.exceptions.BadCredentialsException;
import fr.polytech.exceptions.store.StoreNotFoundException;

public interface StoreFinder {

    /**
     * Find a store by credentials
     * @param storeName store name
     * @param myPassword store password
     * @return the Store
     * @throws BadCredentialsException threw if the credentials does not match
     */
    Store findStore(String storeName, String myPassword)  throws BadCredentialsException;

    /**
     * Find a store by ID
     * @param storeID store ID
     * @return the Store
     * @throws StoreNotFoundException threw if the Store has not been found
     */
    Store findStoreByID(Long storeID) throws StoreNotFoundException;
}
