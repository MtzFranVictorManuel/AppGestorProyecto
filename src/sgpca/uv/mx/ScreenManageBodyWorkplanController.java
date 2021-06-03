/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgpca.uv.mx;

import sgpca.uv.mx.domain.Workplan;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import sgpca.uv.mx.businesslogic.ObjectiveDAO;
import sgpca.uv.mx.businesslogic.WorkplanDAO;
import sgpca.uv.mx.domain.AcademicBody;
import sgpca.uv.mx.domain.Objective;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author azul_
 */
public class ScreenManageBodyWorkplanController implements Initializable {
    WorkplanDAO workplanInfo = new WorkplanDAO();
    Workplan workplanObject = new Workplan();
    ObjectiveDAO objectiveInfo = new ObjectiveDAO();
    Objective objectiveObject = new Objective();
    AlertGuis alert = new AlertGuis();

    @FXML
    private Button buttonChangeState;
    @FXML
    private Button buttonAddObjective;
    @FXML
    private Button buttonSaveWorkplan;
    @FXML
    private Button buttonExit;
    @FXML
    private TextField textFieldWorkplan;
    @FXML
    private DatePicker datePickerStartDate;
    @FXML
    private DatePicker datePickerEndDate;
    @FXML
    private TableView<Objective> tableViewObjective;
    @FXML
    private TableColumn columnRegisteredObjective;
    @FXML
    private TableColumn columnDescription;
    @FXML
    private TableColumn columnStateObjective;
    
    
    private ObservableList<Objective> observabelTable;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        workplanInfo.selectInfo(Workplan.getIdWorkplan());
        textFieldWorkplan.setText(workplanObject.getTitle());
        datePickerStartDate.setValue(LOCAL_DATE(workplanObject.getStartDate()));
        datePickerEndDate.setValue(LOCAL_DATE(workplanObject.getEndDate()));
        valuesNotEditable();
        showTableInformation();
        buttonChangeState.setText("Edit workplan");
    }    

    @FXML
    private void editWorkplanAndSave(ActionEvent event) {
        try{
            textFieldWorkplan.setDisable(false);
            datePickerStartDate.setDisable(false);
            datePickerEndDate.setDisable(false);
            buttonSaveWorkplan.setVisible(true);
            buttonChangeState.setText("Cancel");
            if(buttonChangeState.getText() == "Cancel"){
                if(alert.alertConfirmation("Edit", "", "You want to edit the work plan information") == false){
                    buttonChangeState.setText("Edit workplan");
                    valuesNotEditable();
                }
            }
        }
        catch(NullPointerException exception){
            Logger.getLogger(ScreenManageBodyWorkplanController.class.getName()).log(Level.SEVERE, null, exception);
        }
    }
    
    @FXML
    private void saveEditWorkplan(ActionEvent event) {
        try{
            String workplanKey = textFieldWorkplan.getText();
            String workplan = workplanObject.getTitle();
            String startDate = datePickerStartDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String endDate = datePickerEndDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));        
            String getExistence = workplanInfo.validateExistence(AcademicBody.getIdAcademicBody(), workplanKey);
            workplanObject.setTitle(workplanKey);
            workplanObject.setStartDate(startDate);
            workplanObject.setEndDate(endDate);
            if (!workplanKey.equals(getExistence)) {
                if (alert.alertConfirmation("Confirmation insert", "Confirmation", "You are adding the following data to "
                        + "the database, are you sure you want to register the following data? \n" + "Workplan Key: \n" + workplanKey
                        + "\nStart date: \n" + startDate + "\nEnd date: \n" + endDate) == true) {
                    System.out.println(workplanObject.getTitle());
                    workplanInfo.update(workplanObject, workplan, AcademicBody.getIdAcademicBody());
                    alert.alertInformation("Aggregation successfully", null, "The data obtained was entered correctly.");
                    buttonChangeState.setText("Edit workplan");
                    valuesNotEditable();
                }
            } else {
                alert.alertWarning("Existence of value in database", "Warning", "The entered value " + workplanKey + " is "
                        + "already registered in the database, try another key for the work plan. ");
            }         
        }
        catch(NullPointerException exception){
            alert.alertInformation("Empty fields", null, "Complete the values marked with * to register a work plan.");
        } 
    }

    @FXML
    private void addObjective(ActionEvent event) {
        navigationScreen("gui/ScreenAddObjective.fxml");
    }

    @FXML
    private void exitToHome(ActionEvent event) {
        navigationScreen("gui/ScreenHome.fxml");
    }
    
    public void navigationScreen(String url){
        try{
            Stage stage = (Stage) buttonExit.getScene().getWindow();
            Scene escena = new Scene(FXMLLoader.load(getClass().getResource(url)));
            stage.setScene(escena);
            stage.setResizable(false);
            stage.show();
        }catch(IOException ex){
            Logger.getLogger(ScreenManageBodyWorkplanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static final LocalDate LOCAL_DATE (String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }
    
    public void valuesNotEditable(){
        textFieldWorkplan.setDisable(true);
        datePickerStartDate.setDisable(true);
        datePickerEndDate.setDisable(true);
        buttonSaveWorkplan.setVisible(false);
    }
        
    public void showTableInformation(){
        observabelTable = FXCollections.observableArrayList();
        this.columnRegisteredObjective.setCellValueFactory(new PropertyValueFactory("title"));
        this.columnDescription.setCellValueFactory(new PropertyValueFactory("description"));
        this.columnStateObjective.setCellValueFactory(new PropertyValueFactory("targetState"));
        tableViewObjective.setItems(objectiveInfo.selectTableView(observabelTable, Workplan.getIdWorkplan()));
        
    }
}
