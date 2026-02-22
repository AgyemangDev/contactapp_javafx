package fr.isen.java2.service;

import fr.isen.java2.dao.PersonDAO;
import fr.isen.java2.model.Person;

import java.util.List;

public class PersonService {
    private final PersonDAO personDAO = new PersonDAO();

    public List<Person> getAllPersons() {
        return personDAO.findAll();
    }

    public void updatePerson(Person person) {
        if (person == null || person.getIdperson() <= 0) {
            throw new IllegalArgumentException("Invalid person for update");
        }
        personDAO.updatePerson(person);
    }

    public void deletePerson(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Person id must be positive");
        }
        personDAO.deletePerson(id);
    }
}
