package fr.polytech.entities.structure;
import fr.polytech.entities.Schedule;
import fr.polytech.entities.item.Discount;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Set;
@Entity
public class Store extends Organisation {
    @OneToOne
    Schedule schedule;
    @OneToMany
    private Set<Discount> offers;
    @Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
