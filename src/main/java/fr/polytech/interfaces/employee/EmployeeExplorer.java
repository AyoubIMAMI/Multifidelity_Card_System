package fr.polytech.interfaces.employee;

import fr.polytech.exceptions.BadCredentialsException;
import fr.polytech.exceptions.MalformedCredentialsExceptions;
import fr.polytech.exceptions.store.EmployeeNotFoundException;
import fr.polytech.pojo.structure.Employee;

import java.util.Set;

public interface EmployeeExplorer {
    /**
     * For a given id, return the employee with the given id
     * @param id The id of the employee to find
     * @return The employee associated with the given id
     * @throws EmployeeNotFoundException The employee wasn't found
     */
    Employee findEmployeeById(int id) throws EmployeeNotFoundException;

    /**
     * For a given id, return all the employee with the given name
     * @param name The name of the employee to find
     * @return The employees associated with the given name
     */
    Set<Employee> findEmployeeByName(String name);

    /**
     * Check the given credentials
     * @param name The name of the employee
     * @param password The password of the employee
     * @throws BadCredentialsException The credentials are mismatched
     * @throws MalformedCredentialsExceptions The credentials are malformed
     */
    void checkCredentials(String name, String password) throws BadCredentialsException, MalformedCredentialsExceptions;
}
