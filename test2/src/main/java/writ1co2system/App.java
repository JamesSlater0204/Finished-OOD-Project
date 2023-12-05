package writ1co2system;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    private static MyServer serverInstance;

    @Override
    public void start(Stage stage) throws IOException {

        //creating an instance of the server
        Thread serverThread = new Thread(() -> {
            serverInstance = new MyServer();
            serverInstance.startServer();
        });
        serverThread.setDaemon(true); // Set as daemon so it doesn't prevent JVM shutdown
        serverThread.start();

        // scene = new Scene(loadFXML("startPage"), 640, 480);
        // stage.setScene(scene);
        // stage.show();



        Scene scene1 = new Scene(loadFXML("startPage"));
        Scene scene2 = new Scene(loadFXML("startPage"));
        Scene scene3 = new Scene(loadFXML("startPage"));
        Scene scene4 = new Scene(loadFXML("startPage"));
        Scene scene5 = new Scene(loadFXML("startPage"));

        // Create stages to simulate each client connection
        Stage stage1 = new Stage();
        stage1.setScene(scene1);

        Stage stage2 = new Stage();
        stage2.setScene(scene2);

        Stage stage3 = new Stage();
        stage3.setScene(scene3);

        Stage stage4 = new Stage();
        stage4.setScene(scene4);

        Stage stage5 = new Stage();
        stage5.setScene(scene5);

        // Show all stages
        stage1.show();
        stage2.show();
        stage3.show();
        stage4.show();
        stage5.show();
    }

    public static MyServer getServerInstance()
    {
        return serverInstance;
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}