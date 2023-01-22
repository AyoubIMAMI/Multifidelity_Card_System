package fr.polytech.interfaces.store;

import fr.polytech.exceptions.BadCredentialsException;
import fr.polytech.exceptions.store.StoreNotFoundException;

public interface StoreFinder {
    void findStore(String name, String myName, String myPassword)  throws BadCredentialsException, StoreNotFoundException;
}
