package fr.polytech.interfaces.advantage;

import fr.polytech.exceptions.CustomerNotFoundException;
import fr.polytech.exceptions.ParkingUnavailableException;
import fr.polytech.exceptions.advantage.AdvantageAlreadyConsumedException;
import fr.polytech.exceptions.advantage.NoAdvantageFoundException;
import fr.polytech.exceptions.advantage.VFPNotFoundException;

public interface VFPTransaction {
    void tryUseAdvantage(Long userID, Long advantageID) throws CustomerNotFoundException, NoAdvantageFoundException, VFPNotFoundException, AdvantageAlreadyConsumedException;

    void tryUseParkingAdvantage(Long userID, Long advantageID, Long parkingID) throws CustomerNotFoundException, NoAdvantageFoundException, VFPNotFoundException, AdvantageAlreadyConsumedException, ParkingUnavailableException;
}