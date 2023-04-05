package fr.polytech.interfaces.fidelity;

import fr.polytech.exceptions.CustomerNotFoundException;
import fr.polytech.exceptions.FidelityAccountNotFoundException;
import fr.polytech.entities.Customer;
import fr.polytech.entities.FidelityAccount;
import java.util.List;

public interface FidelityExplorer {
    FidelityAccount findFidelityAccountByCustomer(Customer customer) throws CustomerNotFoundException, FidelityAccountNotFoundException;

    FidelityAccount findFidelityAccountById(Long id) throws CustomerNotFoundException, FidelityAccountNotFoundException;
    boolean checkIfPossibleToBecomeVfp(Customer customer);


}
