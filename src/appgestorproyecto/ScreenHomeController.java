package appgestorproyecto;

import businesslogic.AcademicBodyDAO;
import businesslogic.MembersDAO;
import businesslogic.ObjectiveDAO;
import businesslogic.WorkplanDAO;
import domain.AcademicBody;
import domain.Members;
import domain.Objective;
import domain.Workplan;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ScreenHomeController implements Initializable {
    MembersDAO memberInfo = new MembersDAO();
    WorkplanDAO workplanInfo = new WorkplanDAO();
    AcademicBodyDAO academicInfo = new AcademicBodyDAO();
    ObjectiveDAO objectiveInfo = new ObjectiveDAO();
    Members memberObject = new Members();  
    AcademicBody academicObject = new AcademicBody();
    Workplan workplanObject = new Workplan();
    
    
    @FXML
    private Label labelInstitucionalFaculty;
    @FXML
    private Label labelName;
    @FXML
    private Label labelPositionAcamedic;
    
    private ObservableList<String> listWorkPlan;
    
    private ObservableList<Objective> objectiveState;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private TableColumn columnPendingObjective;
    @FXML
    private TableColumn columnCompletedObjective;
    @FXML
    private TableView<Objective> tableViewPending;
    @FXML
    private TableView<Objective> tableViewCompleted;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {       
        listWorkPlan = FXCollections.observableArrayList();
        memberInfo.select(memberObject.getIdMember());
        academicInfo.select(memberObject.getIdMember());
        labelInstitucionalFaculty.setText(academicObject.getInstitucionalFaculty());
        labelName.setText(memberObject.getName());
        comboBox.getItems().addAll(workplanInfo.logWorkplanList(listWorkPlan));       
    }    

    @FXML
    private void addWorkplan(ActionEvent event) {
        
        navigationScreen("gui/ScreenAddWorkplan.fxml");
    }
    
    public void navigationScreen(String url){
        try{
            Stage stage = (Stage) labelName.getScene().getWindow();
            Scene escena = new Scene(FXMLLoader.load(getClass().getResource(url)));
            stage.setScene(escena);
            stage.show();
        }catch(IOException ex){
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void callWorkPlan(ActionEvent event) {
        updateTable();
    }
    
    
    public void updateTable(){
        String workplanID = comboBox.getValue();
        int idWorkplan = workplanInfo.queryWorkplanID(workplanID);
        objectiveState = FXCollections.observableArrayList();
        this.columnPendingObjective.setCellValueFactory(new PropertyValueFactory("title"));
        tableViewPending.setItems(objectiveInfo.loadObjectivePending(objectiveState, "pendiente", idWorkplan));
        this.columnCompletedObjective.setCellValueFactory(new PropertyValueFactory("title"));
        tableViewCompleted.setItems(objectiveInfo.loadObjectiveComplet(objectiveState, "completado", idWorkplan));
        labelPositionAcamedic.setText(workplanID + "  " + idWorkplan);
    }
    
    @FXML
    private void logout(ActionEvent event) {
        navigationScreen("gui/ScreenLogin.fxml");
    }
    
}
