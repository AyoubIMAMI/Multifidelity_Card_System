package fr.polytech.interfaces.employee;

import fr.polytech.exceptions.BadCredentilasException;
import fr.polytech.exceptions.MalformedCredentialsExceptions;
import fr.polytech.exceptions.store.EmployeeNotFoundException;
import fr.polytech.pojo.structure.Employee;

public interface EmployeeFinder {
    Employee findEmployeeById(int id) throws EmployeeNotFoundException;
    void checkCredentials(String name, String password) throws BadCredentilasException, MalformedCredentialsExceptions;
}
