package fr.polytech.entities;
import fr.polytech.entities.item.Discount;
import fr.polytech.entities.structure.Organisation;

import java.util.Set;

public class Store extends Organisation {
    Schedule schedule;
    private Set<Discount> offers;
    private Long id;

    public Store(String storeName, String storeSiret) {
        super(storeSiret, storeName);
    }

    public Object getId() {
        return id;
    }
}
