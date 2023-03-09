package fr.polytech.entities.structure;
import fr.polytech.entities.Schedule;
import fr.polytech.entities.item.Discount;

import javax.persistence.*;
import java.util.Set;
public class Store extends Organisation {
    Schedule schedule;
    private Set<Discount> offers;
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
