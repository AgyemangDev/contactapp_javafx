package fr.isen.java2;

import fr.isen.java2.dao.PersonDAO;
import fr.isen.java2.db.DatabaseManager;
import fr.isen.java2.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


public class PersonDAOTest {
    private final PersonDAO personDAO = new PersonDAO();

    @BeforeEach
    void initDb() throws Exception {
        Connection connection = DatabaseManager.getConnection();
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS person (\n" +
                        "                                      idperson INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                        "                                      lastname VARCHAR(45) NOT NULL,\n" +
                        "    firstname VARCHAR(45) NOT NULL,\n" +
                        "    nickname VARCHAR(45) NOT NULL,\n" +
                        "    phone_number VARCHAR(15) NULL,\n" +
                        "    address VARCHAR(200) NULL,\n" +
                        "    email_address VARCHAR(150) NULL,\n" +
                        "    birth_date DATE NULL);");
        stmt.executeUpdate("DELETE FROM person");
        stmt.executeUpdate("DELETE FROM sqlite_sequence WHERE name='person'");
        stmt.executeUpdate("""
                INSERT INTO person (lastname, firstname, nickname, phone_number, address, email_address, birth_date)
                VALUES
                ('Smith', 'John', 'JS', '1111111111', 'Street 1', 'john1@mail.com', '2000-01-01'),
                ('Brown', 'Alice', 'AB', '2222222222', 'Street 2', 'alice@mail.com', '1999-02-02'),
                ('Taylor', 'Robert', 'RT', '3333333333', 'Street 3', 'robert@mail.com', '1998-03-03')
                """);
        stmt.close();
        connection.close();
    }

    @Test
    public void shouldListPersons() {
        //act
        var persons = personDAO.findAll();

        //assert
        assert (!persons.isEmpty());
        assertEquals(3, persons.size());
        assertEquals(1, persons.get(0).getIdperson());
        assertEquals("Smith", persons.get(0).getLastName());
        assertEquals(2, persons.get(1).getIdperson());
        assertEquals("Brown", persons.get(1).getLastName());
        assertEquals(3, persons.get(2).getIdperson());
        assertEquals("Taylor", persons.get(2).getLastName());

    }

    @Test
    public void shouldDeletePerson() {
        //act
        personDAO.deletePerson(2);

        //assert
        var persons = personDAO.findAll();
        assert (!persons.isEmpty());
        assertEquals(2, persons.size());
        assertEquals(1, persons.get(0).getIdperson());
        assertEquals("Smith", persons.get(0).getLastName());
        assertEquals(3, persons.get(1).getIdperson());
        assertEquals("Taylor", persons.get(1).getLastName());

    }

    @Test
    public void shouldUpdatePerson() {

        // Arrange
        var persons = personDAO.findAll();
        assert (!persons.isEmpty());

        var personToUpdate = persons.getFirst();

        // Modify data
        personToUpdate.setLastName("UpdatedName");
        personToUpdate.setFirstName("UpdatedFirstName");
        personToUpdate.setNickname("UPD");
        personToUpdate.setBirthDate(LocalDate.of(1990, 1, 1));

        // Act
        personDAO.updatePerson(personToUpdate);

        // Assert
        var updatedList = personDAO.findAll();

        assertEquals(3, updatedList.size());

        var updatedPerson = updatedList.getFirst();

        assertEquals("UpdatedName", updatedPerson.getLastName());
        assertEquals("UpdatedFirstName", updatedPerson.getFirstName());
        assertEquals("UPD", updatedPerson.getNickname());
        assertEquals("1111111111", updatedPerson.getPhoneNumber());
        assertEquals("Street 1", updatedPerson.getAddress());
        assertEquals("john1@mail.com", updatedPerson.getEmailAddress());
        assertEquals(LocalDate.of(1990, 1, 1), updatedPerson.getBirthDate());
    }
    @Test
    void shouldFailUpdateWhenFirstnameIsNull() {
        var person = personDAO.findAll().getFirst();
        person.setFirstName(null);

        assertThrows(RuntimeException.class, () -> {
            personDAO.updatePerson(person);
        });
    }
    @Test
    void shouldCreatePerson() {

        // Arrange
        var newPerson = new Person();
        newPerson.setLastName("Walker");
        newPerson.setFirstName("Paul");
        newPerson.setNickname("PW");
        newPerson.setPhoneNumber("4444444444");
        newPerson.setAddress("Street 4");
        newPerson.setEmailAddress("paul@mail.com");
        newPerson.setBirthDate(LocalDate.of(1995, 5, 5));

        // Act
        personDAO.createPerson(newPerson);

        // Assert
        var persons = personDAO.findAll();

        assertEquals(4, persons.size());
        assertEquals(4, newPerson.getIdperson());

        var inserted = persons.getLast();

        assertEquals("Walker", inserted.getLastName());
        assertEquals("Paul", inserted.getFirstName());
        assertEquals("PW", inserted.getNickname());
        assertEquals("4444444444", inserted.getPhoneNumber());
        assertEquals("Street 4", inserted.getAddress());
        assertEquals("paul@mail.com", inserted.getEmailAddress());
        assertEquals(LocalDate.of(1995, 5, 5), inserted.getBirthDate());
    }

    @Test
    void shouldFailCreateWhenLastnameIsNull() {

        var newPerson = new fr.isen.java2.model.Person();
        newPerson.setLastName(null); // NOT NULL column
        newPerson.setFirstName("Test");
        newPerson.setNickname("TT");

        assertThrows(RuntimeException.class, () -> {
            personDAO.createPerson(newPerson);
        });
    }
}
