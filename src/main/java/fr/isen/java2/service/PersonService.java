package fr.isen.java2.service;

import fr.isen.java2.dao.PersonDAO;
import fr.isen.java2.exceptions.InvalidPerson;
import fr.isen.java2.model.Person;

import java.util.List;

public class PersonService {

    private final PersonDAO personDAO = new PersonDAO();


    public List<Person> getAllPersons() {
        return personDAO.findAll();
    }

    public void createPerson(Person person) {
        validatePersonForCreate(person);
        personDAO.createPerson(person);
    }

    public void updatePerson(Person person) {
        validatePersonForUpdate(person);
        personDAO.updatePerson(person);
    }

    public void deletePerson(int id) {
        if (id <= 0) {
            throw new InvalidPerson("Person id must be positive");
        }
        personDAO.deletePerson(id);
    }


    private void validatePersonForCreate(Person person) {
        validatePerson(person);
    }

    private void validatePersonForUpdate(Person person) {
        if (person == null || person.getIdperson() <= 0) {
            throw new InvalidPerson("Invalid person for update");
        }
        validatePerson(person);
    }

    private void validatePerson(Person person) {

        if (person == null) {
            throw new InvalidPerson("Person cannot be null");
        }

        if (isBlank(person.getFirstName())) {
            throw new InvalidPerson("First name is required");
        }

        if (isBlank(person.getLastName())) {
            throw new InvalidPerson("Last name is required");
        }

        if (isBlank(person.getNickname())) {
            throw new InvalidPerson("Nickname is required");
        }
        if (person.getPhotoPath() != null && person.getPhotoPath().length() > 255) {
            throw new InvalidPerson("Photo path too long");
        }

        person.setFirstName(person.getFirstName().trim());
        person.setLastName(person.getLastName().trim());
        person.setNickname(person.getNickname().trim());
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}