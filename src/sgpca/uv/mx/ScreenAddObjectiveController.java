package sgpca.uv.mx;

import sgpca.uv.mx.utilities.AlertGuis;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sgpca.uv.mx.businesslogic.ActionObjectiveDAO;
import sgpca.uv.mx.businesslogic.ObjectiveDAO;
import sgpca.uv.mx.domain.ActionObjective;
import sgpca.uv.mx.domain.Objective;
import sgpca.uv.mx.domain.Workplan;


public class ScreenAddObjectiveController implements Initializable {
    Workplan workplanInfo = new Workplan();
    ActionObjectiveDAO actionInfo = new ActionObjectiveDAO();
    ObjectiveDAO objectiveInfo = new ObjectiveDAO();
    Objective objectiveObject = new Objective();
    ActionObjective actionObjectiveObject = new ActionObjective();
    AlertGuis alertGuis = new AlertGuis();
 

    @FXML
    private Button buttonAddObjective;
    @FXML
    private TextField textFieldObjectiveTitle;
    @FXML
    private TextField textFieldObjectiveStrategy;
    @FXML
    private TextArea textFieldObjectiveDescription;
    @FXML
    private TextArea textFieldObjectiveGoal;
    @FXML
    private Button buttonDeleteAction;
    @FXML
    private TextField textFieldActionTitle;
    @FXML
    private TextArea textFieldActionDescription;
    @FXML
    private TextField textFieldActionResult;
    @FXML
    private TableView<ActionObjective> tableViewAction;
    @FXML
    private TableColumn tableColumnActionTitle;
    @FXML
    private TableColumn tableColumnActionDescription;
    @FXML
    private TableColumn tableColumnActionResult;
    @FXML
    private Button buttonExitObjective;
    @FXML
    private AnchorPane anchorPanelObjective;
    @FXML
    private Button buttonAddAction;

    private ObservableList<ActionObjective> observableTable;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        buttonAddAction.setVisible(false);
        buttonDeleteAction.setVisible(false);
    }    

    @FXML
    private void actionAddObjective(ActionEvent event) {
        String title = textFieldObjectiveTitle.getText();
        String strategy = textFieldObjectiveStrategy.getText();
        String goal = textFieldObjectiveGoal.getText();
        String description = textFieldObjectiveDescription.getText();
        if(title.equals(null) || title.equals("")){
            alertGuis.alertInformation("Requested data not entered", "Verify that the field marked with (*) has some data entered.", "");
        } else{
            objectiveObject.setTitle(title);
            objectiveObject.setStrategy(strategy);
            objectiveObject.setResult(null);
            objectiveObject.setGoal(goal);
            objectiveObject.setDescription(description);
            objectiveObject.setTargetState("Pendiente");
            objectiveInfo.insert(objectiveObject, Workplan.getIdWorkplan());
            buttonAddObjective.setVisible(false);
            buttonAddAction.setVisible(true);
            buttonDeleteAction.setVisible(true);
            valuesNotEditable();
        }
    }

    @FXML
    private void actionDeleteAction(ActionEvent event) {
        actionInfo.delete(actionObjectiveObject.getIdStaticAction());
        showTable();
    }

    @FXML
    private void actionAddAction(ActionEvent event) {
        String title = textFieldActionTitle.getText();
        String decription = textFieldActionDescription.getText();
        String result = textFieldActionResult.getText();
        Objective.setIdObjective(objectiveInfo.selectIdObject(textFieldObjectiveTitle.getText(), Workplan.getIdWorkplan()));
        if(title.equals(null) || title.equals("")){
            alertGuis.alertInformation("Requested data not entered", "Verify that the field marked with (*) has some data entered.", "");
        } else{
            actionObjectiveObject.setTitle(title);
            actionObjectiveObject.setDescription(decription);
            actionObjectiveObject.setResult(result);
            actionInfo.insert(actionObjectiveObject, Objective.getIdObjective());
            showTable();
            clearTextFieldsOfActionObjective();
        }
    }

    @FXML
    private void actionExitObject(ActionEvent event) {
        try{
            Stage stage = (Stage) buttonExitObjective.getScene().getWindow();
            Scene escena = new Scene(FXMLLoader.load(getClass().getResource("gui/ScreenManageBodyWorkplan.fxml")));
            stage.setScene(escena);
            stage.setResizable(false);
            stage.show();
        }catch(IOException ex){
            Logger.getLogger(ScreenManageBodyWorkplanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void valuesNotEditable(){
        textFieldObjectiveDescription.setDisable(true);
        textFieldObjectiveGoal.setDisable(true);
        textFieldObjectiveStrategy.setDisable(true);
        textFieldObjectiveTitle.setDisable(true);
    }
    
    private void showTable(){
        observableTable = FXCollections.observableArrayList();
        this.tableColumnActionTitle.setCellValueFactory(new PropertyValueFactory("title"));
        this.tableColumnActionDescription.setCellValueFactory(new PropertyValueFactory("description"));
        this.tableColumnActionResult.setCellValueFactory(new PropertyValueFactory("result"));
        tableViewAction.setItems(actionInfo.loadList(observableTable, Objective.getIdObjective()));
    }
    
    private void clearTextFieldsOfActionObjective(){
        textFieldActionTitle.setText("");
        textFieldActionDescription.setText("");
        textFieldActionResult.setText("");
    }

    @FXML
    private void deleteActionSelect(MouseEvent event) {
        try{
            ActionObjective actionSelectoToDelete = tableViewAction.getSelectionModel().getSelectedItem();
            int idActionObjective = actionSelectoToDelete.getIdAction();
            actionObjectiveObject.setIdStaticAction(idActionObjective);
        }catch(RuntimeException exception){
            alertGuis.alertError("Selection not confirmed ", "No selected action found.", "");
        }
    }
}
