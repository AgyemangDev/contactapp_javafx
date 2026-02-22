package fr.isen.java2;

import fr.isen.java2.exceptions.InvalidPerson;
import fr.isen.java2.model.Person;
import fr.isen.java2.service.PersonService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        person.setLastName("  Smith  ");
        person.setFirstName("  John  ");
        person.setNickname("  JS  ");

        personService.createPerson(person);

        assertThat(person.getLastName()).isEqualTo("Smith");
        assertThat(person.getFirstName()).isEqualTo("John");
        assertThat(person.getNickname()).isEqualTo("JS");
    }
}
