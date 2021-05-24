package sgpca.uv.mx;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sgpca.uv.mx.bussinesslogic.EvidenceDAO;
import sgpca.uv.mx.domain.Evidence;


public class EvidenceController implements Initializable {

    private ObservableList<String> typeList;
    @FXML
    private TextField textFieldName;
    @FXML
    private TextField textFieldAutor;
    @FXML
    private DatePicker datePickerDate;
    @FXML
    private ComboBox comboBoxType;
    @FXML
    private TextArea textFieldDescription;
    @FXML
    private Button buttonSave;
    @FXML
    private Button buttonCancel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        typeList = FXCollections.observableArrayList();
        typeList.add("Book");
        typeList.add("Magazine");
        typeList.add("Thesis");
        typeList.add("Research");
        comboBoxType.setItems(typeList);
    }    

    @FXML
    private void saveEvidence(ActionEvent event) {
        if(!validateEvidenceTxf()){
            emptyTxfAlert();
        }else{
            EvidenceDAO evidenceDAO = new EvidenceDAO();
            Evidence evidence;
            String name, autor, type, description;
            Date date;

            name = textFieldName.getText();
            autor = textFieldAutor.getText();
            date = Date.valueOf(datePickerDate.getValue());
            type = comboBoxType.getValue().toString();
            description = textFieldDescription.getText();

            evidence = new Evidence(name, autor, date, type);
            evidenceDAO.saveEvidence(evidence);

            saveConfirmation();
            cleanTextFields();
            closeEvidenceWindow();
        }
    }

    @FXML
    private void cancelEvidenceRegister(ActionEvent event) {
        Alert saveAlert = new Alert(Alert.AlertType.CONFIRMATION);
        saveAlert.setHeaderText(null);
        saveAlert.setTitle("Exit");
        saveAlert.setContentText("Cancel evidence register?");
        Optional<ButtonType> action = saveAlert.showAndWait();
        
        if(action.get() == ButtonType.OK){
            closeEvidenceWindow();
        }
    }
    
    private void saveConfirmation(){
        Alert saveAlert = new Alert(Alert.AlertType.INFORMATION);
        saveAlert.setHeaderText(null);
        saveAlert.setTitle("Evidence Saved");
        saveAlert.setContentText("The evidence has been saved");
        saveAlert.showAndWait();
    }
    
    private void cleanTextFields(){
        textFieldName.setText("");
        textFieldAutor.setText("");
        datePickerDate.setValue(null);
        comboBoxType.setValue(null);
        textFieldDescription.setText("");
    }
    
    private boolean validateEvidenceTxf(){
        boolean validTxf = true;
        if(textFieldName.getText().isEmpty() || textFieldAutor.getText().isEmpty() || datePickerDate.getValue() == null || comboBoxType.getValue() == null || textFieldDescription.getText().isEmpty()){
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

    public void closeEvidenceWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EvidenceListView.fxml"));
            Parent root = loader.load();
            EvidenceListController controller = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            
            stage.setScene(scene);
            stage.setTitle("Evidence Register");
            stage.show();
            
            Stage myStage = (Stage) this.buttonCancel.getScene().getWindow();
            myStage.close();
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void navigationScreen(String url){
        try{
            Stage stage = (Stage) buttonCancel.getScene().getWindow();
            Scene escena = new Scene(FXMLLoader.load(getClass().getResource(url)));
            stage.setScene(escena);
            stage.setResizable(false);
            stage.show();
        }catch(IOException ex){
            Logger.getLogger(EvidenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
}