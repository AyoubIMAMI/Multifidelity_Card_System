package fr.polytech.interfaces.employee;

import fr.polytech.exceptions.store.EmployeeNotFoundException;
import fr.polytech.entities.structure.Employee;
import fr.polytech.entities.structure.Role;

public interface EmployeeRegistration {
    /**
     * Add an employee to the registry
     * @param employee The employee in charge of adding a new employee
     * @param newEmployeeName The name of the new employee
     * @param newEmployeePassword The password of the new employee
     * @param newEmployeeRole The role of the new employee
     * @throws EmployeeNotFoundException The employee in charge of adding a new employee wasn't found
     */
    void addEmployee(Employee employee, String newEmployeeName, String newEmployeePassword, Role newEmployeeRole) throws  EmployeeNotFoundException;
}
