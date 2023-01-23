package fr.polytech.interfaces.store;

import fr.polytech.exceptions.BadCredentialsException;
import fr.polytech.exceptions.NotEnoughPermissionException;
import fr.polytech.exceptions.store.MissingInformationsException;

public interface StoreRegistration {
    void registerNewStore(String storeName, String storeSiret, String username, String password) throws BadCredentialsException, NotEnoughPermissionException, MissingInformationsException;
}
