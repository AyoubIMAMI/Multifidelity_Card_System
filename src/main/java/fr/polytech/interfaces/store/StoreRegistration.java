package fr.polytech.interfaces.store;

import fr.polytech.exceptions.BadCredentialsException;
import fr.polytech.exceptions.NotEnoughPermissionException;
import fr.polytech.exceptions.store.MissingInformationsException;
import fr.polytech.pojo.structure.Store;


public interface StoreRegistration {
    void registerNewStore(Store store, String userName, String password) throws BadCredentialsException, NotEnoughPermissionException, MissingInformationsException;
}
