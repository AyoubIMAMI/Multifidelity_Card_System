package fr.polytech.interfaces.advantage;

import fr.polytech.exceptions.advantage.NoAdvantageFoundException;
import fr.polytech.entities.Advantage;

public interface AdvantageConsumer {
    void consumeAdvantage(Advantage advantage) throws NoAdvantageFoundException;
}
