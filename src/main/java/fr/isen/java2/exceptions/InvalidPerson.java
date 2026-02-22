package fr.isen.java2.exceptions;

public class InvalidPerson extends RuntimeException {
    public InvalidPerson(String message) {
        super(message);
    }
}
