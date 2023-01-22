package fr.polytech.pojo.structure;
import fr.polytech.pojo.Schedule;
import fr.polytech.pojo.item.Discount;

import java.util.Set;

public class Store extends Organisation {
    Schedule schedule;
    private Set<Discount> offers;
}
