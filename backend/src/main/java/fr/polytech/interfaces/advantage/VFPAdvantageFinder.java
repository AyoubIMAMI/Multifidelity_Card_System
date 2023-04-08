package fr.polytech.interfaces.advantage;

import fr.polytech.entities.Customer;
import fr.polytech.entities.VFPAccount;
import fr.polytech.exceptions.advantage.AdvantageAlreadyConsumedException;
import fr.polytech.exceptions.advantage.NoAdvantageFoundException;
import fr.polytech.entities.Advantage;
import fr.polytech.exceptions.advantage.VFPNotFoundException;

import java.util.Optional;

public interface VFPAdvantageFinder {

    /**
     * Get the customer advantage account
     * @param customer we want to get the advantage account
     * @return an Optional of account
     */
    Optional<VFPAccount> findCustomerAdvantageAccount(Customer customer);

    /**
     * Verify is the advantage date and use it
     * @param consumerAdvantageID the VFP account id
     * @param advantage the advantage to consume
     * @throws NoAdvantageFoundException threw if the advantage is not found
     * @throws VFPNotFoundException threw if the VFP account is not found
     * @throws AdvantageAlreadyConsumedException threw if the advantage has already been consumed
     */
    void consumeAdvantage(VFPAccount consumerAdvantageID, Advantage advantage) throws NoAdvantageFoundException, VFPNotFoundException, AdvantageAlreadyConsumedException;

}
