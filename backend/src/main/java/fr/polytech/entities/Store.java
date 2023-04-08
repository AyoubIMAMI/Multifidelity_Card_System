package fr.polytech.entities;
import fr.polytech.entities.item.Discount;
import fr.polytech.entities.structure.Organisation;
import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Store extends Organisation {

    //Schedule schedule;

    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE)
    private Set<Discount> offers;

    public Store(String storeName, String storeSiret, String password) {
        super(storeSiret, storeName, password);
    }

    public Store() {

    }
}
