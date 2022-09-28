/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author dkasakow
 */
public class DetailController implements Initializable {

    private HostServices hostServices;
    @FXML
    private Label veranstalterName;
    @FXML
    private ScrollPane scrollPane;

    public void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices;
    }

    public void fillDetails(String ort) {
        veranstalterName.setText(ort);
        List<List<String>> events = new ArrayList<>();
        events = Model.getEvents(ort);

        VBox eventList = new VBox();

        for (List<String> event : events) {

            for (int i = 0; i < event.size(); i++) {//Details hinzufügen
                Label detail = new Label(event.get(i));

                if (i == 0) {
                    detail.getStyleClass().add("label-EventTitel");
                } else {
                    detail.getStyleClass().add("label-EventDetails");
                }
                detail.setWrapText(true);
                detail.setMaxWidth(600);
                eventList.getChildren().add(detail);
            }
            Hyperlink termin = new Hyperlink();
            termin.setText("Termin erstellen");
            termin.getStyleClass().add("hyper-link-termin");
            termin.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {

                    try {
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(App.class.getResource("TerminView.fxml"));
                        Scene scene = new Scene(loader.load());

                        TerminController t = loader.getController();
                        t.fillDetails(ort, event.get(0));
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.getIcons().add(new Image(App.class.getResourceAsStream("logo.png")));
                        stage.setTitle("Terminerstellung");
                        stage.show();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                }
            });
            eventList.getChildren().add(termin);
        }

        String adresse = Model.getAdress(ort);
        Label text = new Label("Adresse:");
        text.getStyleClass().add("label-EventTitel");
        eventList.getChildren().add(text);
        for (int i = 0; i < adresse.length(); i++) {
            if (adresse.charAt(i) == ',') {
                Label detail = new Label(adresse.substring(0, i));
                detail.getStyleClass().add("label-Adresse");
                eventList.getChildren().add(detail);
                adresse = adresse.substring(i + 1).trim();
                i = -1;
                continue;
            } else if (i == adresse.length() - 1) {
                Label detail = new Label(adresse.substring(0));
                detail.getStyleClass().add("label-Adresse");
                eventList.getChildren().add(detail);
            }
        }

        while (adresse.contains(",")) {//in do while umwandeln
            Label detail = new Label(adresse.substring(0, adresse.indexOf(",")));
            adresse = adresse.substring(adresse.indexOf(","));

        }

        Hyperlink weblink = new Hyperlink();
        weblink.setText("Zur Webseite");
        weblink.getStyleClass().add("hyper-link-info");
        weblink.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                hostServices.showDocument(Model.getLink(ort));
            }
        });
        eventList.getChildren().add(weblink);
        AnchorPane anchorEvents = new AnchorPane();
        anchorEvents.getChildren().add(eventList);
        scrollPane.setContent(anchorEvents);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
