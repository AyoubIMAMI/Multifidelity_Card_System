package fr.polytech.components.store;

import fr.polytech.exceptions.BadCredentialsException;
import fr.polytech.exceptions.MailAlreadyUsedException;
import fr.polytech.exceptions.store.*;
import fr.polytech.interfaces.store.StoreExplorer;
import fr.polytech.interfaces.store.StoreFinder;
import fr.polytech.interfaces.store.StoreModifier;
import fr.polytech.interfaces.store.StoreRegistration;
import fr.polytech.pojo.Schedule;
import fr.polytech.pojo.item.Discount;
import fr.polytech.pojo.structure.Role;
import fr.polytech.pojo.structure.Store;
import fr.polytech.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class StoreManager implements StoreFinder, StoreModifier, StoreRegistration, StoreExplorer {
    StoreRepository storeRepository;
    @Autowired
    StoreManager(StoreRepository storeRepository){
        this.storeRepository = storeRepository;
    }

    @Override
    public Schedule getOpeningHours(Store store) throws StoreNotFoundException {
        return null;
    }

    @Override
    public Set<Discount> getOffers(Store store) throws StoreNotFoundException {
        return null;
    }

    @Override
    public Store findStore(String storeName, String myName, String myPassword) throws BadCredentialsException, StoreNotFoundException {
        return null;
    }

    @Override
    public void addEmployee(String employeeName, String employeePassword, String newEmployeeName, String newEmployeePassword, Role newEmployeeRole) throws EmployeeNotFoundException {

    }

    @Override
    public void deleteEmployee(int id, String myName, String myPassword) throws EmployeeNotFoundException {

    }

    @Override
    public void changeDayOpeningHours(String Day, String openingHour, String closingHour, String myName, String myPassword) throws InvalidDayException, InvalidHourException {

    }

    @Override
    public void changeDayStatus(String Day, Boolean open, String myName, String myPassword) throws InvalidDayException, InvalidHourException {

    }

    @Override
    public Store registerNewStore(String storeName, String storeSiret, String username, String password) throws MissingInformationsException, MailAlreadyUsedException {
        if(storeRepository.isStoreSiretAlreadyUsed(storeSiret))
            throw new MailAlreadyUsedException();
        Store store = new Store(storeName, storeSiret);
            //a voir pourquoi ca marche pas
            //storeRepository.save(store.getId(), store);
        return store;

    }
}
