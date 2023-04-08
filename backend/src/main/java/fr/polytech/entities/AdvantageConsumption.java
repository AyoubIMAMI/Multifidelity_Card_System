package fr.polytech.entities;

import javax.persistence.*;
import java.util.Date;

@Embeddable
public class AdvantageConsumption {
    @ManyToOne
    private Advantage advantage;
    private Date lastConsumedDate;

    public AdvantageConsumption(Advantage advantage, Date lastConsumedDate){
        this.advantage=advantage;
        this.lastConsumedDate=lastConsumedDate;
    }

    public AdvantageConsumption() {

    }


    public Advantage getAdvantage() {
        return advantage;
    }

    public Date getLastConsumedDate() {
        return lastConsumedDate;
    }

    public void setLastConsumedDate(Date lastConsumedDate) {
        this.lastConsumedDate = lastConsumedDate;
    }
}
