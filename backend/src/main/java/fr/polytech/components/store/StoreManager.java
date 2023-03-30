package fr.polytech.components.store;

import fr.polytech.entities.Customer;
import fr.polytech.entities.Schedule;
import fr.polytech.entities.item.Discount;
import fr.polytech.entities.structure.Role;
import fr.polytech.exceptions.BadCredentialsException;
import fr.polytech.exceptions.MailAlreadyUsedException;
import fr.polytech.exceptions.store.*;
import fr.polytech.interfaces.store.StoreExplorer;
import fr.polytech.interfaces.store.StoreFinder;
import fr.polytech.interfaces.store.StoreModifier;
import fr.polytech.interfaces.store.StoreRegistration;
import fr.polytech.entities.Store;
import fr.polytech.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.stream.StreamSupport;

@Component
public class StoreManager implements StoreFinder, StoreRegistration{
    StoreRepository storeRepository;
    @Autowired
    StoreManager(StoreRepository storeRepository){
        this.storeRepository = storeRepository;
    }

    @Override
    public Store findStore(String storeName, String myPassword) throws BadCredentialsException {
        Optional<Store> storeCurrent= StreamSupport.stream(storeRepository.findAll().spliterator(), false)
                .filter(store -> storeName.equals(store.getName())&&myPassword.equals(store.getPassword())).findAny();
        if (storeCurrent.isEmpty()) throw new BadCredentialsException();
        else return storeCurrent.get();
    }

    @Override
    public Store registerNewStore(String storeName, String storeSiret,String password) throws MissingInformationsException, MailAlreadyUsedException {
        if(storeRepository.existsStoreBySiret(storeSiret))
            throw new MailAlreadyUsedException();
        Store store = new Store(storeName, storeSiret,password);
        storeRepository.save(store);
        return store;
    }
}
