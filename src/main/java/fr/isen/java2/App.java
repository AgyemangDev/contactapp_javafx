package fr.isen.java2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class App extends Application
{

    @Override
    public void init() throws Exception{
        System.out.println("Init called. Database setup can go here");
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),800,600);

        stage.setTitle("Contact App");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        System.out.println("Stop called - System close resources go here");
    }
    public static void main( String[] args )
    {
        launch();
    }
}
