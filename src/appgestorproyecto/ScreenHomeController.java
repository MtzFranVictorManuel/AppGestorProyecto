package appgestorproyecto;

import businesslogic.AcademicBodyDAO;
import businesslogic.MembersDAO;
import businesslogic.WorkplanDAO;
import domain.AcademicBody;
import domain.Members;
import domain.Workplan;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class ScreenHomeController implements Initializable {
    MembersDAO memberInfo = new MembersDAO();
    WorkplanDAO workplanInfo = new WorkplanDAO();
    AcademicBodyDAO academicInfo = new AcademicBodyDAO();
    Members memberObject = new Members();  
    AcademicBody academicObject = new AcademicBody();
    
    
    @FXML
    private Label labelInstitucionalFaculty;
    @FXML
    private Label labelName;
    @FXML
    private Label labelPositionAcamedic;
    @FXML
    private ChoiceBox<String> choiceBoxWorkplanKey;
    
    private ObservableList<String> listWorkPlan;
    @FXML
    private Button addBodyWorkplan;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listWorkPlan = FXCollections.observableArrayList();
        memberInfo.select(memberObject.getIdMember());
        academicInfo.select(memberObject.getIdMember());
        labelInstitucionalFaculty.setText(academicObject.getInstitucionalFaculty());
        labelName.setText(memberObject.getName());
        choiceBoxWorkplanKey.getItems().addAll(workplanInfo.logWorkplanList(listWorkPlan));
    }    

    @FXML
    private void addWorkplan(ActionEvent event) {
    }
    
    
}
