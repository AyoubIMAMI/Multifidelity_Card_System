package fr.polytech.interfaces.store;

import fr.polytech.exceptions.NotEnoughPermissionException;
import fr.polytech.exceptions.WrongEmployeeNameOrPassword;
import fr.polytech.exceptions.store.InvalidDayException;
import fr.polytech.exceptions.store.InvalidHourException;

public interface ScheduleModifier {
    void changeDayOpeningHours(String Day,String openingHour,String closingHour) throws NotEnoughPermissionException, WrongEmployeeNameOrPassword, InvalidDayException, InvalidHourException;
    void changeDayStatus(String Day,Boolean open) throws NotEnoughPermissionException, WrongEmployeeNameOrPassword, InvalidDayException, InvalidHourException;

}
