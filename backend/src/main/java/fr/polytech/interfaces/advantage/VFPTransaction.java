package fr.polytech.interfaces.advantage;

import fr.polytech.exceptions.CustomerNotFoundException;
import fr.polytech.exceptions.NotVFPClientException;
import fr.polytech.exceptions.advantage.ISawWhereYouParkedLastSummerUnvailableException;
import fr.polytech.entities.Advantage;

public interface VFPTransaction {
    boolean tryUseAdvantage(String username, Advantage advantage) throws CustomerNotFoundException, NotVFPClientException, ISawWhereYouParkedLastSummerUnvailableException;
}
