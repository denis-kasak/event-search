/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author d-kas
 */
public class MainController implements Initializable {

    @FXML
    private ToggleButton tglAlle;
    @FXML
    private ToggleButton tglMuseum;
    @FXML
    private ToggleButton tglKino;
    @FXML
    private ToggleButton tglMarkt;
    @FXML
    private AnchorPane imgRoot;
    @FXML
    private ImageView imgBerlin;
    @FXML
    private DatePicker datepicker;

    public void initialize(URL location, ResourceBundle resources) {
        //BEI NULL POINTER CHECKEN, DASS AUCH ID IN FXML GEPFLEGT IST
        imgBerlin.fitWidthProperty().bind(imgRoot.widthProperty());
        imgBerlin.fitHeightProperty().bind(imgRoot.heightProperty());

        datepicker.setValue(LocalDate.now());

    }

    @FXML
    private void search() {

        ArrayList<String> filter = new ArrayList<String>();

        if (tglAlle.isSelected()) {
            filter.add("all");
        } else {
            if (tglMuseum.isSelected()) {
                filter.add("museum");
            }
            if (tglKino.isSelected()) {
                filter.add("kino");
            }
            if (tglMarkt.isSelected()) {
                filter.add("markt");
            }
        }
        System.out.println(filter);
    }

    @FXML
    private void toggleAll() {
        if (tglAlle.isSelected()) {
            tglKino.setSelected(true);
            tglMuseum.setSelected(true);
            tglMarkt.setSelected(true);
        } else {
            tglKino.setSelected(false);
            tglMuseum.setSelected(false);
            tglMarkt.setSelected(false);
        }

    }

}
