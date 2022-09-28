/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author d-kas
 */
public class TerminController implements Initializable{

    Stage stage;
    @FXML private TextField txtTitel;
    @FXML private TextField txtAdresse;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    
    public void createEvent(String titel, String beschreibung, String datum, String dauer, String ort) {
        String event = "BEGIN:VCALENDAR\n"
                + System.lineSeparator() + "VERSION:2.0"
                + System.lineSeparator() + "PRODID:-//ABC Corporation//NONSGML My Product//EN"
                + System.lineSeparator() + "BEGIN:VEVENT"
                + System.lineSeparator() + "SUMMARY:" + titel
                + System.lineSeparator() + "DTSTART;TZID=America/New_York:20160420T120000"
                + System.lineSeparator() + "DURATION:PT1H"
                + System.lineSeparator() + "DESCRIPTION:" + beschreibung
                + System.lineSeparator() + "LOCATION:" + ort
                + System.lineSeparator() + "END:VEVENT"
                + System.lineSeparator() + "END:VCALENDAR";

        FileChooser fileChooser = new FileChooser();

        //Set extension filter for text files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("ICS files (*.ics)", "*.ics");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            try {
                PrintWriter writer;
                writer = new PrintWriter(file);
                writer.println(event);
                writer.close();
            } catch (IOException ex) {
                System.out.println("Couldnt save file");
            }
        }

    }

    void fillDetails(String ort, String titel) {
        txtTitel.setText(titel);
        
        String adress = Model.getAdress(ort);
        txtAdresse.setText(adress);
    }
}
