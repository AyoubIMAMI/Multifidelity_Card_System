package fr.polytech.components.advantage;

import fr.polytech.interfaces.advantage.AdvantageConsumer;
import fr.polytech.interfaces.advantage.CustomerAdvantageExplorer;
import fr.polytech.entities.Advantage;

import java.util.List;

public class VFPAdvantageManager implements AdvantageConsumer, CustomerAdvantageExplorer {
    @Override
    public void consumeAdvantage(Advantage advantage) {

    }

    @Override
    public List<Advantage> getAdvantage(int userId) {
        return null;
    }
}
