package fr.polytech.interfaces.CustomerAccount;

import fr.polytech.pojo.Customer;

public interface CustomerRegistration {
    void register(Customer customer);
    //This method below might be included in the register() method
    boolean checkRegistryInformation(Customer customer);
    boolean login(Customer customer);
}
