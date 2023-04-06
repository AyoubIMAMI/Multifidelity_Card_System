package fr.polytech.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Advantage {

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

    public Long getId() {
        return id;
    }

    public String getAdvantageName() {
        return advantageName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String advantageName) {this.advantageName = advantageName;}
}
