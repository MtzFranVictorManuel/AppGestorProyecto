
package appgestorproyecto;

import java.io.IOException;
import businesslogic.MembersDAO;
import domain.Members;
import appgestorproyecto.AlertGuis;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController implements Initializable {
    MembersDAO memberInfo = new MembersDAO();
    Members memberObject = new Members();
    AlertGuis alert = new AlertGuis();
    
    
    @FXML
    private TextField textFieldEmailID;
    @FXML
    private PasswordField textFieldPassword;
    
    @FXML
    private void loginaButton(ActionEvent event){        
        String emailID = textFieldEmailID.getText();
        String passwordID = textFieldPassword.getText();
            if(!emailID.isEmpty() && !passwordID.isEmpty()){
                if(EmailValidation.isValid(emailID)){
                    memberInfo.select(emailID, passwordID);
                    if(memberObject.getEmail() == null && memberObject.getPassword() == null){
                        alert.alertInformation("Error entering email", "Wrong email", "The email that was entered "+ emailID + " is not valid, try a valid email or request one to register.");
                        return;
                    }
                    else if(memberObject.getEmail().equals(emailID) && memberObject.getPassword().equals(passwordID)){    
                        navigationScreen("gui/ScreenHome.fxml");
                    }
                }
                else{
                    alert.alertInformation("Error entering email", "Wrong email", "The email that was entered "+ emailID + " is not valid, try a valid email or request one to register.");
                }
            }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public void navigationScreen(String url){
        try{
            Stage stage = (Stage) textFieldEmailID.getScene().getWindow();
            Scene escena = new Scene(FXMLLoader.load(getClass().getResource(url)));
            stage.setScene(escena);
            stage.show();
        }catch(IOException ex){
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
