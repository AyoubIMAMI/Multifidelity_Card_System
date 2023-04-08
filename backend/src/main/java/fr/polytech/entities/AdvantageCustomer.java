package fr.polytech.entities;

import javax.persistence.*;
import java.util.Date;

@Embeddable
public class AdvantageCustomer {
    @ManyToOne
    private Advantage advantage;
    private Date lastConsumedDate;

    public AdvantageCustomer(Advantage advantage,Date lastConsumedDate){
        this.advantage=advantage;
        this.lastConsumedDate=lastConsumedDate;
    }

    public AdvantageCustomer() {

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
