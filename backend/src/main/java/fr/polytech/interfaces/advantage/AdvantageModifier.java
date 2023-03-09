package fr.polytech.interfaces.advantage;

import fr.polytech.exceptions.advantage.AdvantageNotFoundException;
import fr.polytech.entities.Advantage;

public interface AdvantageModifier {
    void modifyAdvantageName(Advantage advantage, String newName) throws AdvantageNotFoundException;
    void modifyExpirationDate(Advantage advantage, String newDate) throws AdvantageNotFoundException;
    void modifyCounter(Advantage advantage, int newCounter) throws AdvantageNotFoundException;
}
