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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sgpca.uv.mx.businesslogic.AcademicBodyDAO;
import sgpca.uv.mx.businesslogic.MembersDAO;
import sgpca.uv.mx.businesslogic.ResumeDAO;
import sgpca.uv.mx.domain.AcademicBody;
import sgpca.uv.mx.domain.Members;
import sgpca.uv.mx.domain.Resume;


public class ScreenGeneralCurriculumController implements Initializable {
    Members memberObject = new Members();
    MembersDAO memberInfo = new MembersDAO();
    AcademicBody academicBodyObject = new AcademicBody();
    AcademicBodyDAO academicBodyInfo = new AcademicBodyDAO();
    Resume resumeObject = new Resume();
    ResumeDAO resumeInfo = new ResumeDAO();

    @FXML
    private TextField textFieldAreaName;
    @FXML
    private TextField textFieldDependencyInstitution;
    @FXML
    private TextField textFieldFacultyInstitution;
    @FXML
    private TextField textFieldDegreeConsolidation;
    @FXML
    private TextField textFieldKeyCode;
    @FXML
    private TextField textFieldNumberParticipants;
    @FXML
    private TextField textFieldNumberCollaborators;
    @FXML
    private TextArea textAreaGeneralObjective;
    @FXML
    private TextArea textAreaMission;
    @FXML
    private TextArea textAreaVision;
    @FXML
    private Button buttonEditInformation;
    @FXML
    private Button buttonMembersList;
    @FXML
    private Button buttonSaveChanges;
    @FXML
    private Button buttonExit;
    @FXML
    private DatePicker datePickerRegistrationDate;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        resumeInfo.select(memberObject.getIdMember());
        textFieldAreaName.setText(resumeObject.getNameResume());
        textFieldFacultyInstitution.setText(academicBodyObject.getInstitucionalFaculty());
        textFieldDependencyInstitution.setText(academicBodyObject.getDependecyInstitution());
        textFieldDegreeConsolidation.setText(academicBodyObject.getDegreeConsolidation());
        textFieldKeyCode.setText(academicBodyObject.getKeyCode());
        textFieldNumberParticipants.setText(Integer.toString(academicBodyObject.getNumberParticipants()));
        textFieldNumberCollaborators.setText(Integer.toString(academicBodyObject.getNumberCollaborators()));
        datePickerRegistrationDate.setValue(LOCAL_DATE(academicBodyObject.getRegistrationDate().toString()));
        textAreaGeneralObjective.setText(resumeObject.getGeneralObjetive());
        textAreaMission.setText(resumeObject.getMission());
        textAreaVision.setText(resumeObject.getVision());
        informationBlock(true);
        
    }    

    @FXML
    private void editInformation(ActionEvent event) {
        informationBlock(false);
    }

    @FXML
    private void viewMembersList(ActionEvent event) {
    }

    @FXML
    private void saveChanges(ActionEvent event) {
        informationBlock(true);
        String areaName = textFieldAreaName.getText();
        String generalObjective = textAreaGeneralObjective.getText();
        String mission = textAreaMission.getText();
        String vision = textAreaVision.getText();
        
        if((areaName == null) && (generalObjective == null) && (mission == null) && (vision == null)){
            resumeObject.setNameResume(areaName);
            resumeObject.setGeneralObjetive(generalObjective);
            resumeObject.setMission(mission);
            resumeObject.setVision(vision);
            resumeInfo.insert(resumeObject, memberObject.getIdMember());
        } else {
            resumeObject.setNameResume(areaName);
            resumeObject.setGeneralObjetive(generalObjective);
            resumeObject.setMission(mission);
            resumeObject.setVision(vision);
            resumeInfo.update(resumeObject, memberObject.getIdMember());
        }
        
    }

    @FXML
    private void exitToHome(ActionEvent event) {
        navegationScreen("gui/ScreenHome.fxml");
    }
    
    private void informationBlock(boolean value){
        textAreaGeneralObjective.setDisable(value);
        textAreaMission.setDisable(value);
        textAreaVision.setDisable(value);
        textFieldAreaName.setDisable(value);
        textFieldDegreeConsolidation.setDisable(value);
        textFieldDependencyInstitution.setDisable(value);
        textFieldFacultyInstitution.setDisable(value);
        textFieldKeyCode.setDisable(value);
        textFieldNumberCollaborators.setDisable(value);
        textFieldNumberParticipants.setDisable(value);
        datePickerRegistrationDate.setDisable(value);
        buttonSaveChanges.setVisible(!value);
        buttonEditInformation.setVisible(value);
        buttonMembersList.setVisible(value);
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
    
    public static final LocalDate LOCAL_DATE (String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }
}
