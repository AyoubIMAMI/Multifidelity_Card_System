package fr.polytech.exceptions;

public class MailAlreadyUsedException extends Exception {

    String mail;

    public MailAlreadyUsedException(String mail) {
        this.mail = mail;
    }

    public MailAlreadyUsedException(){}

    public String getMail() {
        return mail;
    }
}
