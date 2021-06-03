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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sgpca.uv.mx.businesslogic.MembersDAO;
import sgpca.uv.mx.domain.Members;


public class ScreenEditPersonalInformationController implements Initializable {
    Members memberObject = new Members();
    MembersDAO memberInfo = new MembersDAO();

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

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(memberObject.getIdMember());
        /*memberInfo.select(memberObject.getIdMember());
        textFieldName.setText(memberObject.getName());
        textFieldSurname.setText(memberObject.getLastName());
        textFieldAcademicBodyPosition.setText(memberObject.getPosition());
        datePickerDateBirth.setValue(LOCAL_DATE(memberObject.getBirthday().toString()));
        textFieldCurp.setText(memberObject.getCurp());
        textFieldEmail.setText(memberObject.getEmail());
        textFieldPhoneNumber.setText(memberObject.getPhoneNumber());*/
        valuesNotEditable(true);
    }    

    @FXML
    private void actionExit(ActionEvent event) {
        navegationScreen("gui/ScreenHome.fxml");
    }

    @FXML
    private void actionEditInformaion(ActionEvent event) {
        valuesNotEditable(false);
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
    }
    
    public static final LocalDate LOCAL_DATE (String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }
}
