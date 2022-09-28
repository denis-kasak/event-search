/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ResourceBundle;
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
    @FXML private DatePicker datePicker;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        SpinnerValueFactory valueFactory = new SpinnerValueFactory<LocalTime>() {
            {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                setConverter(new LocalTimeStringConverter(formatter,null));
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

        cbDauer.setItems(FXCollections.observableArrayList(
                new String("0,5 Stunden"),
                new String("1 Stunde"),
                new String("1,5 Stunden"),
                new String("2 Stunden"),
                new String("2,5 Stunden"),
                new String("3 Stunden")
        ));
    }

    public void createEvent(String titel, String ort, String datum,String zeit, String dauer) {
        
        datum=datum.replace("-",""); //aus YYYY-MM-DD mach YYYYMMDD
        zeit=zeit.replace(":","")+"00";
        
        if(dauer.charAt(1)==','){
            dauer = dauer.substring(0,1)+"H"+"30M";
        }else{
            dauer = dauer.substring(0,1)+"H";
        }
        
        String event = "BEGIN:VCALENDAR\n"
                + System.lineSeparator() + "VERSION:2.0"
                + System.lineSeparator() + "PRODID:-//ABC Corporation//NONSGML My Product//EN"
                + System.lineSeparator() + "BEGIN:VEVENT"
                + System.lineSeparator() + "SUMMARY:" + titel
                + System.lineSeparator() + "DTSTART;TZID=Germany/Berlin:"+datum+"T"+zeit
                + System.lineSeparator() + "DURATION:P"+dauer
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

    @FXML
    private void saveTermin() {
        String titel = txtTitel.getText();
        String adresse = txtAdresse.getText();
        LocalDate date = datePicker.getValue();
        Object time = spinnerTime.getValue();
        String dauer =(String) cbDauer.getValue();
        System.out.println(titel);
        System.out.println(adresse);
        System.out.println(date);
        System.out.println(time);
        System.out.println(dauer);
        createEvent(titel,adresse,date.toString(),time.toString(),dauer);
    }

    public void fillDetails(String ort, String titel) {
        txtTitel.setText(titel);

        String adress = Model.getAdress(ort);
        txtAdresse.setText(adress);
    }
}
