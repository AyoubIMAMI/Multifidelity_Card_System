package fr.polytech.pojo.structure;
import fr.polytech.pojo.Schedule;
import fr.polytech.pojo.item.FidelityProduct;

import java.util.Date;
import java.util.HashMap;
import java.util.Set;

public class Store extends Organisation {
    Schedule schedule;
    private Set<FidelityProduct> offers;
}
