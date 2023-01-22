package fr.polytech.interfaces.store;

import fr.polytech.exceptions.BadCredentilasException;
import fr.polytech.exceptions.NotEnoughPermissionException;
import fr.polytech.exceptions.store.MissingInformationsException;
import fr.polytech.pojo.item.FidelityProduct;
import fr.polytech.pojo.structure.Employee;

import java.util.HashMap;
import java.util.Set;

public interface StoreRegistration {
    void registerNewStore(HashMap<String,String> Calendar,Set<FidelityProduct> offers,String name,Set<Employee> employees, String userName, String password) throws BadCredentilasException, NotEnoughPermissionException, MissingInformationsException;

}
