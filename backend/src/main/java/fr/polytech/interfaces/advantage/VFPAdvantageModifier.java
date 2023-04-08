package fr.polytech.interfaces.advantage;

import fr.polytech.entities.Customer;
import fr.polytech.entities.VFPAccount;

public interface VFPAdvantageModifier {

    /**
     * Add a customer to the VFP program
     * @param consumer customer to ba added
     * @return the customer VFP account
     */
    VFPAccount addCustomerToProgramVFP(Customer consumer);
}
