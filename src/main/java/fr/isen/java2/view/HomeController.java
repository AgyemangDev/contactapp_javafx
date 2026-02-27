package fr.isen.java2.view;

import fr.isen.java2.App;
import javafx.fxml.FXML;

public class HomeController {

    @FXML
    private void handleOpenContacts() {
        App.showView("MainView");
    }
}