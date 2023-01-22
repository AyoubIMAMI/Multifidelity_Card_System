package fr.polytech.interfaces.store;

import fr.polytech.exceptions.*;
import fr.polytech.exceptions.store.EmployeeNotFoundException;
import fr.polytech.exceptions.store.NotValidDayException;
import fr.polytech.exceptions.store.NotValidHourException;

public interface StoreModifier {
    void addEmployee(String name, String password,String role) throws NotEnoughPermissionException, EmployeeNotFoundException, WrongEmployeeNameOrPassword;
    void deleteEmployee(int id, String myName, String myPassword) throws NotEnoughPermissionException, EmployeeNotFoundException, WrongEmployeeNameOrPassword;
    void changeDayOpeningHours(String Day,String openingHour,String closingHour, String myName, String myPassword) throws NotEnoughPermissionException, WrongEmployeeNameOrPassword, NotValidDayException, NotValidHourException;
    void changeDayStatus(String Day,Boolean open, String myName, String myPassword) throws NotEnoughPermissionException, WrongEmployeeNameOrPassword, NotValidDayException, NotValidHourException;
}
