package fr.polytech.interfaces.store;

import fr.polytech.exceptions.NotEnoughPermissionException;
import fr.polytech.exceptions.WrongEmployeeNameOrPassword;
import fr.polytech.exceptions.store.NotValidDayException;
import fr.polytech.exceptions.store.NotValidHourException;

public interface ScheduleModifier {
    void changeDayOpeningHours(String Day,String openingHour,String closingHour) throws NotEnoughPermissionException, WrongEmployeeNameOrPassword, NotValidDayException, NotValidHourException;
    void changeDayStatus(String Day,Boolean open) throws NotEnoughPermissionException, WrongEmployeeNameOrPassword, NotValidDayException, NotValidHourException;

}
