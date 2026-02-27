package fr.isen.java2.view;

import fr.isen.java2.App;
import fr.isen.java2.model.Person;
import fr.isen.java2.service.PersonService;
import fr.isen.java2.util.PersonSession;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;

public class PersonFormController {

    private final PersonService personService = new PersonService();

    @FXML
    private Label formTitle;

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField nicknameField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField addressField;

    @FXML
    private DatePicker birthDatePicker;
    @FXML
    private ImageView photoView;

    private String selectedPhotoPath;

    private Person person;
    private boolean editingMode;

    @FXML
    public void initialize() {
        loadEditingPerson();
    }

    private void loadEditingPerson() {

        person = PersonSession.editingPerson;
        editingMode = person != null;

        if (editingMode) {

            formTitle.setText("Edit Person");

            firstNameField.setText(person.getFirstName());
            lastNameField.setText(person.getLastName());
            nicknameField.setText(person.getNickname());
            phoneField.setText(person.getPhoneNumber());
            emailField.setText(person.getEmailAddress());
            addressField.setText(person.getAddress());
            birthDatePicker.setValue(person.getBirthDate());

            selectedPhotoPath = person.getPhotoPath();

            if (selectedPhotoPath != null && !selectedPhotoPath.isBlank()) {

                try {

                    Image image;

                    if (selectedPhotoPath.startsWith("file:") ||
                            selectedPhotoPath.startsWith("http")) {

                        image = new Image(selectedPhotoPath);

                    } else {

                        var resource = getClass().getResource("/" + selectedPhotoPath);

                        if (resource == null) {
                            throw new IllegalArgumentException("Image resource not found");
                        }

                        image = new Image(resource.toExternalForm());
                    }

                    photoView.setImage(image);

                } catch (Exception e) {
                    photoView.setImage(null);
                }
            }

        } else {
            formTitle.setText("Add Person");
            person = new Person();
            selectedPhotoPath = null;
        }
    }

    @FXML
    private void handleSave() {

        if (!validateForm()) return;

        person.setFirstName(firstNameField.getText().trim());
        person.setLastName(lastNameField.getText().trim());
        person.setNickname(nicknameField.getText().trim());
        person.setPhoneNumber(phoneField.getText().trim());
        person.setEmailAddress(emailField.getText().trim());
        person.setAddress(addressField.getText().trim());
        person.setBirthDate(birthDatePicker.getValue());

        person.setPhotoPath(selectedPhotoPath);

        if (editingMode) {
            personService.updatePerson(person);
        } else {
            personService.createPerson(person);
        }

        App.showView("MainView");
    }

    private boolean validateForm() {

        if (firstNameField.getText().isBlank()) {
            alert("First name required");
            return false;
        }

        if (lastNameField.getText().isBlank()) {
            alert("Last name required");
            return false;
        }

        if (nicknameField.getText().isBlank()) {
            alert("Nickname required");
            return false;
        }

        return true;
    }

    @FXML
    private void handleCancel() {
        App.showView("MainView");
    }

    @FXML
    private void handleSelectImage() {

        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter(
                        "Images",
                        "*.png", "*.jpg", "*.jpeg"
                )
        );

        File file = chooser.showOpenDialog(null);

        if (file == null) return;

        selectedPhotoPath = file.toURI().toString();

        Image image = new Image(selectedPhotoPath);
        photoView.setImage(image);
    }

    private void alert(String msg) {
        new Alert(Alert.AlertType.WARNING, msg).showAndWait();
    }

    @FXML
    private void handleBack() {
        App.showView("MainView");
    }
}