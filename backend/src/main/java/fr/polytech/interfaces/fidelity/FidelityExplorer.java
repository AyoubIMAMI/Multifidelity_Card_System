package fr.polytech.interfaces.fidelity;

import fr.polytech.exceptions.CustomerNotFoundException;
import fr.polytech.entities.Customer;
import fr.polytech.entities.FidelityAccount;
import java.util.List;

public interface FidelityExplorer {
    boolean checkIfPossibleToBecomeVfp(Customer customer);
}
