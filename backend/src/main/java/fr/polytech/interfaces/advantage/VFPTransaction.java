package fr.polytech.interfaces.advantage;

import fr.polytech.exceptions.CustomerNotFoundException;
import fr.polytech.exceptions.ParkingUnavailableException;
import fr.polytech.exceptions.advantage.AdvantageAlreadyConsumedException;
import fr.polytech.exceptions.advantage.NoAdvantageFoundException;
import fr.polytech.exceptions.advantage.VFPNotFoundException;

public interface VFPTransaction {

    /**
     * Do all the verification on an advantage before using it
     * @param userID user ID who wants to use an advantage
     * @param advantageID advantage ID to be used
     * @throws CustomerNotFoundException threw if the customer is not found
     * @throws NoAdvantageFoundException threw if the advantage is not found
     * @throws VFPNotFoundException if the VFP account is not found
     * @throws AdvantageAlreadyConsumedException threw is the advantage has already been used
     */
    void tryUseAdvantage(Long userID, Long advantageID) throws CustomerNotFoundException, NoAdvantageFoundException, VFPNotFoundException, AdvantageAlreadyConsumedException;

    /**
     * Do all the verification through the tryUseAdvantage method and add a verification on the parking service
     * @param userID customer ID
     * @param advantageID advantage ID
     * @param parkingID parking ID
     * @throws CustomerNotFoundException threw if the customer is not found
     * @throws NoAdvantageFoundException threw if the advantage is not found
     * @throws VFPNotFoundException if the VFP account is not found
     * @throws AdvantageAlreadyConsumedException threw is the advantage has already been used
     * @throws ParkingUnavailableException threw is the parking is unavailable
     */
    void tryUseParkingAdvantage(Long userID, Long advantageID, Long parkingID) throws CustomerNotFoundException, NoAdvantageFoundException, VFPNotFoundException, AdvantageAlreadyConsumedException, ParkingUnavailableException;
}