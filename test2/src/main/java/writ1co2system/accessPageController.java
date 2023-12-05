package writ1co2system;

import java.io.IOException;
import java.net.Socket;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

public class accessPageController {
    @FXML
    private TextField txtUserid;

    @FXML
    private TextField txtPostCode;

    @FXML
    private TextField txtCo2Conc;

    @FXML
    private Label txtMessage;

    @FXML
    private Button btnServerDisconnect;

    @FXML
    private Button btnViewCsv;
    
    @FXML
    private void writeData() throws IOException 
    {
        String userID = txtUserid.getText();
        String postcode = txtPostCode.getText();
        float co2Concentration = Float.parseFloat(txtCo2Conc.getText());

        clientHandler handler = new clientHandler(userID); // Create an instance

        // Call the storeDataCSV method
        boolean success = handler.storeDataCSV(postcode, co2Concentration);

        if (success) {
            txtMessage.setText("Data stored successfully!");
            txtMessage.setStyle("-fx-text-fill: red;");
        } else {
            txtMessage.setText("Failed to store data.");
            txtMessage.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    private void serverDisconnect() throws IOException
    {
        MyServer server = App.getServerInstance();
        if(server !=null)
        {
            server.disconnectClient();
        }

        Stage currentStage = (Stage) btnServerDisconnect.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("startPage.fxml"));
        Scene scene = new Scene(root);

        currentStage.setScene(scene);
        currentStage.show();
    }

    @FXML
    private void goToCsv() throws IOException
    {
        Stage currentStage = (Stage) btnViewCsv.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("csvView.fxml"));
        Scene scene = new Scene(root);

        currentStage.setScene(scene);
        currentStage.show();
    }
}