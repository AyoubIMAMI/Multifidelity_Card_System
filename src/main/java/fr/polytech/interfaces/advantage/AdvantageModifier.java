package fr.polytech.interfaces.advantage;

import fr.polytech.exceptions.advantage.AdvantageNotFoundException;
import fr.polytech.pojo.Advantage;

import java.util.Date;

public interface AdvantageModifier {
    void modifyAdvantageName(Advantage advantage) throws AdvantageNotFoundException;
    void modifyExpirationDate(Advantage advantage) throws AdvantageNotFoundException;
    void modifCounter(Advantage advantage) throws AdvantageNotFoundException;
}
