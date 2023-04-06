package fr.polytech.components.store;

import fr.polytech.exceptions.BadCredentialsException;
import fr.polytech.exceptions.store.*;
import fr.polytech.interfaces.store.StoreFinder;
import fr.polytech.interfaces.store.StoreRegistration;
import fr.polytech.entities.Store;
import fr.polytech.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Component
public class StoreManager implements StoreFinder, StoreRegistration{
    StoreRepository storeRepository;
    @Autowired
    StoreManager(StoreRepository storeRepository){
        this.storeRepository = storeRepository;
    }

    @Override
    public Store findStore(String storeName, String myPassword) throws BadCredentialsException {
        Optional<Store> storeCurrent = storeRepository.findAll().stream()
                .filter(store -> storeName.equals(store.getName())&&myPassword.equals(store.getPassword())).findAny();
        if (storeCurrent.isEmpty()) throw new BadCredentialsException();
        else return storeCurrent.get();
    }
    @Override
    public Store findStoreByID(Long storeID) throws StoreNotFoundException {
        System.out.println("On cherche le store avec l'id : " + storeID);
        Optional<Store> store = storeRepository.findStoreById(storeID);
        if (store.isEmpty()) {
            System.out.println("Aucun store trouvé");
            throw new StoreNotFoundException(storeID);
        }
        return store.get();
    }
    @Override
    public Store registerNewStore(String storeName, String storeSiret, String password) throws StoreSiretAlreadyUsedException {
        if(storeRepository.existsStoreBySiret(storeSiret))
            throw new StoreSiretAlreadyUsedException(storeSiret);
        Store store = new Store(storeName, storeSiret, password);
        return storeRepository.save(store);
    }
}
