package fr.polytech.entities;
import fr.polytech.entities.item.Discount;
import fr.polytech.entities.structure.Organisation;

import javax.persistence.Entity;
import java.util.Set;

@Entity
public class Store extends Organisation {

    Schedule schedule;

    private Set<Discount> offers;

    public Store(String storeName, String storeSiret, String password) {
        super(storeSiret, storeName, password);
    }

    public Store() {

    }
}
