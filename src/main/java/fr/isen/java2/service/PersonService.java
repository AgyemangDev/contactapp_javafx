package fr.isen.java2.service;

import fr.isen.java2.dao.PersonDAO;
import fr.isen.java2.model.Person;

import java.util.List;

public class PersonService {
    private final PersonDAO personDAO = new PersonDAO();

    public List<Person> getAllPersons() {
        try {
            return personDAO.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error fetching persons", e);
        }
    }

//    public void addPerson(Person p) throws SQLException {
//        personDAO.insert(p);
//    }
//
//    public void updatePerson(Person p) throws SQLException {
//        personDAO.update(p);
//    }
//
    public void deletePerson(int id)  {
        try {
            if(id <= 0) {
                throw new IllegalArgumentException("Person id must be positive");
            }
            personDAO.deletePerson(id);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting person with id " + id, e);
        }
    }
}
