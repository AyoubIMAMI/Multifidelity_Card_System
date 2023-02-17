package fr.polytech.interfaces.advantage;

import fr.polytech.exceptions.NotEnoughPermissionException;
import fr.polytech.pojo.item.Discount;

import java.util.List;

public interface AdvantageExplorer {
    List<Discount> getAllAdvantage() throws NotEnoughPermissionException;
}
