package fr.polytech.interfaces.advantage;

import fr.polytech.exceptions.NotEnoughPermissionException;
import fr.polytech.exceptions.advantage.AdvantageNotFoundException;
import fr.polytech.pojo.item.Discount;
import fr.polytech.pojo.structure.Store;

import java.util.List;

public interface AdvantageExplorer {
    List<Discount> getAllAdvantage() throws NotEnoughPermissionException;
}
