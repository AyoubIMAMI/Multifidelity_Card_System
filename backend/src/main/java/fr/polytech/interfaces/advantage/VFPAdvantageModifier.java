package fr.polytech.interfaces.advantage;

import fr.polytech.entities.Customer;
import fr.polytech.entities.VFPAccount;

public interface VFPAdvantageModifier {
    VFPAccount addCustomerToProgramVFP(Customer consumer);
}
