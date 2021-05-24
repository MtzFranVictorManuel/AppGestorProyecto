package sgpca.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sgpca.bussinesslogic.ResearchProyectDAO;
import sgpca.domain.ResearchProyect;

public class ProyectListController implements Initializable {

    @FXML
    private Button buttonSearch;
    @FXML
    private TableColumn columnName;
    @FXML
    private TableColumn columnStartDate;
    @FXML
    private TableColumn columnFinishDate;
    @FXML
    private Button buttonRegisterProyect;
    @FXML
    private Button buttonExit;
    @FXML
    private TableView tableProyectList;
    
    private ObservableList<ResearchProyect> proyectList;
    @FXML
    private TextField textFieldSearchProyect;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        obtainResearchProyects();
    }    

    @FXML
    private void searchProyect(ActionEvent event) {
        ResearchProyectDAO proyectdao = new ResearchProyectDAO();
        
        if(textFieldSearchProyect.getText().isEmpty()){
            obtainResearchProyects();
        }else{
            String searchProyect = textFieldSearchProyect.getText();
            proyectList = FXCollections.observableArrayList(proyectdao.searchRPByName(searchProyect));
            columnName.setCellValueFactory(new PropertyValueFactory("proyectTitle"));
            columnStartDate.setCellValueFactory(new PropertyValueFactory("startDate"));
            columnFinishDate.setCellValueFactory(new PropertyValueFactory("endDate"));

            tableProyectList.setItems(proyectList);
        }
    }

    @FXML
    private void registerProyect(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ResearchProyectView.fxml"));
            Parent root = loader.load();
            ResearchProyectController controller = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("");
            stage.setScene(scene);
            stage.show();
            
            stage.setOnCloseRequest(e -> controller.closeWindow());
            
            Stage myStage = (Stage) this.buttonRegisterProyect.getScene().getWindow();
            myStage.close();
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void exitProyectList(ActionEvent event) {
        Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
        exitAlert.setHeaderText(null);
        exitAlert.setTitle("Exit");
        exitAlert.setContentText("");
        exitAlert.showAndWait();
        Optional<ButtonType> action = exitAlert.showAndWait();
        
        if(action.get() == ButtonType.OK){
            //close Window
        }
    }
    
    private void obtainResearchProyects(){
        ResearchProyectDAO proyectdao = new ResearchProyectDAO();
        proyectList = FXCollections.observableArrayList(proyectdao.consultRProyectList());
        columnName.setCellValueFactory(new PropertyValueFactory("proyectTitle"));
        columnStartDate.setCellValueFactory(new PropertyValueFactory("startDate"));
        columnFinishDate.setCellValueFactory(new PropertyValueFactory("endDate"));
        
        
        tableProyectList.setItems(proyectList);
    }
}
