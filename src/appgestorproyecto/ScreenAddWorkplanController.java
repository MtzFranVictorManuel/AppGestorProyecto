package appgestorproyecto;

import businesslogic.WorkplanDAO;
import domain.AcademicBody;
import domain.Workplan;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Victor Manuel Martinez Franzoni
 */
public class ScreenAddWorkplanController implements Initializable {
    WorkplanDAO workplanQuery = new WorkplanDAO();
    Workplan workplanObject = new Workplan();
    AcademicBody academicObject = new AcademicBody();
    AlertGuis alert = new AlertGuis();

    @FXML
    private TextField textFieldWorkplanKey;
    @FXML
    private DatePicker datePickerStartDate;
    @FXML
    private DatePicker datePickerEndDate;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void buttonAddWorkplanAction(ActionEvent event) {
        try{
            String workplanKey = textFieldWorkplanKey.getText();
            String startDate = datePickerStartDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String endDate = datePickerEndDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));        
            String getExistence = workplanQuery.validateExistence(AcademicBody.getIdAcademicBody(), workplanKey);
            workplanObject.setTitle(workplanKey);
            workplanObject.setStartDate(startDate);
            workplanObject.setEndDate(endDate);
            if(!workplanKey.equals(getExistence))
            {
                if(alert.alertConfirmation("Confirmation insert", "Confirmation", "You are adding the following data to "
                        + "the database, are you sure you want to register the following data? \n" + "Workplan Key: \n" + workplanKey 
                        + "\nStart date: \n" + startDate + "\nEnd date: \n" + endDate) == true)
                {
                    workplanQuery.insert(workplanObject, AcademicBody.getIdAcademicBody());
                    alert.alertInformation("Aggregation successfully", null, "The data obtained was entered correctly.");
                    empyText();
                }
            }
            else{
                alert.alertWarning("Existence of value in database", "Warning", "The entered value "+ workplanKey +" is "
                    + "already registered in the database, try another key for the work plan. ");
            }          
        }
        catch(NullPointerException exception){
            alert.alertInformation("Empty fields", null, "Complete the values marked with * to register a work plan.");
        }   
    }

    @FXML
    private void buttonCancelAction(ActionEvent event) {
        try{
            Stage stage = (Stage) textFieldWorkplanKey.getScene().getWindow();
            Scene escena = new Scene(FXMLLoader.load(getClass().getResource("gui/ScreenHome.fxml")));
            stage.setScene(escena);
            stage.show();
        }catch(IOException ex){
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void empyText(){
        textFieldWorkplanKey.setText("");
        datePickerStartDate.setValue(null);
        datePickerEndDate.setValue(null);
    }
}
