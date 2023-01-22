package fr.polytech.interfaces.employee;

import fr.polytech.exceptions.NotEnoughPermissionException;
import fr.polytech.exceptions.WrongEmployeeNameOrPassword;
import fr.polytech.exceptions.store.EmployeeNotFoundException;
import fr.polytech.pojo.structure.Employee;
import fr.polytech.pojo.structure.Role;

public interface EmployeeRegistration {
    void addEmployee(Employee employee, String newEmployeeName, String newEmployeePassword, Role newEmployeeRole) throws NotEnoughPermissionException, EmployeeNotFoundException, WrongEmployeeNameOrPassword;
}
