/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author dkasakow
 */
public class DetailController implements Initializable {

    Stage stage;
    Model model;

    @FXML
    private VBox eventList;
    @FXML
    private AnchorPane rootPane;

    public void fillDetails(String ort) {
        List<List<String>> events = new ArrayList<>();
        events = Model.getEvents(ort);

        for (List<String> event : events) {

            for (int i = 0; i < event.size(); i++) {
                Label detail = new Label(event.get(i));
                eventList.getChildren().add(detail);
            }
            eventList.getChildren().add(new VBox());
        }
        
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        
    }

}
