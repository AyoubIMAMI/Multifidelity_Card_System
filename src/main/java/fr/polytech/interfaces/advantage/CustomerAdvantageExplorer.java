package fr.polytech.interfaces.advantage;

import fr.polytech.pojo.Advantage;

import java.util.List;

public interface CustomerAdvantageExplorer {
    List<Advantage> getAdvantage(int userId);
}
