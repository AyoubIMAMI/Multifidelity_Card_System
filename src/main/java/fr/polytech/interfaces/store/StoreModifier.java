package fr.polytech.interfaces.store;

import fr.polytech.exceptions.*;
import fr.polytech.pojo.structure.Role;
import fr.polytech.exceptions.store.EmployeeNotFoundException;
import fr.polytech.exceptions.store.InvalidDayException;
import fr.polytech.exceptions.store.InvalidHourException;

public interface StoreModifier {
    void addEmployee(String employeeName, String employeePassword, String newEmployeeName, String newEmployeePassword, Role newEmployeeRole) throws NotEnoughPermissionException, EmployeeNotFoundException, WrongEmployeeNameOrPassword;
    void deleteEmployee(int id, String myName, String myPassword) throws NotEnoughPermissionException, EmployeeNotFoundException, WrongEmployeeNameOrPassword;
    void changeDayOpeningHours(String Day,String openingHour,String closingHour, String myName, String myPassword) throws NotEnoughPermissionException, WrongEmployeeNameOrPassword, InvalidDayException, InvalidHourException;
    void changeDayStatus(String Day,Boolean open, String myName, String myPassword) throws NotEnoughPermissionException, WrongEmployeeNameOrPassword, InvalidDayException, InvalidHourException;
}
