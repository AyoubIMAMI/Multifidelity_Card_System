package fr.polytech.entities.structure;

import javax.persistence.Entity;
import javax.persistence.Id;

public class TerritorialCommunity extends Organisation {
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
