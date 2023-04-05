package fr.polytech.entities;

import fr.polytech.entities.structure.TerritorialCommunity;
import fr.polytech.repository.AdvantageRepository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;
@Entity
public class  Advantage {

    //private TerritorialCommunity provider;
    private String advantageName;
    @GeneratedValue
    @Id
    private Long id;

    public Advantage(String advantageName){
        this.advantageName=advantageName;
    }
    public Advantage() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getAdvantageName() {
        return advantageName;
    }
}
