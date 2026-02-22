package fr.isen.java2.view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import fr.isen.java2.model.Person;

import java.io.File;

public class PersonFormController {

    @FXML private Label formTitle;
    @FXML private TextField firstNameField, lastNameField, nicknameField, phoneField, emailField, addressField;
    @FXML private DatePicker birthDatePicker;
    @FXML private ImageView photoView;
    @FXML private Button saveButton;

    private Stage dialogStage;
    private Person person; // Person being edited or new
    private boolean saved = false;
    private Image selectedImage;

    public void setDialogStage(Stage stage) {
        this.dialogStage = stage;
    }

    public void setPerson(Person person) {
        this.person = person;
        if(person != null) {
            formTitle.setText("Edit Person");
            firstNameField.setText(person.getFirstName());
            lastNameField.setText(person.getLastName());
            nicknameField.setText(person.getNickname());
            phoneField.setText(person.getPhoneNumber());
            emailField.setText(person.getEmailAddress());
            addressField.setText(person.getAddress());
            birthDatePicker.setValue(person.getBirthDate());
            
            if(person.getPhoto() != null) {
                photoView.setImage(person.getPhoto());
                selectedImage = person.getPhoto();
            }
        } else {
            formTitle.setText("Add Person");
        }
    }

    public boolean isSaved() { 
        return saved; 
    }

    public Person getPerson() {
        return person;
    }

    @FXML
    private void handleSave() {
        // Basic validation
        if(firstNameField.getText() == null || firstNameField.getText().trim().isEmpty()) {
            showAlert("Validation Error", "First name is required!");
            return;
        }
        if(lastNameField.getText() == null || lastNameField.getText().trim().isEmpty()) {
            showAlert("Validation Error", "Last name is required!");
            return;
        }

        // Create or update person
        if(person == null) {
            person = new Person();
        }
        
        person.setFirstName(firstNameField.getText().trim());
        person.setLastName(lastNameField.getText().trim());
        person.setNickname(nicknameField.getText().trim());
        person.setPhoneNumber(phoneField.getText().trim());
        person.setEmailAddress(emailField.getText().trim());
        person.setAddress(addressField.getText().trim());
        person.setBirthDate(birthDatePicker.getValue());
        
        // Set photo if one was selected
        if(selectedImage != null) {
            person.setPhoto(selectedImage);
        }
        
        saved = true;
        dialogStage.close();
    }

    @FXML
    private void handleCancel() {
        saved = false;
        dialogStage.close();
    }

    @FXML
    private void handleSelectImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Profile Photo");
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );
        
        File file = fileChooser.showOpenDialog(dialogStage);
        if(file != null) {
            try {
                Image img = new Image(file.toURI().toString());
                photoView.setImage(img);
                selectedImage = img;
            } catch (Exception e) {
                showAlert("Image Error", "Failed to load image: " + e.getMessage());
            }
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}