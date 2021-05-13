/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appgestorproyecto;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author azul_
 */
public class ScreenAddWorkplanController implements Initializable {

    @FXML
    private TextField textFieldWorkplanKey;
    @FXML
    private DatePicker datePickerStartDate;
    @FXML
    private DatePicker datePickerEndDate;
    @FXML
    private Button buttonAddWorkplan;
    @FXML
    private Button buttonCancel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void buttonAddWorkplanAction(ActionEvent event) {
    }

    @FXML
    private void buttonCancelAction(ActionEvent event) {
        
    }
    
}
