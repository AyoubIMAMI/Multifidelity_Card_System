package fr.polytech.interfaces.store;

import fr.polytech.exceptions.BadCredentialsException;
import fr.polytech.exceptions.MailAlreadyUsedException;
import fr.polytech.exceptions.store.MissingInformationsException;
import fr.polytech.entities.Store;

public interface StoreRegistration {
    Store registerNewStore(String storeName, String storeSiret, String username, String password) throws BadCredentialsException, MissingInformationsException, MailAlreadyUsedException;
}
