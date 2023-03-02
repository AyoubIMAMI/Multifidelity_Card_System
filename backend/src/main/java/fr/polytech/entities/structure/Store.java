package fr.polytech.entities.structure;
import fr.polytech.entities.Schedule;
import fr.polytech.entities.item.Discount;

import java.util.Set;

public class Store extends Organisation {
    Schedule schedule;
    private Set<Discount> offers;
}
