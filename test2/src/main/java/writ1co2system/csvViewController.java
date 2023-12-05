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
    @FXML
    private Button btnReadFile;

    @FXML
    private TextArea txtCsvContents;

    @FXML
    private Button btnDisconnect;
    
    @FXML
    private void ReadDataFile() throws IOException
    {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader("dataFile.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n"); // Append each line to the StringBuilder
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
