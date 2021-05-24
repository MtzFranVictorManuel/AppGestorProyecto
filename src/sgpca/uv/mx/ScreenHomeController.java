package sgpca.uv.mx;

import sgpca.uv.mx.bussinesslogic.AcademicBodyDAO;
import sgpca.uv.mx.bussinesslogic.MembersDAO;
import sgpca.uv.mx.bussinesslogic.ObjectiveDAO;
import sgpca.uv.mx.bussinesslogic.WorkplanDAO;
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
import javafx.scene.layout.AnchorPane;
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
        comboBox.getItems().addAll(workplanInfo.logWorkplanList(listWorkPlan));
        labelPositionAcamedic.setText(": id: " + setDateComboBox());
    }    

    @FXML
    private void addWorkplan(ActionEvent event) {
        
        navigationScreen("gui/ScreenAddWorkplan.fxml");
    }  

    @FXML
    private void actionProduction(ActionEvent event) {
        navigationScreen("gui/EvidenceView.fxml");
    }
    
    @FXML
    private void callWorkPlan(ActionEvent event) {
        updateTable();
    }
    
    @FXML
    private void logout(ActionEvent event) {
        navigationScreen("gui/ScreenLogin.fxml");
    }

    @FXML
    private void manageWorkplan(ActionEvent event) {
        if(setDateComboBox() != 0){
            Workplan workid = new Workplan(setDateComboBox());
            navigationScreen("gui/ScreenManageBodyWorkplan.fxml");         
        }
    }
    
    public void updateTable(){
        objectiveState = FXCollections.observableArrayList();
        objectiveStateComplet = FXCollections.observableArrayList();
        this.columnPendingObjective.setCellValueFactory(new PropertyValueFactory("title"));
        tableViewCompleted.setItems(objectiveInfo.loadObjectiveComplet(objectiveStateComplet, "completado", setDateComboBox()));
        tableViewPending.setItems(objectiveInfo.loadObjectivePending(objectiveState, "pendiente", setDateComboBox()));
        this.columnCompletedObjective.setCellValueFactory(new PropertyValueFactory("title"));
        labelPositionAcamedic.setText(": id: " + setDateComboBox());
    }
    
    public int setDateComboBox(){
        String workplanID = comboBox.getValue();
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

    
}
