package fr.isen.java2.service;

import fr.isen.java2.dao.PersonDAO;
import fr.isen.java2.model.Person;

import java.sql.SQLException;
import java.util.List;

public class PersonService {
    private final PersonDAO personDAO = new PersonDAO();

    public List<Person> getAllPersons() throws SQLException {
        return personDAO.findAll();
    }

//    public void addPerson(Person p) throws SQLException {
//        personDAO.insert(p);
//    }
//
//    public void updatePerson(Person p) throws SQLException {
//        personDAO.update(p);
//    }
//
//    public void deletePerson(int id) throws SQLException {
//        personDAO.delete(id);
//    }
}
