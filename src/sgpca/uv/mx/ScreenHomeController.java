package sgpca.uv.mx;

import sgpca.uv.mx.businesslogic.AcademicBodyDAO;
import sgpca.uv.mx.businesslogic.MembersDAO;
import sgpca.uv.mx.businesslogic.ObjectiveDAO;
import sgpca.uv.mx.businesslogic.WorkplanDAO;
import sgpca.uv.mx.domain.AcademicBody;
import sgpca.uv.mx.domain.Members;
import sgpca.uv.mx.domain.Objective;
import sgpca.uv.mx.domain.Workplan;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ScreenHomeController implements Initializable {
    MembersDAO memberInfo = new MembersDAO();
    WorkplanDAO workplanInfo = new WorkplanDAO();
    AcademicBodyDAO academicInfo = new AcademicBodyDAO();
    ObjectiveDAO objectiveInfo = new ObjectiveDAO();
    Objective objectiveObject = new Objective();
    Members memberObject = new Members();  
    AcademicBody academicObject = new AcademicBody();
    Workplan workplanObject = new Workplan();
    AlertGuis alertGuis = new AlertGuis();
    
    @FXML
    private Label labelInstitucionalFaculty;
    
    @FXML
    private Label labelName;
    
    @FXML
    private Label labelPositionAcamedic;   
    
    @FXML
    private ComboBox<String> comboBoxSelectWorkplanKey;
    
    @FXML
    private TableColumn columnPendingObjective;
    
    @FXML
    private TableColumn columnCompletedObjective;
    
    @FXML
    private TableColumn columnIdPendingObjectives;
    
    @FXML
    private TableColumn columnIdCompletedObjective;
    
    @FXML
    private TableView<Objective> tableViewPending;
    
    @FXML
    private TableView<Objective> tableViewCompleted;
    
    @FXML
    private Button addBodyWorkplan;
    
    @FXML
    private Button idLogout;
    
    @FXML
    private Button manageBodyWorkplan;
    
    private ObservableList<String> listWorkPlan;
   
    private ObservableList<Objective> objectiveState;
    
    private ObservableList<Objective> objectiveStateComplet;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {       
        listWorkPlan = FXCollections.observableArrayList();
        memberInfo.select(memberObject.getIdMember());
        academicInfo.select(memberObject.getIdMember());
        labelInstitucionalFaculty.setText(academicObject.getInstitucionalFaculty());
        labelName.setText(memberObject.getName());
        comboBoxSelectWorkplanKey.getItems().addAll(workplanInfo.logWorkplanList(listWorkPlan));
    }    

    @FXML
    private void callWorkPlan(ActionEvent event) {
        updateTable();
    }
    
    @FXML
    private void actionEditPersonalInformation(ActionEvent event) {
        navigationScreen("gui/ScreenEditPersonalInformation.fxml");
    }
    
    @FXML
    private void logout(ActionEvent event) {
        navigationScreen("gui/ScreenLogin.fxml");
    }
    
    @FXML
    private void addWorkplan(ActionEvent event) {
        
        navigationScreen("gui/ScreenAddWorkplan.fxml");
    }  

    @FXML
    private void manageWorkplan(ActionEvent event) {
        if(setDateComboBox() != 0){
            Workplan.setIdWorkplan(setDateComboBox());
            navigationScreen("gui/ScreenManageBodyWorkplan.fxml");         
        }
    }
    
    public void updateTable(){
        objectiveState = FXCollections.observableArrayList();
        objectiveStateComplet = FXCollections.observableArrayList();
        tableViewCompleted.setItems(objectiveInfo.loadObjectiveComplet(objectiveStateComplet, "completado", setDateComboBox()));
        tableViewPending.setItems(objectiveInfo.loadObjectivePending(objectiveState, "pendiente", setDateComboBox()));
        this.columnPendingObjective.setCellValueFactory(new PropertyValueFactory("title"));
        this.columnIdPendingObjectives.setCellValueFactory(new PropertyValueFactory("idNoStaticObjective"));
        this.columnCompletedObjective.setCellValueFactory(new PropertyValueFactory("title"));
        this.columnIdCompletedObjective.setCellValueFactory(new PropertyValueFactory("idNoStaticObjective"));
    }
    
    public int setDateComboBox(){
        String workplanID = comboBoxSelectWorkplanKey.getValue();
        int idWorkplan = workplanInfo.queryWorkplanID(workplanID);
        return idWorkplan;
    }
    
    public void navigationScreen(String url){
        try{
            Stage stage = (Stage) labelName.getScene().getWindow();
            Scene escena = new Scene(FXMLLoader.load(getClass().getResource(url)));
            stage.setScene(escena);
            stage.setResizable(false);
            stage.show();
        }catch(IOException ex){
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void displayInformationPending(MouseEvent event) {
        try{
            Objective objectiveContainerPending = tableViewPending.getSelectionModel().getSelectedItem();
            int idObjectivePending = objectiveContainerPending.getIdNoStaticObjective();
            Objective.setIdObjective(idObjectivePending);
            navigationScreen("gui/ScreenEditObjective.fxml");
        } catch(RuntimeException exception){
            alertGuis.alertError("Selection not confirmed ", "A work plan key is not selected or an objective has not been selected.", "");
        }
    }

    @FXML
    private void generalCurriculum(ActionEvent event) {
        navigationScreen("gui/ScreenGeneralCurriculum.fxml");
    }   

    @FXML
    private void displayInformationCompleted(MouseEvent event) {
        try{
            Objective objectiveContainerCompleted = tableViewCompleted.getSelectionModel().getSelectedItem();
            int idObjectiveCompleted = objectiveContainerCompleted.getIdNoStaticObjective();
            Objective.setIdObjective(idObjectiveCompleted);
            navigationScreen("gui/ScreenEditObjective.fxml");
        } catch(RuntimeException exception){
            alertGuis.alertError("Selection not confirmed ", "A work plan key is not selected or an objective has not been selected.", "");
        }
    }
}
