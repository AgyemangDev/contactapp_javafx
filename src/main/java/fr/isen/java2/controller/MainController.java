package fr.isen.java2.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

public class MainController {

    @FXML
    private TableView<?> personTable;

    @FXML
    private Button addButton, updateButton, deleteButton;

    @FXML
    public void initialize() {
        // Here later we will populate table from DB
        System.out.println("Controller initialized");
    }

    @FXML
    private void handleAdd() {
        System.out.println("Add button clicked");
    }

    @FXML
    private void handleUpdate() {
        System.out.println("Update button clicked");
    }

    @FXML
    private void handleDelete() {
        System.out.println("Delete button clicked");
    }
}