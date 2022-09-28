/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;
import static uni.App.loadFXML;
import static uni.App.scene;

/**
 *
 * @author d-kas
 */
public class TerminController {

    Stage stage;

    public TerminController(Stage stage) throws IOException {
        stage = new Stage();
        scene = new Scene(loadFXML("TerminView"));
        stage.setScene(scene);
        stage.show();
    }

    public void initWindow(String titel, String adresse, String datum, String ort) throws IOException {

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
}
