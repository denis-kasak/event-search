/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;
import java.util.UUID;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.LocalTimeStringConverter;

/**
 *
 * @author d-kas
 */
public class TerminController implements Initializable {

    Stage stage;
    @FXML
    private TextField txtTitel;
    @FXML
    private TextField txtAdresse;
    @FXML
    private ComboBox cbDauer;
    @FXML
    private Spinner<String> spinnerTime;
    @FXML
    private DatePicker datePicker;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        SpinnerValueFactory valueFactory = new SpinnerValueFactory<LocalTime>() {
            {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                setConverter(new LocalTimeStringConverter(formatter, null));
            }

            @Override
            public void decrement(int steps) {
                if (getValue() == null) {
                    setValue(LocalTime.now());
                } else {
                    LocalTime time = (LocalTime) getValue();
                    setValue(time.minusMinutes(steps));
                }
            }

            @Override
            public void increment(int steps) {
                if (this.getValue() == null) {
                    setValue(LocalTime.now());
                } else {
                    LocalTime time = (LocalTime) getValue();
                    setValue(time.plusMinutes(steps));
                }
            }

        };
        spinnerTime.setValueFactory(valueFactory);

        cbDauer.setItems(FXCollections.observableArrayList("0,5 Stunden", "1 Stunde", "1,5 Stunden", "2 Stunden", "2,5 Stunden", "3 Stunden"));
    }

    public void createEvent(String titel, String ort, LocalDate sDate, LocalTime sTime, String dauer) {
    	
    	sTime=sTime.minusHours(2);
    	
    	Object[] eDateTime = addHours(sDate, sTime, dauer);
    	
    	LocalDate eDate = (LocalDate) eDateTime[0];
    	LocalTime eTime = (LocalTime) eDateTime[1];
    	  	
    	String nsDate = sDate.toString();
    	nsDate = nsDate.replace("-", ""); //aus YYYY-MM-DD mach YYYYMMDD
    	String neDate = eDate.toString();
    	neDate = neDate.replace("-", "");
    	String nsTime = sTime.toString();
    	nsTime = nsTime.replace(":", "") + "00"; //aus HH:MM mach HHMMSS
    	String neTime = eTime.toString();
    	neTime = neTime.replace(":", "") + "00";
    	
    	Instant instant= Instant.now();
    	ZoneId zone = ZoneId.of("UTC");
    	LocalDate stampDate = LocalDate.ofInstant(instant, zone);
    	LocalTime stampTime = LocalTime.ofInstant(instant, zone).truncatedTo(ChronoUnit.SECONDS);
    	LocalTime stampTimeModified = stampTime;
    	
    	String stampD = stampDate.toString();
    	stampD = stampD.replace("-", "");
    	
    	String stampT = stampTime.toString();
    	stampT = stampT.replace(":","");
    	
    	String stampTM = stampTimeModified.toString();
    	stampTM = stampTM.replace(":", "");
    	
        
    	String event = "BEGIN:VCALENDAR"
        		+ System.lineSeparator() + "PRODID:-//Uni//Event Search//EN"
        		+ System.lineSeparator() + "VERSION:2.0"
        		+ System.lineSeparator() + "CALSCALE:GREGORIAN"
        		+ System.lineSeparator() + "METHOD:PUBLISH"
                + System.lineSeparator() + "X-WR-TIMEZONE:UTC+2"
                //+ System.lineSeparator() + "X-WR-CALNAME:Event Search"
                + System.lineSeparator() + "BEGIN:VEVENT"
                //+ System.lineSeparator() + "UID:"+UUID.randomUUID().toString()
                //+ System.lineSeparator() + "DTSTAMP:"+stampD+"T"+stampT+"Z"
                //+ System.lineSeparator() + "CREATED:"+stampD+"T"+stampT+"Z"
                //+ System.lineSeparator() + "LAST-MODIFIED"+stampD+"T"+stampTM+"Z"
                + System.lineSeparator() + "DTSTART:"+nsDate+"T"+nsTime+"Z"
                + System.lineSeparator() + "DTEND:"+neDate+"T"+neTime+"Z"
                + System.lineSeparator() + "SUMMARY:" + titel
                + System.lineSeparator() + "LOCATION:" + ort
                //+ System.lineSeparator() + "SEQUENCE:0"
                //+ System.lineSeparator() + "STATUS:CONFIRMED"
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

    private Object[] addHours(LocalDate sDate, LocalTime sTime, String dauer) {
    	LocalTime eTime = sTime;
    	if(dauer.charAt(1)==',') { //Dauer ist H,5
    		eTime =eTime.plusHours(Character.getNumericValue(dauer.charAt(0)));
    		eTime = eTime.plusMinutes(30);
    	}else {//Dauer ist nur H
    		eTime.plusHours(Character.getNumericValue(dauer.charAt(0)));
    	}
    	
    	LocalDate eDate = sDate;
    	if(0<sTime.compareTo(eTime)) {//Ende ist einen Tag später
    		eDate = eDate.plusDays(1);
    	}
    	
    	Object[] dateTime = {eDate, eTime};
    	return dateTime;
    }
    
    @FXML
    private void saveTermin() {
        String titel = txtTitel.getText();
        String adresse = txtAdresse.getText();
        LocalDate date = datePicker.getValue();
        Object time = spinnerTime.getValue();
        String dauer = (String) cbDauer.getValue();
        createEvent(titel, adresse, date, (LocalTime) time, dauer);
    }

    public void fillDetails(String ort, String titel) {
        txtTitel.setText(titel);

        String adress = ort + ", " +Model.getAdress(ort);
        txtAdresse.setText(adress);
    }
}
