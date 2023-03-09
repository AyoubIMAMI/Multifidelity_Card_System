package fr.polytech.entities;
import fr.polytech.entities.item.Discount;
import fr.polytech.entities.structure.Organisation;

import java.util.Set;
import java.util.UUID;

public class Store extends Organisation {
    Schedule schedule;
    private Set<Discount> offers;
    private UUID id;

    public Store(String storeName, String storeSiret) {
        super(storeSiret, storeName);
        id= UUID.randomUUID();
    }

    public Object getId() {
        return id;
    }
}
