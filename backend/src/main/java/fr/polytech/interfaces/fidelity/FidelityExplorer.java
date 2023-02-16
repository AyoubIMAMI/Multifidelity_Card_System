package fr.polytech.interfaces.fidelity;

import fr.polytech.exceptions.CustomerNotFoundException;
import fr.polytech.exceptions.FidelityAccountNotFoundException;
import fr.polytech.pojo.Customer;
import fr.polytech.pojo.FidelityAccount;
import java.util.List;

import java.util.List;

public interface FidelityExplorer {
    FidelityAccount getFidelityAccountByCustomer(Customer customer) throws CustomerNotFoundException, FidelityAccountNotFoundException;
    List<FidelityExplorer> getAllEligibleVFPCustomer();
}
