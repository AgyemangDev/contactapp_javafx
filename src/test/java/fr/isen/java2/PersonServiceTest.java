package fr.isen.java2;

import fr.isen.java2.exceptions.InvalidPerson;
import fr.isen.java2.model.Person;
import fr.isen.java2.service.PersonService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings({"unused"})
class PersonServiceTest {

    private final PersonService personService = new PersonService();

    @Test
    void shouldThrowWhenFirstnameIsBlank() {

        Person person = new Person();
        person.setLastName("Smith");
        person.setFirstName("");
        person.setNickname("JS");

        assertThrows(InvalidPerson.class, () ->
                personService.createPerson(person)
        );
    }

    @Test
    void shouldTrimValuesBeforeCreate() {

    Person person = new Person();
    person.setLastName("  SmithTrim  ");
    person.setFirstName("  JohnTrim  ");
    person.setNickname("  JST  ");
    person.setPhoneNumber("111");
    person.setEmailAddress("trim@test.com");
    person.setAddress("Street");
    person.setBirthDate(java.time.LocalDate.of(2000,1,1));
    person.setPhoto(null);

    personService.createPerson(person);

    assertEquals("SmithTrim", person.getLastName());
    assertEquals("JohnTrim", person.getFirstName());
    assertEquals("JST", person.getNickname());

}
    @Test
    void createPersonTest(){
        
    Person p = new Person();
    p.setFirstName("TestUnique");
    p.setLastName("User");
    p.setNickname("TU");
    p.setPhoneNumber("111");
    p.setEmailAddress("test@test.com");
    p.setAddress("Test street");
    p.setBirthDate(java.time.LocalDate.of(2000,1,1));
    p.setPhoto(null);

    personService.createPerson(p);

    assertTrue(personService.getAllPersons().stream()
            .anyMatch(x -> "TestUnique".equals(x.getFirstName())));
}
@BeforeEach
void initDb() throws Exception {
    var connection = fr.isen.java2.db.DatabaseManager.getConnection();
    var stmt = connection.createStatement();

    stmt.executeUpdate(
            "CREATE TABLE IF NOT EXISTS person (\n" +
                    "idperson INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "lastname VARCHAR(45) NOT NULL,\n" +
                    "firstname VARCHAR(45) NOT NULL,\n" +
                    "nickname VARCHAR(45) NOT NULL,\n" +
                    "phone_number VARCHAR(15) NULL,\n" +
                    "address VARCHAR(200) NULL,\n" +
                    "email_address VARCHAR(150) NULL,\n" +
                    "birth_date DATE NULL);");

    stmt.executeUpdate("DELETE FROM person");
    stmt.executeUpdate("DELETE FROM sqlite_sequence WHERE name='person'");

    stmt.close();
    connection.close();
}
}