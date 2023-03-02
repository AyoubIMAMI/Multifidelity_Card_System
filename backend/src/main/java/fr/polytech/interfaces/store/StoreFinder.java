package fr.polytech.interfaces.store;

import fr.polytech.exceptions.BadCredentialsException;
import fr.polytech.exceptions.store.StoreNotFoundException;
import fr.polytech.entities.structure.Store;

public interface StoreFinder {
    Store findStore(String storeName, String myName, String myPassword)  throws BadCredentialsException, StoreNotFoundException;
}
