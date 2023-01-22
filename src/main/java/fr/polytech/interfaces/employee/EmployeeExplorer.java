package fr.polytech.interfaces.employee;

import fr.polytech.exceptions.BadCredentialsException;
import fr.polytech.exceptions.MalformedCredentialsExceptions;
import fr.polytech.exceptions.store.EmployeeNotFoundException;
import fr.polytech.pojo.structure.Employee;

public interface EmployeeExplorer {
    /**
     * For a given id, return a
     * @param id The id of an employee to find
     * @return The employee associated with the given id
     * @throws EmployeeNotFoundException The employee wasn't found
     */
    Employee findEmployeeById(int id) throws EmployeeNotFoundException;

    /**
     * For a given id, return a
     * @param name The name of an employee to find
     * @return The employee associated with the given name
     * @throws EmployeeNotFoundException The employee wasn't found
     */
    Employee findEmployeeByName(String name) throws EmployeeNotFoundException;

    /**
     * Check the given credentials
     * @param name The name of the employee
     * @param password The password of the employee
     * @throws BadCredentialsException The credentials are mismatched
     * @throws MalformedCredentialsExceptions The credentials are malformed
     */
    void checkCredentials(String name, String password) throws BadCredentialsException, MalformedCredentialsExceptions;
}
