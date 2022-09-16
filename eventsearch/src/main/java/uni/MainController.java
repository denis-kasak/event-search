/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 *
 * @author d-kas
 */
public class MainController implements Initializable {
    
    
    private float mapRatio = (float) 1.78; //Breite:Höhe -> mapRatio:1
            
    @FXML
    private ToggleButton tglAlle;
    @FXML
    private ToggleButton tglMuseum;
    @FXML
    private ToggleButton tglKino;
    @FXML
    private ToggleButton tglMarkt;
    @FXML
    private ImageView imgBerlin;
    @FXML
    private DatePicker datepicker;
    @FXML
    private StackPane stackPaneImg;
    @FXML private AnchorPane paneButtons;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //BEI NULL POINTER CHECKEN, DASS AUCH ID IN FXML GEPFLEGT IST

        imgBerlin.fitWidthProperty().bind(stackPaneImg.widthProperty());
        imgBerlin.fitHeightProperty().bind(stackPaneImg.heightProperty());

        datepicker.setValue(LocalDate.now());

        stackPaneImg.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldPaneWidth, Number newPaneWidth) {
                correctPane((double)newPaneWidth, stackPaneImg.heightProperty().doubleValue());

            }
        });
        stackPaneImg.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldPaneHeight, Number newPaneHeight) {
                correctPane( stackPaneImg.widthProperty().doubleValue(),(double)newPaneHeight);
            }
        });

    }

    private void correctPane(double stackWidth, double stackHeight) {
        if ((float) stackWidth / stackHeight > 1.78) {
            //Platz fürs Bild ist zu breit
            //Höhe Bild = Höhe StackPane
            paneButtons.setMaxSize(stackPaneImg.heightProperty().multiply(mapRatio).doubleValue(),stackPaneImg.heightProperty().doubleValue());
            paneButtons.setMinSize(stackPaneImg.heightProperty().multiply(mapRatio).doubleValue(),stackPaneImg.heightProperty().doubleValue());

        } else if ((float) stackWidth / stackHeight < 1.78) {
            //Platz fürs Bild ist zu hoch
            //Breite Bild = Breite StackPane
            paneButtons.setMaxSize(stackPaneImg.widthProperty().doubleValue(),stackPaneImg.widthProperty().divide(mapRatio).doubleValue());
            paneButtons.setMinSize(stackPaneImg.widthProperty().doubleValue(),stackPaneImg.widthProperty().divide(mapRatio).doubleValue());
            

        }

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
