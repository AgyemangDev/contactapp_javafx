package fr.isen.java2.util;

import fr.isen.java2.model.Person;
import fr.isen.java2.view.MainController;

/**
 * Simple session holder for navigation between views.
 * Used to transfer the person being edited.
 */
public class PersonSession {

    public static Person editingPerson = null;

    private PersonSession() {
        // Prevent instantiation
    }
}