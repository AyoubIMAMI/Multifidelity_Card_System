package fr.polytech.entities.structure;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TerritorialCommunity extends Organisation {
    @Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
