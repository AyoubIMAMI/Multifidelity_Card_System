package fr.polytech.interfaces.fidelity;

import fr.polytech.exceptions.CustomerNotFoundException;
import fr.polytech.entities.Customer;
import fr.polytech.entities.FidelityAccount;
import java.util.List;

public interface FidelityExplorer {
    FidelityAccount findFidelityAccountByCustomer(Customer customer) throws CustomerNotFoundException;

    FidelityAccount findFidelityAccountById(Long id) throws CustomerNotFoundException;
    boolean checkIfPossibleToBecomeVfp(Customer customer);
}
