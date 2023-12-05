package writ1co2system;

import java.io.IOException;
import java.net.Socket;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class startPageController {

    @FXML
    private Button btnClient2Access1;

    @FXML
    private void connectToServer() throws IOException {
        
        MyServer server = App.getServerInstance();
        boolean hasSpace = server.hasSpaceForConnection();

        if (hasSpace) {
            Socket socket = new Socket("localhost", 5000);

            Stage currentStage = (Stage) btnClient2Access1.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("accessPage.fxml"));
            Scene scene = new Scene(root);

            currentStage.setScene(scene);
            currentStage.show();

        
        //App.setRoot("accessPage");
        }
    }
}
