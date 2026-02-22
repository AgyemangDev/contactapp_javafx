package fr.isen.java2.view;

import fr.isen.java2.model.Person;
import fr.isen.java2.service.PersonService;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Date;

public class MainController {

    @FXML
    private TableView<Person> personTable;

    @FXML
    private TableColumn<Person, Integer> idColumn;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;
    @FXML
    private TableColumn<Person, Date> birthDateColumn;
    @FXML
    private TableColumn<Person, String> nicknameColumn;
    @FXML
    private TableColumn<Person, String> phoneColumn;
    @FXML
    private TableColumn<Person, String> emailColumn;
    @FXML
    private TableColumn<Person, String> addressColumn;
    private final PersonService personService = new PersonService();

    private void refreshList() {
        personTable.refresh();
        personTable.getSelectionModel().clearSelection();
    }

    private void populateList() {

        personTable.getItems().setAll(personService.getAllPersons());
        refreshList();
    }
    private void setupTableColumns(){
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idperson"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        nicknameColumn.setCellValueFactory(new PropertyValueFactory<>("nickname"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("emailAddress"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        birthDateColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
    }

    @FXML
    public void initialize() {

        setupTableColumns();
        populateList();



//        // Add Actions column (Edit/Delete buttons per row)
//        TableColumn<Person, Void> actionCol = new TableColumn<>("Actions");
//        actionCol.setPrefWidth(150);
//
//        Callback<TableColumn<Person, Void>, TableCell<Person, Void>> cellFactory = new Callback<>() {
//            @Override
//            public TableCell<Person, Void> call(final TableColumn<Person, Void> param) {
//                return new TableCell<>() {
//
//                    private final Button btnEdit = new Button("Edit");
//                    private final Button btnDelete = new Button("Delete");
//                    private final HBox pane = new HBox(5, btnEdit, btnDelete);
//
//                    {
//                        btnEdit.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
//                        btnDelete.setStyle("-fx-background-color: #F44336; -fx-text-fill: white;");
//
//                        // Open modal to edit person
//                        btnEdit.setOnAction(e -> {
//                            Person person = getTableView().getItems().get(getIndex());
//                            showPersonForm(person);
//                        });
//
//                        // Delete logic
//                        btnDelete.setOnAction(e -> {
//                            Person person = getTableView().getItems().get(getIndex());
//                            System.out.println("Delete: " + person.getFirstName());
//                            // TODO: Delete from DB and refresh table
//                            getTableView().getItems().remove(person);
//                        });
//                    }
//
//                    @Override
//                    protected void updateItem(Void item, boolean empty) {
//                        super.updateItem(item, empty);
//                        if (empty) {
//                            setGraphic(null);
//                        } else {
//                            setGraphic(pane);
//                        }
//                    }
//                };
//            }
//        };
//
//        actionCol.setCellFactory(cellFactory);
//        personTable.getColumns().add(actionCol);
    }

    /**
     * Show the modal for adding/editing a Person
     */
//    private void showPersonForm(Person person) {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PersonForm.fxml"));
//            Parent root = loader.load();
//
//            PersonFormController controller = loader.getController();
//
//            Stage stage = new Stage();
//            stage.setTitle(person == null ? "Add Person" : "Edit Person");
//            stage.initModality(Modality.WINDOW_MODAL);
//            stage.initOwner(addButton.getScene().getWindow());
//            stage.setScene(new Scene(root));
//
//            controller.setDialogStage(stage);
//            controller.setPerson(person);
//
//            stage.showAndWait();
//
//            if (controller.isSaved()) {
//                Person savedPerson = controller.getPerson();
//                if (person == null) {
//                    // New person - add to table
//                    personTable.getItems().add(savedPerson);
//                    System.out.println("New person added: " + savedPerson.getFirstName());
//                    // TODO: Add to DB
//                } else {
//                    // Existing person - refresh table
//                    personTable.refresh();
//                    System.out.println("Person updated: " + savedPerson.getFirstName());
//                    // TODO: Update in DB
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    /**
//     * Handle Add button (opens modal for new Person)
//     */
//    @FXML
//    private void handleAdd() {
//        showPersonForm(null);
//    }
}