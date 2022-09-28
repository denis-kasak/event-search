/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import static uni.App.loadFXML;
import static uni.App.scene;

/**
 *
 * @author dkasakow
 */
public class DetailController implements Initializable {

    Stage stage;

    @FXML
    private VBox eventList;

    public void createDetail() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(eventList);
    }

}
