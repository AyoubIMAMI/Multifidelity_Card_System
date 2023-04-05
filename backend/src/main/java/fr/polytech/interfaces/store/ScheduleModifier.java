package fr.polytech.interfaces.store;

import fr.polytech.entities.Schedule;

public interface ScheduleModifier {
    void changeDayOpeningHours(Schedule schedule, String Day, String openingHour, String closingHour);
    void changeDayStatus(Schedule schedule, String Day,Boolean open);

}
