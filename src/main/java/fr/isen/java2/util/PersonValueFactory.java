package fr.isen.java2.util;

import fr.isen.java2.model.Person;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class PersonValueFactory<T>
        implements Callback<TableColumn.CellDataFeatures<Person, T>, ObservableValue<T>> {

    private final String field;

    public PersonValueFactory(String field) {
        this.field = field;
    }

    @Override
    public ObservableValue<T> call(TableColumn.CellDataFeatures<Person, T> param) {

        Person person = param.getValue();

        return switch (field) {
            case "idperson" -> new SimpleObjectProperty<>((T) Integer.valueOf(person.getIdperson()));
            case "firstName" -> new SimpleObjectProperty<>((T) person.getFirstName());
            case "lastName" -> new SimpleObjectProperty<>((T) person.getLastName());
            case "nickname" -> new SimpleObjectProperty<>((T) person.getNickname());
            case "phoneNumber" -> new SimpleObjectProperty<>((T) person.getPhoneNumber());
            case "emailAddress" -> new SimpleObjectProperty<>((T) person.getEmailAddress());
            case "address" -> new SimpleObjectProperty<>((T) person.getAddress());
            case "birthDate" -> new SimpleObjectProperty<>((T) person.getBirthDate());
            default -> new SimpleObjectProperty<>(null);
        };
    }
}