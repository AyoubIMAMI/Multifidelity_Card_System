package fr.polytech.interfaces.store;

import fr.polytech.exceptions.store.InvalidDayException;
import fr.polytech.exceptions.store.InvalidHourException;
import fr.polytech.entities.Schedule;

public interface ScheduleModifier {
    void changeDayOpeningHours(Schedule schedule, String Day, String openingHour, String closingHour) throws  InvalidDayException, InvalidHourException;
    void changeDayStatus(Schedule schedule, String Day,Boolean open) throws InvalidDayException, InvalidHourException;

}
