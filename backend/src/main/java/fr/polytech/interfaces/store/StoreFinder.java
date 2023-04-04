package fr.polytech.interfaces.store;

import fr.polytech.entities.Store;
import fr.polytech.exceptions.BadCredentialsException;
import fr.polytech.exceptions.store.StoreNotFoundException;

public interface StoreFinder {
    Store findStore(String storeName, String myPassword)  throws BadCredentialsException;
    Store findStoreByID(Long storeID) throws StoreNotFoundException;
}
