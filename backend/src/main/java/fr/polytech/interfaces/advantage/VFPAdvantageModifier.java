package fr.polytech.interfaces.advantage;

import fr.polytech.entities.Customer;
import fr.polytech.entities.CustomerAdvantage;

public interface VFPAdvantageModifier {
    CustomerAdvantage addCustomerToProgramVFP(Customer consumer);
}
