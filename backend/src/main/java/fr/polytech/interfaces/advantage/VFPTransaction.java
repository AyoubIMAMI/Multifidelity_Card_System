package fr.polytech.interfaces.advantage;

import fr.polytech.exceptions.CustomerNotFoundException;
import fr.polytech.exceptions.NotVFPClientException;
import fr.polytech.exceptions.advantage.AdvantageAlreadyConsumedException;
import fr.polytech.exceptions.advantage.ISawWhereYouParkedLastSummerUnvailableException;
import fr.polytech.exceptions.advantage.NoAdvantageFoundException;
import fr.polytech.exceptions.advantage.VFPNotFoundException;

public interface VFPTransaction {
    void tryUseAdvantage(Long userID, Long advantageID) throws CustomerNotFoundException, NotVFPClientException, ISawWhereYouParkedLastSummerUnvailableException, NoAdvantageFoundException, VFPNotFoundException, AdvantageAlreadyConsumedException;
}
