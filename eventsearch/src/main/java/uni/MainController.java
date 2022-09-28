/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.application.HostServices;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
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
    private ImageView imgBerlin;
    @FXML
    private DatePicker datepicker;
    @FXML
    private StackPane stackPaneImg;
    @FXML
    private AnchorPane paneButtons;
    @FXML
    private HBox hboxToolbar;
    @FXML
    private AnchorPane paneRoot;
    @FXML
    private Button btnBodeMuseum, btnUciLux, btnAltMuseum, btnFriedKirche, btnGemGalerie, btnHambBahnhof, btnJamSimGalerie, btnKunstBib, btnKunstGewMuseum, btnKupfKabinett, btnMuseumFoto, btnNeuNatGalerie, btnNeuMuseum, btnPergMuseum, btnPergMusPanoram, btnHumbForum, btnBerlGalerie, btnAltNatGalerie;

    private Map<Button, String> buttonMap;
    private float mapRatio = (float) 1.85; //Breite:Höhe -> mapRatio:1
    double trueImgWidth;
    double trueImgHeight;
    private HostServices hostServices;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //BEI NULL POINTER CHECKEN, DASS AUCH ID IN FXML GEPFLEGT IST

        Model.initEvents();

        initButtonMap();

        imgBerlin.fitWidthProperty().bind(stackPaneImg.widthProperty());
        imgBerlin.fitHeightProperty().bind(stackPaneImg.heightProperty());

        datepicker.setValue(LocalDate.now());

        hboxToolbar.prefWidthProperty().bind(paneRoot.widthProperty());

        //Resize Listener
        stackPaneImg.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldPaneWidth, Number newPaneWidth) {
                correctPane((double) newPaneWidth, stackPaneImg.heightProperty().doubleValue());

            }
        });
        stackPaneImg.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldPaneHeight, Number newPaneHeight) {
                correctPane(stackPaneImg.widthProperty().doubleValue(), (double) newPaneHeight);
            }
        });

        updateMap(false, false, false);
    }
    
    public void setHostServices(HostServices hostServices){
        this.hostServices=hostServices;
    }

    @FXML
    private void showDetails(ActionEvent e) {

        Button b = (Button) e.getSource();
        String ort = buttonMap.get(b);
        
        try {
            //String ort
            Stage stage = new Stage();

//        Parent root = FXMLLoader.load(DetailController.class.getResource("DetailView.fxml"));
//        scene = new Scene((Parent) root);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("DetailView.fxml"));
            Scene scene = new Scene(loader.load());
            
            DetailController d = loader.getController();
            d.fillDetails(ort);
            d.setHostServices(hostServices);
            scene.getStylesheets().add("../resource/design.css");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    private void updateMap(boolean museum, boolean kino, boolean flohmarkt) {
        List<String> orte = new ArrayList<String>();

        if (!museum) {
            orte = Model.getAllType("museum");
            for (Button b : valueToKey(orte)) {
                b.setVisible(false);
            }
        } else {
            orte = Model.getAllType("museum");
            for (Button b : valueToKey(orte)) {
                b.setVisible(true);
            }
        }
        if (!kino) {
            orte = Model.getAllType("kino");
            for (Button b : valueToKey(orte)) {
                b.setVisible(false);
            }
        } else {
            orte = Model.getAllType("kino");
            for (Button b : valueToKey(orte)) {
                b.setVisible(true);
            }
        }
    }

    public List<Button> valueToKey(List<String> orte) {

        List<Button> buttons = new ArrayList<>();

        for (String s : orte) {

            for (Map.Entry<Button, String> entry : buttonMap.entrySet()) {
                if (entry.getValue().equals(s)) {
                    buttons.add(entry.getKey());
                }
            }
        }

        return buttons;
    }

    private void initButtonMap() {
        buttonMap = new HashMap<>();

        //Kinos
        buttonMap.put(btnUciLux, "UCI Kino Berlin - Mercedes Platz | Luxe");

        //Museen
        buttonMap.put(btnBodeMuseum, "Bode-Museum");
        buttonMap.put(btnAltMuseum, "Altes Museum");
        buttonMap.put(btnFriedKirche, "Friedrichswerdersche Kirche");
        buttonMap.put(btnGemGalerie, "Gemäldegalerie");
        buttonMap.put(btnHambBahnhof, "Hamburger Bahnhof – Museum für Gegenwart – Berlin");
        buttonMap.put(btnJamSimGalerie, "James-Simon-Galerie");
        buttonMap.put(btnKunstBib, "Kunstbibliothek");
        buttonMap.put(btnKunstGewMuseum, "Kunstgewerbemuseum");
        buttonMap.put(btnKupfKabinett, "Kupferstichkabinett");
        buttonMap.put(btnNeuMuseum, "Neues Museum");
        buttonMap.put(btnMuseumFoto, "Museum für Fotografie");
        buttonMap.put(btnNeuNatGalerie, "Neue Nationalgalerie");
        buttonMap.put(btnPergMuseum, "Pergamonmuseum");
        buttonMap.put(btnPergMusPanoram, "Pergamonmuseum. Das Panorama");
        buttonMap.put(btnHumbForum, "Humboldt Forum");
        buttonMap.put(btnBerlGalerie, "Berlinische Galerie");
        buttonMap.put(btnAltNatGalerie, "Alte Nationalgalerie");

    }

    private void correctPane(double stackWidth, double stackHeight) {
        if ((float) stackWidth / stackHeight > mapRatio) {
            //Platz fürs Bild ist zu breit
            //Höhe Bild = Höhe StackPane

            trueImgWidth = stackPaneImg.heightProperty().doubleValue() * mapRatio;
            trueImgHeight = stackPaneImg.heightProperty().doubleValue();
        } else if ((float) stackWidth / stackHeight < mapRatio) {
            //Platz fürs Bild ist zu hoch
            //Breite Bild = Breite StackPane

            trueImgWidth = stackPaneImg.widthProperty().doubleValue();
            trueImgHeight = stackPaneImg.widthProperty().doubleValue() / mapRatio;
        }
        paneButtons.setMaxSize(trueImgWidth, trueImgHeight);
        paneButtons.setMinSize(trueImgWidth, trueImgHeight);

        int origWidth = 1567;
        int origHeight = 847;
        double scaleWidth = trueImgWidth / origWidth;
        double scaleHeight = trueImgHeight / origHeight;

        for (Map.Entry<Button, String> entry : buttonMap.entrySet()) {
            Button b = entry.getKey();
            String ort = entry.getValue();

            int x = Model.getX(ort);
            int y = Model.getY(ort);
            b.setLayoutX(x * scaleWidth);
            b.setLayoutY(y * scaleHeight);
        }

    }

    @FXML
    private void search() {

        List<String> filter = new ArrayList<>();

        if (tglAlle.isSelected()) {
            filter.add("museum");
            filter.add("kino");
            filter.add("markt");
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
    private void toggle(ActionEvent e) {//toggles außer "Alle"
        ToggleButton b = (ToggleButton) e.getSource();

        if (b == tglAlle) {
            if (tglAlle.isSelected()) {
                tglKino.setSelected(true);
                tglMuseum.setSelected(true);
                tglMarkt.setSelected(true);
            } else {
                tglKino.setSelected(false);
                tglMuseum.setSelected(false);
                tglMarkt.setSelected(false);
            }
        } else {

            if (!b.isSelected() && tglAlle.isSelected()) {
                tglAlle.setSelected(false);
            } else if (tglKino.isSelected() && tglMuseum.isSelected() && tglMarkt.isSelected()) {
                tglAlle.setSelected(true);
            }
        }
        updateMap(tglMuseum.isSelected(), tglKino.isSelected(), tglMarkt.isSelected());
    }

}
