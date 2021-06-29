/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sgpca.uv.mx.businesslogic.ActionObjectiveDAO;
import sgpca.uv.mx.businesslogic.ObjectiveDAO;
import sgpca.uv.mx.domain.ActionObjective;
import sgpca.uv.mx.domain.Objective;

/**
 * FXML Controller class
 *
 * @author azul_
 */
public class ScreenEditObjectiveController implements Initializable {
    Objective objectiveObject = new Objective();
    ObjectiveDAO objectiveInfo = new ObjectiveDAO();
    ActionObjectiveDAO actionInfo = new ActionObjectiveDAO();
    ActionObjective actionObjectiveObject = new ActionObjective();
    AlertGuis alertGuis = new AlertGuis();

    @FXML
    private Button buttonEditObjective;
    
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
    private Button buttonAddAction;
    
    @FXML
    private ChoiceBox<String> choiceBoxObjectiveState;
    
    
    private ObservableList<ActionObjective> observableTable;
    
    @FXML
    private Button buttonSaveChanges;
    
    @FXML
    private Button buttonCancelChanges;
    
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textFieldObjectiveTitle.setText(objectiveInfo.select(Objective.getIdObjective()).getTitle());
        textFieldObjectiveStrategy.setText(objectiveInfo.select(Objective.getIdObjective()).getStrategy());
        textFieldObjectiveGoal.setText(objectiveInfo.select(Objective.getIdObjective()).getGoal());
        textFieldObjectiveDescription.setText(objectiveInfo.select(Objective.getIdObjective()).getDescription());
        choiceBoxObjectiveState.setValue(objectiveInfo.select(Objective.getIdObjective()).getTargetState());
        buttonCancelChanges.setText("Cancel");
        loadDataChoiceBox();
        visibleButtons(true);
        valuesNotvaluesNotEditableFromObjetive(true);
        showTable();
    }    

    @FXML
    private void exitToHome(ActionEvent event) {
        navigationScreen("gui/ScreenHome.fxml");
    }

    @FXML
    private void actionEditObjective(ActionEvent event) {
        System.out.println(choiceBoxObjectiveState.getValue());
        valuesNotvaluesNotEditableFromObjetive(false);
        visibleButtons(false);
        if(choiceBoxObjectiveState.getValue().equals("Completado") || choiceBoxObjectiveState.getValue().equals("completado")){
            choiceBoxObjectiveState.setDisable(true);
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
    private void deleteActionSelect(MouseEvent event) {
        try{
            ActionObjective actionSelectoToDelete = tableViewAction.getSelectionModel().getSelectedItem();
            int idActionObjective = actionSelectoToDelete.getIdAction();
            actionObjectiveObject.setIdStaticAction(idActionObjective);
        }catch(RuntimeException exception){
            alertGuis.alertError("Selection not confirmed ", "No selected action found.", "");
        }
    }

    @FXML
    private void actionSaveChanges(ActionEvent event) {
        String title = textFieldObjectiveTitle.getText();
        String strategy = textFieldObjectiveStrategy.getText();
        String goal = textFieldObjectiveGoal.getText();
        String description = textFieldObjectiveDescription.getText();
        String selectValue = choiceBoxObjectiveState.getValue();
        System.out.println(Objective.getIdObjective());
        if(title.equals(null) || title.equals("")){
            alertGuis.alertInformation("Requested data not entered", "Verify that the field marked with (*) has some data entered.", "");
        } else{
            Objective objectiveUpdate = new Objective(title, strategy, null, goal, description, selectValue);
            objectiveInfo.update(objectiveUpdate, Objective.getIdObjective(), title);
            visibleButtons(true);
            valuesNotvaluesNotEditableFromObjetive(true);
        }
    }

    @FXML
    private void actionCancelChanges(ActionEvent event) {
        if(alertGuis.alertConfirmation("Confirm editing cancellation.", "You "
                + "are about to cancel the edition of the objective, if you press "
                + "the accept button, the changes will not be applied.", "") == true){
            visibleButtons(true);
            valuesNotvaluesNotEditableFromObjetive(true);
        }
    }
        
    private void clearTextFieldsOfActionObjective(){
        textFieldActionTitle.setText("");
        textFieldActionDescription.setText("");
        textFieldActionResult.setText("");
    }
    
    private void visibleButtons(boolean value){
        buttonEditObjective.setVisible(value);
        buttonCancelChanges.setVisible(!value);
        buttonSaveChanges.setVisible(!value);
    }
        
    private void showTable(){
        observableTable = FXCollections.observableArrayList();
        this.tableColumnActionTitle.setCellValueFactory(new PropertyValueFactory("title"));
        this.tableColumnActionDescription.setCellValueFactory(new PropertyValueFactory("description"));
        this.tableColumnActionResult.setCellValueFactory(new PropertyValueFactory("result"));
        tableViewAction.setItems(actionInfo.loadList(observableTable, Objective.getIdObjective()));
    }
    
    private void valuesNotvaluesNotEditableFromObjetive(boolean value){
        textFieldObjectiveTitle.setDisable(value);
        textFieldObjectiveStrategy.setDisable(value);
        textFieldObjectiveGoal.setDisable(value);
        textFieldObjectiveDescription.setDisable(value);
        choiceBoxObjectiveState.setDisable(value);
    }
    
    private void loadDataChoiceBox(){
        ObservableList listOptions = FXCollections.observableArrayList();
        listOptions.removeAll(listOptions);
        String pending = "Pendiente";
        String complete = "Completado";
        listOptions.addAll(pending, complete);
        choiceBoxObjectiveState.getItems().addAll(listOptions);
    }
    
     public void navigationScreen(String url){
        try{
            Stage stage = (Stage) buttonAddAction.getScene().getWindow();
            Scene escena = new Scene(FXMLLoader.load(getClass().getResource(url)));
            stage.setScene(escena);
            stage.setResizable(false);
            stage.show();
        }catch(IOException ex){
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
