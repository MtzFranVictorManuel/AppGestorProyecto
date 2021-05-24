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
import sgpca.bussinesslogic.EvidenceDAO;
import sgpca.domain.Evidence;

public class EvidenceListController implements Initializable {

    @FXML
    private TableView<Evidence> tableEvidenceList;
    @FXML
    private TableColumn columnName;
    @FXML
    private TableColumn columnAutor;
    @FXML
    private TableColumn columnType;
    @FXML
    private TableColumn columnDate;
    @FXML
    private Button buttonSearchEvidence;
    @FXML
    private TextField textFieldSearchEvidence;
    @FXML
    private Button buttonRegisterEvidence;
    @FXML
    private Button buttonExitEvidenceList;
    
    private ObservableList<Evidence> evidences; 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        obtainEvidences();
    }    

    
    @FXML
    private void searchEvidence(ActionEvent event) {
        EvidenceDAO evidencedao = new EvidenceDAO();
                
        if(textFieldSearchEvidence.getText().isEmpty()){
            obtainEvidences();
        }else{
            String searchedName = textFieldSearchEvidence.getText();
            evidences = FXCollections.observableArrayList(evidencedao.searchEvidenceByName(searchedName));

            columnName.setCellValueFactory(new PropertyValueFactory("evidenceName"));
            columnAutor.setCellValueFactory(new PropertyValueFactory("autor"));
            columnType.setCellValueFactory(new PropertyValueFactory("evidenceType"));
            columnDate.setCellValueFactory(new PropertyValueFactory("date"));

            tableEvidenceList.setItems(evidences);
        }
    }

    
    @FXML
    private void registerEvidence(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EvidenceView.fxml"));
            Parent root = loader.load();
            EvidenceController controller = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Registro Dispositivo");
            stage.setScene(scene);
            stage.show();
            
            stage.setOnCloseRequest(e -> controller.closeEvidenceWindow());
            
            Stage myStage = (Stage) this.buttonRegisterEvidence.getScene().getWindow();
            myStage.close();
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    
    @FXML
    private void exitEvidenceList(ActionEvent event) {
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
    
    
    private void obtainEvidences(){
        EvidenceDAO evidencedao = new EvidenceDAO();
        evidences = FXCollections.observableArrayList(evidencedao.consultEvidenceList());
        
        columnName.setCellValueFactory(new PropertyValueFactory("evidenceName"));
        columnAutor.setCellValueFactory(new PropertyValueFactory("autor"));
        columnType.setCellValueFactory(new PropertyValueFactory("evidenceType"));
        columnDate.setCellValueFactory(new PropertyValueFactory("date"));
        
        tableEvidenceList.setItems(evidences);
    }
    
    
    
}
