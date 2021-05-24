package sgpca.GUI;

import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sgpca.bussinesslogic.BlueprintDAO;
import sgpca.domain.Blueprint;

public class BlueprintController implements Initializable {

    @FXML
    private TextField textFieldTitle;
    @FXML
    private DatePicker datePickerStartDate;
    @FXML
    private DatePicker datePickerFinishDate;
    @FXML
    private TextField textFieldAssociates;
    @FXML
    private TextField textFieldStudent;
    @FXML
    private TextArea textFieldDescription;
    @FXML
    private Button buttonSave;
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
    private void saveBlueprint(ActionEvent event) {
        if(!validateBlueprintTxf()){
            emptyTxfAlert();
        }else{
            BlueprintDAO blueDAO = new BlueprintDAO();
            Blueprint blueprint;

            String title, associates, student, description;
            Date startDate, finishDate;

            title = textFieldTitle.getText();
            startDate = Date.valueOf(datePickerStartDate.getValue());
            finishDate = Date.valueOf(datePickerFinishDate.getValue());
            associates = textFieldAssociates.getText();
            student = textFieldStudent.getText();
            description = textFieldDescription.getText();

            blueprint = new Blueprint(title, startDate, associates, description);
            blueDAO.saveBlueprint(blueprint);

            saveBlueprintConfirmation();
            cleanBlueprintTxf();
        }
    }

    @FXML
    private void cancelSaveBlueprint(ActionEvent event) { 
        Alert saveAlert = new Alert(Alert.AlertType.CONFIRMATION);
        saveAlert.setHeaderText(null);
        saveAlert.setTitle("Exit");
        saveAlert.setContentText("Cancel blueprint register?");
        Optional<ButtonType> action = saveAlert.showAndWait();
        
        if(action.get() == ButtonType.OK){
            System.out.println("hoila");
        }
    }
    
    private void saveBlueprintConfirmation(){
        Alert saveAlert = new Alert(Alert.AlertType.INFORMATION);
        saveAlert.setHeaderText(null);
        saveAlert.setTitle("Blueprint Saved");
        saveAlert.setContentText("The blueprint has been saved");
        saveAlert.showAndWait();
    }
    
    private void cleanBlueprintTxf(){
        textFieldTitle.setText("");
        datePickerStartDate.setValue(null);
        datePickerFinishDate.setValue(null);
        textFieldAssociates.setText("");
        textFieldStudent.setText("");
        textFieldDescription.setText("");
    }
    
    private boolean validateBlueprintTxf(){
        boolean validTxf = true;
        if(textFieldTitle.getText().isEmpty() || datePickerStartDate.getValue() == null || datePickerFinishDate.getValue() == null || textFieldAssociates.getText().isEmpty() || textFieldStudent.getText().isEmpty() || textFieldDescription.getText().isEmpty()){
            validTxf = false;
        }
        
        return validTxf;
    }
    
    private void emptyTxfAlert(){
        Alert emptyTxf = new Alert(Alert.AlertType.WARNING);
        emptyTxf.setHeaderText(null);
        emptyTxf.setTitle("Empty fields");
        emptyTxf.setContentText("Complete all the fields");
        emptyTxf.showAndWait();
    }
}
