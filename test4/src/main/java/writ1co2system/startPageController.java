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

    //set the variable for  the button on the page
    @FXML
    private Button btnClient2Access1;

    @FXML
    private void connectToServer() throws IOException {
        
        MyServer server = App.getServerInstance();
        boolean hasSpace = server.hasSpaceForConnection();

        //if there is space for a new connection
        if (hasSpace) {
            //check for any new incomming connections
            Socket socket = new Socket("localhost", 5000);

            //we get the stage which the button is located
            Stage currentStage = (Stage) btnClient2Access1.getScene().getWindow();
            //get the new scene
            Parent root = FXMLLoader.load(getClass().getResource("accessPage.fxml"));
            //set the new scene
            Scene scene = new Scene(root);

            //show and direct to new scene
            currentStage.setScene(scene);
            currentStage.show();
        }
    }
}
