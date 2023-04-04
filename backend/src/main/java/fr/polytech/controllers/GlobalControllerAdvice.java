package fr.polytech.controllers;

import fr.polytech.controllers.dto.ErrorDTO;
import fr.polytech.exceptions.BadCredentialsException;
import fr.polytech.exceptions.MailAlreadyUsedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = {CustomerAccountController.class})
public class GlobalControllerAdvice {

    @ExceptionHandler({MailAlreadyUsedException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleExceptions(MailAlreadyUsedException e) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setError("Mail already used!");
        errorDTO.setDetails(e.getMail() + " has already been used to register to our multi-fidelity card service...");
        return errorDTO;
    }

    @ExceptionHandler({BadCredentialsException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleExceptions(BadCredentialsException e) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setError("Wrong credentials!");
        errorDTO.setDetails("The mail or the password is wrong.");
        return errorDTO;
    }
}
