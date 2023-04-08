package fr.polytech.interfaces.advantage;

import fr.polytech.exceptions.advantage.AdvantageNotFoundException;
import fr.polytech.entities.Advantage;

public interface AdvantageModifier {

    /**
     * Create an advantage
     * @param advantageName advantage name to create
     * @return the created advantage
     */
    Advantage createAdvantage(String advantageName);

    /**
     * Delete an advantage
     * @param advantageID advantage ID
     * @throws AdvantageNotFoundException threw if the advantage is not found
     */
    void deleteAdvantage(Long advantageID) throws AdvantageNotFoundException;
}
