package fr.polytech.interfaces.payment;

import fr.polytech.exceptions.NotEnoughBalanceException;
import fr.polytech.exceptions.WrongEmployeeNameOrPassword;
import fr.polytech.pojo.Payment;

public interface IPayment {
    void pay(Payment payment, String EmployeeName, String employeePassword) throws NotEnoughBalanceException, WrongEmployeeNameOrPassword;
}
