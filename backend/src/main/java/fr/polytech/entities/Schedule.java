package fr.polytech.entities;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Schedule {

    private Long id;
    List<String> openingDaysAndHours;
    HashMap<String,String> openingHour;
    HashMap<String,String> closingHour;


    public Schedule(List<String> openingDaysAndHours, HashMap<String, String> openingHour, HashMap<String, String> closingHour) {
        this.openingDaysAndHours = openingDaysAndHours;
        this.openingHour = openingHour;
        this.closingHour = closingHour;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public List<String> getOpeningDaysAndHours() {
        return openingDaysAndHours;
    }

    public void setOpeningDaysAndHours(List<String> openingDaysAndHours) {
        this.openingDaysAndHours = openingDaysAndHours;
    }

    public HashMap<String, String> getOpeningHour() {
        return openingHour;
    }

    public void setOpeningHour(HashMap<String, String> openingHour) {
        this.openingHour = openingHour;
    }

    public HashMap<String, String> getClosingHour() {
        return closingHour;
    }

    public void setClosingHour(HashMap<String, String> closingHour) {
        this.closingHour = closingHour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return Objects.equals(openingDaysAndHours, schedule.openingDaysAndHours) && Objects.equals(openingHour, schedule.openingHour) && Objects.equals(closingHour, schedule.closingHour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(openingDaysAndHours, openingHour, closingHour);
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", openingDaysAndHours=" + openingDaysAndHours +
                ", openingHour=" + openingHour +
                ", closingHour=" + closingHour +
                '}';
    }
}
