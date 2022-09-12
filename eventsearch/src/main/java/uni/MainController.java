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
    private void test(){
        System.out.println("test erfolgreich");
    }
    
    @FXML
    private void search(){
        
        //hole Toggle Filter Informationen
        
        ArrayList<String> filter = new ArrayList<String>();
        
        if(tglAlle.isSelected()){
            System.out.println("yesssss");
        }
        
        //Model.showEvents([bool Toggle1, bool Toggle2, bool Toggle3])
    }
}
