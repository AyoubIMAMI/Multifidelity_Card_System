package fr.polytech.interfaces.advantage;


import fr.polytech.entities.Advantage;
import fr.polytech.exceptions.advantage.AdvantageNotFoundException;
import java.util.Optional;

public interface AdvantageExplorer {

    /**
     * Used to verify if an advantage exists from the advantage repository
     * @param advantageID the advantage ID
     * @return an Optional Advantage, since the advantage can expire
     */
    Optional<Advantage> VerifyAdvantage(Long advantageID);

    /**
     * Get the advantage but throw if it is not found
     * @param advantageId the advantage ID
     * @return the Advantage
     * @throws AdvantageNotFoundException threw if not found
     */
    Advantage findAdvantageById(Long advantageId) throws AdvantageNotFoundException;
}
