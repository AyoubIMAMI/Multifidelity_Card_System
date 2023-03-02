package fr.polytech.interfaces.store;

import fr.polytech.exceptions.BadCredentialsException;
import fr.polytech.exceptions.MailAlreadyUsedException;
import fr.polytech.exceptions.NotEnoughPermissionException;
import fr.polytech.exceptions.store.MissingInformationsException;
import fr.polytech.pojo.structure.Store;

public interface StoreRegistration {
    Store registerNewStore(String storeName, String storeSiret, String username, String password) throws BadCredentialsException, NotEnoughPermissionException, MissingInformationsException, MailAlreadyUsedException;
}
