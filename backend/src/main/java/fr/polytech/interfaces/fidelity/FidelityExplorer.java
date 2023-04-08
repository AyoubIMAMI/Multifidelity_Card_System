package fr.polytech.interfaces.fidelity;

import fr.polytech.entities.Customer;

public interface FidelityExplorer {

    /**
     * Verify if the customer can become VFP
     * @param customer to verify
     * @return true if customer can become VFP
     */
    boolean checkIfPossibleToBecomeVfp(Customer customer);
}
