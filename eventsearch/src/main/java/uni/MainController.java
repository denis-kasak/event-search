/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni;

import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;

/**
 *
 * @author d-kas
 */

public class MainController {
    @FXML
    private ToggleButton tglAlle;
    @FXML
    private ToggleButton tglMuseum;
    @FXML
    private ToggleButton tglKino;
    @FXML
    private ToggleButton tglMarkt;
    
    @FXML
    private void test(){
        System.out.println("test erfolgreich");
    }
    
    @FXML
    private void search(){
        
        ArrayList<String> filter = new ArrayList<String>();
        
        if(tglAlle.isSelected()){
            filter.add("all");
        } else{
            if(tglMuseum.isSelected()){
                filter.add("museum");
            }
            if(tglKino.isSelected()){
                filter.add("kino");
            }
            if(tglMarkt.isSelected()){
                filter.add("markt");
            }
        }
        Model.showEvents(filter);
    }
}
