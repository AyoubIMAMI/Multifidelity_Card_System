package fr.polytech.interfaces.CustomerAccount;

import fr.polytech.pojo.Customer;

public interface CustomerModifier {
    void changeUsername(Customer customer);
    void changeMail(Customer customer);
    void changerPassword(Customer customer);
}
