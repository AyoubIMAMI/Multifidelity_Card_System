package fr.polytech.interfaces.fidelity;

import fr.polytech.entities.Customer;

public interface FidelityExplorer {
    boolean checkIfPossibleToBecomeVfp(Customer customer);
}
