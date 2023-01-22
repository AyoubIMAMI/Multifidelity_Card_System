package fr.polytech.interfaces.fidelity;

import fr.polytech.exceptions.FidelityAccountNotFoundException;
import fr.polytech.pojo.Customer;
import fr.polytech.pojo.FidelityAccount;

public interface FidelityExplorer {
    FidelityAccount getFidelityAccountByCustomer(Customer customer) throws FidelityAccountNotFoundException;
}
