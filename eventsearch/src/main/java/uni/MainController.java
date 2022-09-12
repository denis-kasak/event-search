/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni;

import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;

/**
 *
 * @author d-kas
 */


public class MainController{
    @FXML
    private ToggleButton tglAlle;
    @FXML
    private ToggleButton tglMuseum;
    @FXML
    private ToggleButton tglKino;
    @FXML
    private ToggleButton tglMarkt;
    
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
        //Model.showEvents(filter);
    }
    
    @FXML
    private void toggleAll(){
        if(tglAlle.isSelected()){
            tglKino.setSelected(true);
            tglMuseum.setSelected(true);
            tglMarkt.setSelected(true);
        }else{
            tglKino.setSelected(false);
            tglMuseum.setSelected(false);
            tglMarkt.setSelected(false);
        }
        
    }
}
