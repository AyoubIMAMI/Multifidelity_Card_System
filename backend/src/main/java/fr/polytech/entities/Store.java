package fr.polytech.entities;
import fr.polytech.entities.item.Discount;
import fr.polytech.entities.structure.Organisation;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Store extends Organisation {


    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE)
    private Set<Payment> payments;
    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE)
    private Set<Discount> offers;

    public Store(String storeName, String storeSiret, String password) {
        super(storeSiret, storeName, password);
    }

    public Store() {

    }
}
