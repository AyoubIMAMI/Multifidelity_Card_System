package fr.polytech.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.HashMap;
import java.util.List;
public class Schedule {
    List<String> openingDaysAndHours;
    HashMap<String,String> openingHour;
    HashMap<String,String> closingHour;
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
