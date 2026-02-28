package fr.isen.java2.view;

import fr.isen.java2.App;
import fr.isen.java2.model.Person;
import fr.isen.java2.service.PersonService;
import fr.isen.java2.util.PersonSession;
import fr.isen.java2.util.PersonValueFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.*;
import java.util.stream.Collectors;

public class MainController {

    @FXML
    private TableView<Person> personTable;

    @FXML
    private TableColumn<Person, Integer> idColumn;
    @FXML
    private TableColumn<Person, String> photoColumn;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;
    @FXML
    private TableColumn<Person, String> phoneColumn;
    @FXML
    private TableColumn<Person, String> emailColumn;
    @FXML
    private TableColumn<Person, String> addressColumn;
    @FXML
    private TableColumn<Person, Date> birthDateColumn;
    @FXML
    private TableColumn<Person, Void> actionColumn;

    @FXML
    private Label contactCountLabel;
    @FXML
    private TextField searchField;

    private final PersonService personService = new PersonService();

    private final Map<String, Image> imageCache = new HashMap<>();

    private List<Person> cachedPersons = new ArrayList<>();

    @FXML
    public void initialize() {
        setupTableColumns();
        setupPhotoColumn();
        setupActionColumn();
        refreshData();
        setupSearch();
        personTable.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        personTable.setPlaceholder(new Label("No contacts found."));
    }

    public void refreshData() {
        cachedPersons = personService.getAllPersons();
        imageCache.clear();
        personTable.getItems().setAll(cachedPersons);
        updateContactCount();
    }

    private void updateContactCount() {
        contactCountLabel.setText(
                "Total Contacts: " + personTable.getItems().size()
        );
    }

    private void setupTableColumns() {

        idColumn.setCellValueFactory(new PersonValueFactory<>("idperson"));
        firstNameColumn.setCellValueFactory(new PersonValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PersonValueFactory<>("lastName"));
        phoneColumn.setCellValueFactory(new PersonValueFactory<>("phoneNumber"));
        emailColumn.setCellValueFactory(new PersonValueFactory<>("emailAddress"));
        addressColumn.setCellValueFactory(new PersonValueFactory<>("address"));
        birthDateColumn.setCellValueFactory(new PersonValueFactory<>("birthDate"));
    }

    private void setupPhotoColumn() {

        photoColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(
                        cellData.getValue().getPhotoPath()
                )
        );

        photoColumn.setCellFactory(col -> new TableCell<>() {

            private final ImageView imageView = new ImageView();

            {
                imageView.setFitWidth(50);
                imageView.setFitHeight(50);
                imageView.setPreserveRatio(true);
            }

            @Override
            protected void updateItem(String photoPath, boolean empty) {
                super.updateItem(photoPath, empty);

                if (empty || photoPath == null) {
                    setGraphic(null);
                    return;
                }

                Image image = imageCache.computeIfAbsent(photoPath, path -> {
                    try {

                        if (path.startsWith("file:") || path.startsWith("http")) {
                            return new Image(path, 50, 50, true, true);
                        }

                        return new Image(
                                getClass().getResource("/" + path).toExternalForm(),
                                50,
                                50,
                                true,
                                true
                        );

                    } catch (Exception e) {
                        return new Image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQeK76ON20FIbTr9u4z9xIJBCxjNr_2xswX-oWSAkiFbFCBHrN15jj_6oCsDVDiyUdN5PSFPln76JXdpgTkZp0WlL-ODuCbIRv3i2lFEK87AA&s=10");
                    }
                });

                imageView.setImage(image);
                setGraphic(imageView);
            }
        });
    }

    private void setupActionColumn() {

        actionColumn.setCellFactory(col -> new TableCell<>() {

            private final Button editButton = createEditButton();
            private final Button deleteButton = createDeleteButton();
            private final HBox box = new HBox(8, editButton, deleteButton);

            {
                editButton.setOnAction(e -> {
                    Person person = getTableView().getItems().get(getIndex());
                    handleEdit(person);
                });

                deleteButton.setOnAction(e -> {
                    Person person = getTableView().getItems().get(getIndex());
                    handleDelete(person);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : box);
            }
        });
    }

    private Button createEditButton() {
        Button btn = new Button("Edit");
        btn.getStyleClass().add("btn-edit");
        return btn;
    }

    private Button createDeleteButton() {
        Button btn = new Button("Delete");
        btn.getStyleClass().add("btn-delete");
        return btn;
    }

    private void handleEdit(Person person) {
        PersonSession.editingPerson = person;
        App.showView("PersonForm");
    }

    private void handleDelete(Person person) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete contact");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("Delete " + person.getFirstName() + "?");

        if (alert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
            personService.deletePerson(person.getIdperson());
            refreshData();
        }
    }

    @FXML
    private void handleAdd() {
        PersonSession.editingPerson = null;
        App.showView("PersonForm");
    }

    @FXML
    private void handleHome() {
        App.showView("Home");
        refreshData();
    }

    private void setupSearch() {

        searchField.textProperty().addListener((obs, oldVal, newVal) -> {

            if (newVal == null || newVal.isBlank()) {
                personTable.getItems().setAll(cachedPersons);
                updateContactCount();
                return;
            }

            String search = newVal.toLowerCase();

            List<Person> filtered = cachedPersons.stream()
                    .filter(p ->
                            safe(p.getFirstName()).toLowerCase().contains(search) ||
                                    safe(p.getLastName()).toLowerCase().contains(search) ||
                                    safe(p.getNickname()).toLowerCase().contains(search) ||
                                    safe(p.getPhoneNumber()).toLowerCase().contains(search) ||
                                    safe(p.getEmailAddress()).toLowerCase().contains(search) ||
                                    safe(p.getAddress()).toLowerCase().contains(search) ||
                                    (p.getBirthDate() != null &&
                                            p.getBirthDate().toString().contains(search))
                    )
                    .collect(Collectors.toList());

            personTable.getItems().setAll(filtered);
            updateContactCount();
        });
    }

    private String safe(String s) {
        return s == null ? "" : s;
    }
}