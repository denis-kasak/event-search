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
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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
    private AnchorPane rootPane;
    @FXML
    private Scene scene;
    @FXML
    private Label veranstalterName;
    @FXML
    private ScrollPane scrollPane;

    public void fillDetails(String ort) {
        veranstalterName.setText(ort);
        List<List<String>> events = new ArrayList<>();
        events = Model.getEvents(ort);
        
        VBox eventList = new VBox();

        for (List<String> event : events) {

            for (int i = 0; i < event.size(); i++) {
                Label detail = new Label(event.get(i));

                if (i == 0) {
                    detail.getStyleClass().add("label-EventTitel");
                } else {
                    detail.getStyleClass().add("label-EventDetails");
                }
                eventList.getChildren().add(detail);
            }
        }
        AnchorPane anchorEvents = new AnchorPane();
        anchorEvents.getChildren().add(eventList);
        scrollPane.setContent(anchorEvents);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
