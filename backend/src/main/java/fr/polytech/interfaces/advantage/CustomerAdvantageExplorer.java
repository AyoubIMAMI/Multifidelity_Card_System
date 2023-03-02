package fr.polytech.interfaces.advantage;

import fr.polytech.exceptions.advantage.NoAdvantageFoundException;
import fr.polytech.entities.Advantage;

import java.util.List;

public interface CustomerAdvantageExplorer {
    List<Advantage> getAdvantage(int userId) throws NoAdvantageFoundException;
}
