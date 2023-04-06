package fr.polytech.interfaces.store;

public interface StoreModifier {
    void addEmployee(String employeeName, String employeePassword, String newEmployeeName, String newEmployeePassword);
    void deleteEmployee(int id, String myName, String myPassword);
    void changeDayOpeningHours(String Day,String openingHour,String closingHour, String myName, String myPassword);
    void changeDayStatus(String Day,Boolean open, String myName, String myPassword);
}
