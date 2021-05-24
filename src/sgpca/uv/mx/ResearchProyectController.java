package sgpca.uv.mx;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sgpca.uv.mx.bussinesslogic.ResearchProyectDAO;
import sgpca.uv.mx.domain.ResearchProyect;


public class ResearchProyectController implements Initializable {

    @FXML
    private TextField textFieldTitle;
    @FXML
    private DatePicker datePickerStartDate;
    @FXML
    private DatePicker datePickerFinishDate;
    @FXML
    private TextArea textFieldDescription;
    @FXML
    private TextField textFieldAssociates;
    @FXML
    private Button buttonSave;
    @FXML
    private Button buttonCancel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void saveResearchProyect(ActionEvent event) {
        if(!validateResearchTxf()){
            emptyTxfAlert();
        }else{
            ResearchProyectDAO researchDAO = new ResearchProyectDAO();
            ResearchProyect researchProyect;
            String title, description, associates;
            Date startDate, finishDate;

            title = textFieldTitle.getText();
            startDate = Date.valueOf(datePickerStartDate.getValue());
            finishDate = Date.valueOf(datePickerFinishDate.getValue());
            description = textFieldDescription.getText();
            associates = textFieldAssociates.getText();

            researchProyect = new ResearchProyect(title, description, startDate, finishDate);
            researchDAO.saveResearchProyect(researchProyect);

            confirmSaveResearchProyect();
            cleanResearchProyectTxf();
            closeWindow();
        }
    }

    @FXML
    private void cancelSaveResearchProyect(ActionEvent event) {
        Alert saveAlert = new Alert(Alert.AlertType.CONFIRMATION);
        saveAlert.setHeaderText(null);
        saveAlert.setTitle("Exit");
        saveAlert.setContentText("Cancel research proyect register?");
        Optional<ButtonType> action = saveAlert.showAndWait();
        
        if(action.get() == ButtonType.OK){
            closeWindow();
        }
    }
    
    private void confirmSaveResearchProyect(){
        Alert saveAlert = new Alert(Alert.AlertType.INFORMATION);
        saveAlert.setHeaderText(null);
        saveAlert.setTitle("Research Proyect Saved");
        saveAlert.setContentText("The research proyect has been saved");
        saveAlert.showAndWait();
    }
    
    private void cleanResearchProyectTxf() {
        textFieldTitle.setText("");
        datePickerStartDate.setValue(null);
        datePickerFinishDate.setValue(null);
        textFieldDescription.setText("");
        textFieldAssociates.setText("");
    }
    
    private boolean validateResearchTxf(){
        boolean validTxf = true;
        if(textFieldTitle.getText().isEmpty() || datePickerStartDate.getValue() == null || datePickerFinishDate.getValue() == null || textFieldDescription.getText().isEmpty() || textFieldAssociates.getText().isEmpty()){
            validTxf = false;
        }
        return validTxf;
    }

    private void emptyTxfAlert() {
        Alert emptyAlert = new Alert(Alert.AlertType.WARNING);
        emptyAlert.setHeaderText(null);
        emptyAlert.setTitle("Empty fields");
        emptyAlert.setContentText("Complete all the fields");
        emptyAlert.showAndWait();
    }
    
    
    public void closeWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ProyectListView.fxml"));
            Parent root = loader.load();
            ProyectListController controller = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            
            stage.setScene(scene);
            stage.setTitle("");
            stage.show();
            
            Stage myStage = (Stage) this.buttonCancel.getScene().getWindow();
            myStage.close();
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
