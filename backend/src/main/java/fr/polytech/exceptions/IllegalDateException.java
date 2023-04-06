package fr.polytech.exceptions;

import java.util.Date;

public class IllegalDateException extends Exception {

    Date date;

    public IllegalDateException(Date date) {
        this.date = date;
    }

    public IllegalDateException() {}

    public Date getDate() {
        return date;
    }
}
