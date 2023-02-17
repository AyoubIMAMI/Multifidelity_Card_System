package fr.polytech.interfaces.fidelity;

import fr.polytech.exceptions.CustomerNotFoundException;
import fr.polytech.exceptions.FidelityAccountNotFoundException;
import fr.polytech.pojo.Customer;
import fr.polytech.pojo.FidelityAccount;

import java.util.List;
import java.util.UUID;

public interface FidelityExplorer {
    FidelityAccount findFidelityAccountByCustomer(Customer customer) throws CustomerNotFoundException, FidelityAccountNotFoundException;

    FidelityAccount findFidelityAccountById(UUID id) throws CustomerNotFoundException, FidelityAccountNotFoundException;
    List<FidelityExplorer> getAllEligibleVFPCustomer();
}
