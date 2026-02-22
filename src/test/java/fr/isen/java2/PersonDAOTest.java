package fr.isen.java2;

import fr.isen.java2.dao.PersonDAO;
import fr.isen.java2.db.DatabaseManager;
import org.junit.jupiter.api.BeforeEach;


public class PersonDAOTest {
    private PersonDAO personDAO;

    @BeforeEach
    void setUp() {
        DatabaseManager.initDatabase();
        personDAO = new PersonDAO();
    }

//    @Test
//    void shouldInsertPerson() throws SQLException {
//        Person p = new Person();
//        p.setFirstname("John");
//        p.setLastname("Doe");
//        p.setNickname("JD");
//
//        personDAO.insert(p);
//
//        List<Person> persons = personDAO.findAll();
//        assertFalse(persons.isEmpty());
//    }
}
