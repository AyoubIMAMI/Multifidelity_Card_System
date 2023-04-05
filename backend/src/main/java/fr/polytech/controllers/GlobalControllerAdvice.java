package fr.polytech.controllers;

import fr.polytech.controllers.dto.ErrorDTO;
import fr.polytech.exceptions.BadCredentialsException;
import fr.polytech.exceptions.CustomerNotFoundException;
import fr.polytech.exceptions.MailAlreadyUsedException;
import fr.polytech.exceptions.payment.NegativeAmountException;
import fr.polytech.exceptions.payment.PaymentInBankException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = {CustomerAccountController.class, CustomerNotFoundException.class, NegativeAmountException.class, PaymentInBankException.class})
public class GlobalControllerAdvice {

    @ExceptionHandler({MailAlreadyUsedException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorDTO handleExceptions(MailAlreadyUsedException e) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setError("Mail already used!");
        errorDTO.setDetails(e.getMail() + " has already been used to register to our multi-fidelity card service...");
        return errorDTO;
    }

    @ExceptionHandler({BadCredentialsException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorDTO handleExceptions(BadCredentialsException e) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setError("Wrong credentials!");
        errorDTO.setDetails("The mail or the password is wrong.");
        return errorDTO;
    }

    @ExceptionHandler({NegativeAmountException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleExceptions(NegativeAmountException e) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setError("Negative amount!");
        errorDTO.setDetails("You cannot refill your card of a " + e.getAmount() + " amount. It must be positive...");
        return errorDTO;
    }
}
