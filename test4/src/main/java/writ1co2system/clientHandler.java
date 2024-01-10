package writ1co2system;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class clientHandler 
{
    private String clientID;

    //constructor
    public clientHandler(String id)
    {
        this.clientID = id;
    }

    //handle the write to csv
    public boolean storeDataCSV( String postcode, float co2)
    {
        //retreive the values from the calling statement
        String co2measure = String.valueOf(co2);

        //create a data store string concatonating each piece of data in csv format
        String dataToStore = clientID+","+postcode+","+co2measure;

        //returns the state of the writeToFile method
        boolean successfull = writeToFile("dataFile.txt", dataToStore);

        //notify the other class of the outcome
        if(successfull)
        {
            return true;
        }
        else
        {
            return false;
        }        
        
    }

    private static boolean writeToFile(String fileName, String content) 
    {
        //open a new bufferreader to write to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName,true))) {
            //print a new line at the end so each piece of data is on a new line
            writer.write(content +"\n");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
