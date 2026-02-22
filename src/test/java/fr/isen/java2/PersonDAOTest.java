package fr.isen.java2;

import fr.isen.java2.dao.PersonDAO;
import fr.isen.java2.db.DatabaseManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PersonDAOTest {
    private PersonDAO personDAO = new PersonDAO();

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
}
