package writ1co2system;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

public class csvViewController 
{
    //FXML variables for this page
    @FXML
    private Button btnReadFile;

    @FXML
    private TextArea txtCsvContents;

    @FXML
    private Button btnDisconnect;
    
    //allow the user to view the contents of the data file
    @FXML
    private void ReadDataFile() throws IOException
    {
        StringBuilder content = new StringBuilder();

        //create a buffer reader to allow us to read file
        try (BufferedReader reader = new BufferedReader(new FileReader("dataFile.txt"))) {
            String line;
            //read line by line
            while ((line = reader.readLine()) != null) {
                // Append each line to the StringBuilder
                content.append(line).append("\n"); 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        txtCsvContents.setText(content.toString()); // Set the content to TextArea
    }

    
    @FXML
    private void serverDisconnect() throws IOException
    {
        MyServer server = App.getServerInstance();
        if(server !=null)
        {
            server.disconnectClient();
        }

        Stage currentStage = (Stage) btnDisconnect.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("startPage.fxml"));
        Scene scene = new Scene(root);

        currentStage.setScene(scene);
        currentStage.show();
    }
    
    
}
