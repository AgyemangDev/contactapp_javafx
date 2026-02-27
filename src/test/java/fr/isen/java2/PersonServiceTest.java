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
        person.setBirthDate(java.time.LocalDate.of(2000, 1, 1));
        person.setPhoto(null);

        personService.createPerson(person);

        assertEquals("SmithTrim", person.getLastName());
        assertEquals("JohnTrim", person.getFirstName());
        assertEquals("JST", person.getNickname());

    }
}