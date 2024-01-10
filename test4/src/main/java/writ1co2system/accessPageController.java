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

    //set all our FXML variables for the page
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
    
    //on button click we write the data to the file
    @FXML
    private void writeData() throws IOException 
    {
        //get the user inputs
        String userID = txtUserid.getText();
        String postcode = txtPostCode.getText();
        float co2Concentration = Float.parseFloat(txtCo2Conc.getText());

        clientHandler handler = new clientHandler(userID); // Create an instance

        // Call the storeDataCSV method
        boolean success = handler.storeDataCSV(postcode, co2Concentration);

        //inform the user on the state 
        if (success) {
            txtMessage.setText("Data stored successfully!");
            txtMessage.setStyle("-fx-text-fill: red;");
        } else {
            txtMessage.setText("Failed to store data.");
            txtMessage.setStyle("-fx-text-fill: red;");
        }
    }

    //on button click they disconnect from server
    @FXML
    private void serverDisconnect() throws IOException
    {
        //access the server object
        MyServer server = App.getServerInstance();
        if(server !=null)
        {
            //call the method in the server calss which disconnects a client
            server.disconnectClient();
        }

        Stage currentStage = (Stage) btnServerDisconnect.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("startPage.fxml"));
        Scene scene = new Scene(root);

        //take the user back to the home screen
        currentStage.setScene(scene);
        currentStage.show();
    }

    //on button click takes the user to the data file
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