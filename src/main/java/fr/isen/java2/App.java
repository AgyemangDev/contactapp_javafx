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
        mainlayout = loadFXML("MainView");
        scene = new Scene(mainlayout, 640, 480);

        stage.setTitle("Contact App");
        stage.setScene(scene);
        stage.show();
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
            if ("MainView".equals(viewName)) {
                mainlayout = loadFXML("MainView");
                scene.setRoot(mainlayout);
            } else {
                mainlayout.setCenter(loadFXML(viewName));
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
