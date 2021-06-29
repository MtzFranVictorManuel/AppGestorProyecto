/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgpca.uv.mx;

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
import javafx.scene.control.Label;
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
import sgpca.uv.mx.domain.Workplan;

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
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textFieldObjectiveTitle.setText(objectiveInfo.select(Objective.getIdObjective()).getTitle());
        textFieldObjectiveStrategy.setText(objectiveInfo.select(Objective.getIdObjective()).getStrategy());
        textFieldObjectiveGoal.setText(objectiveInfo.select(Objective.getIdObjective()).getGoal());
        textFieldObjectiveDescription.setText(objectiveInfo.select(Objective.getIdObjective()).getDescription());
        choiceBoxObjectiveState.setValue(objectiveInfo.select(Objective.getIdObjective()).getTargetState());
        loadDataChoiceBox();
        valuesNotvaluesNotEditableFromObjetive(true);
        showTable();
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

    @FXML
    private void exitToHome(ActionEvent event) {
        navigationScreen("gui/ScreenHome.fxml");
    }

    @FXML
    private void actionEditObjective(ActionEvent event) {
        System.out.println(choiceBoxObjectiveState.getValue());
        valuesNotvaluesNotEditableFromObjetive(false);
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
        actionObjectiveObject.setTitle(title);
        actionObjectiveObject.setDescription(decription);
        actionObjectiveObject.setResult(result);
        actionInfo.insert(actionObjectiveObject, Objective.getIdObjective());
        showTable();
        clearTextFieldsOfActionObjective();
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

    @FXML
    private void deleteActionSelect(MouseEvent event) {
        ActionObjective actionSelectoToDelete = tableViewAction.getSelectionModel().getSelectedItem();
        int idActionObjective = actionSelectoToDelete.getIdAction();
        actionObjectiveObject.setIdStaticAction(idActionObjective);
    }
    
    private void clearTextFieldsOfActionObjective(){
        textFieldActionTitle.setText("");
        textFieldActionDescription.setText("");
        textFieldActionResult.setText("");
    }
}
