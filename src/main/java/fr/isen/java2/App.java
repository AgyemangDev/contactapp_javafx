package fr.isen.java2;

import fr.isen.java2.db.DatabaseManager;
import fr.isen.java2.util.PersonSession;
import fr.isen.java2.view.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    private static Scene scene;
    private static BorderPane mainlayout;

    @Override
    public void init() {
        DatabaseManager.initDatabase();
    }

    @Override
    public void start(Stage stage) throws Exception {

        mainlayout = loadFXML("RootLayout");
        scene = new Scene(mainlayout, 900, 600);
        // GLOBAL CSS
        scene.getStylesheets().add(
                App.class.getResource("/css/main-view.css").toExternalForm()
        );
        scene.getStylesheets().add(
                App.class.getResource("/css/person-form.css").toExternalForm()
        );

        stage.setTitle("Contact App");
        stage.setScene(scene);
        stage.show();

        showView("Home");
    }


    private static <T> T loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }


    public static void main(String[] args) {
        launch();
    }

    public static void showView(String viewName) {
        try {

            if (viewName.equals("Home")) {
                mainlayout.setTop(null); // Remove header
                mainlayout.setBottom(null);
                mainlayout.setCenter(loadFXML("Home"));
            }

            else if (viewName.equals("MainView")) {
                BorderPane contactsView = loadFXML("MainView");
                mainlayout.setTop(contactsView.getTop());   // Header
                mainlayout.setCenter(contactsView.getCenter());
                mainlayout.setBottom(contactsView.getBottom());
            }

            else if (viewName.equals("PersonForm")) {
                BorderPane formView = loadFXML("PersonForm");

                // Keep header from contacts
                BorderPane contactsView = loadFXML("MainView");
                mainlayout.setTop(contactsView.getTop());

                mainlayout.setCenter(formView);
                mainlayout.setBottom(null);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
