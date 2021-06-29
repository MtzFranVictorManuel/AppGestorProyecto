package sgpca.uv.mx;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sgpca.uv.mx.businesslogic.MembersDAO;
import sgpca.uv.mx.domain.Members;
import sgpca.uv.mx.utilities.AlertGuis;


public class ScreenEditPersonalInformationController implements Initializable {
    Members memberObject = new Members();
    MembersDAO memberInfo = new MembersDAO();
    AlertGuis alertGui = new AlertGuis();

    @FXML
    private TextField textFieldName;
    
    @FXML
    private TextField textFieldSurname;
    
    @FXML
    private TextField textFieldAcademicBodyPosition;
    
    @FXML
    private TextField textFieldEmail;
    
    @FXML
    private TextField textFieldPhoneNumber;
    
    @FXML
    private TextField textFieldCurp;
    
    @FXML
    private DatePicker datePickerDateBirth;
    
    @FXML
    private Button buttonExit;
    
    @FXML
    private Button buttonSaveChanges;
    
    @FXML
    private Button buttonChangePassword;
    
    @FXML
    private Label labelOldPassword;
    
    @FXML
    private Label labelNewPassword;
    
    @FXML
    private Label labelConfirmPassword;
    
    @FXML
    private PasswordField textFieldOldPassword;
    
    @FXML
    private PasswordField textFieldNewPassword;
    
    @FXML
    private PasswordField textFieldConfirmPassword;
    
    @FXML
    private Button buttonCancelPasswordChange;
    
    @FXML
    private Button buttonEditInformation;
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textFieldName.setText(memberInfo.select(memberObject.getIdMember()).getName());
        textFieldSurname.setText(memberInfo.select(memberObject.getIdMember()).getLastName());
        textFieldAcademicBodyPosition.setText(memberInfo.select(memberObject.getIdMember()).getPosition());
        datePickerDateBirth.setValue(LOCAL_DATE(memberInfo.select(memberObject.getIdMember()).getBirthday().toString()));
        textFieldCurp.setText(memberInfo.select(memberObject.getIdMember()).getCurp());
        textFieldEmail.setText(memberInfo.select(memberObject.getIdMember()).getEmail());
        textFieldPhoneNumber.setText(memberInfo.select(memberObject.getIdMember()).getPhoneNumber());
        buttonCancelPasswordChange.setVisible(false);
        buttonSaveChanges.setVisible(false);
        valuesNotEditable(true);
        passwordValuesNotVisible(false);
    }    

    @FXML
    private void actionExit(ActionEvent event) {
        navegationScreen("gui/ScreenHome.fxml");
    }

    @FXML
    private void actionEditInformaion(ActionEvent event) {
        if(buttonEditInformation.getText().equals("Edit information")){
            buttonEditInformation.setText("Cancel edit");
            buttonSaveChanges.setVisible(true);
            valuesNotEditable(false);
        } else if(buttonEditInformation.getText().equals("Cancel edit")){
            stateButtons("Edit information", "Save changes", true);
        }
        
    }
    
    @FXML
    private void saveChanges(ActionEvent event) {
        if(buttonSaveChanges.getText().equals("Save changes")){
            System.out.println("Solo guardo esto y no contrasena");
            if(alertGui.alertConfirmation("", "", "") == true){
                stateButtons("Edit information", "Save changes", true);
            }
        } else if(buttonSaveChanges.getText().equals("Save password")){
            System.out.println("Solo guardo la contrasena");
            if(alertGui.alertConfirmation("Password update", "", "You are about"
                    + " to change your password, press the accept button to apply the changes.") == true){
                alertGui.alertInformation("Changes applied", "", "The changes were applied successfully.");
                stateButtons("Edit information", "Save changes", true);
            }
            
        }
    }

    @FXML
    private void changePassword(ActionEvent event) {
        buttonSaveChanges.setText("Save password");
        buttonChangePassword.setVisible(false);
        buttonCancelPasswordChange.setVisible(true);
        passwordValuesNotVisible(true);
        valuesNotEditable(true);
    }
    
    @FXML
    private void cancelPasswordChanges(ActionEvent event) {
        if(alertGui.alertConfirmation("Cancel password edit", "Cancel password", 
                "The edition of the user password is being canceled, do you want to continue?") == true){
            buttonSaveChanges.setText("Save changes");
            buttonChangePassword.setVisible(true);
            buttonCancelPasswordChange.setVisible(false);
            passwordValuesNotVisible(false);
        }
    }

    
    private void navegationScreen(String url){
        try{
            Stage stage = (Stage) buttonExit.getScene().getWindow();
            Scene escena = new Scene(FXMLLoader.load(getClass().getResource(url)));
            stage.setScene(escena);
            stage.setResizable(false);
            stage.show();
        }catch(IOException ex){
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void valuesNotEditable(boolean validation){
        textFieldAcademicBodyPosition.setDisable(validation);
        textFieldCurp.setDisable(validation);
        textFieldEmail.setDisable(validation);
        textFieldName.setDisable(validation);
        textFieldPhoneNumber.setDisable(validation);
        textFieldSurname.setDisable(validation);
        datePickerDateBirth.setDisable(validation);
        buttonChangePassword.setVisible(!validation);
    }
    
    private void passwordValuesNotVisible(boolean validation){
        labelOldPassword.setVisible(validation);
        labelNewPassword.setVisible(validation);
        labelConfirmPassword.setVisible(validation);
        textFieldOldPassword.setVisible(validation);
        textFieldNewPassword.setVisible(validation);
        textFieldConfirmPassword.setVisible(validation);
    }
    
    public static final LocalDate LOCAL_DATE (String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }
    
    private void stateButtons(String textButtonEditInformation, String textButtonSaveChanges, boolean value){
        buttonSaveChanges.setText(textButtonSaveChanges); 
        buttonEditInformation.setText(textButtonEditInformation); 
        buttonCancelPasswordChange.setVisible(!value); 
        buttonSaveChanges.setVisible(!value); 
        valuesNotEditable(value); 
        passwordValuesNotVisible(!value); 
    }
}
