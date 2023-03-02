package fr.polytech.pojo.structure;
import fr.polytech.pojo.Schedule;
import fr.polytech.pojo.item.Discount;

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
