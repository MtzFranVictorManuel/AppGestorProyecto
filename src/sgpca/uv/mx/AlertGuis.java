package sgpca.uv.mx;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author azul_
 */
public class AlertGuis {
    
    public void alertError(String title, String headerText, String contentText){
        Alert alertEmptyInfo = new Alert(Alert.AlertType.ERROR);
        alertEmptyInfo.setTitle(title);
        alertEmptyInfo.setHeaderText(headerText);
        alertEmptyInfo.setContentText(contentText);
        alertEmptyInfo.showAndWait(); 
    }
    
    public boolean alertConfirmation(String title, String headerText, String contentText){
        Alert alertEmptyInfo = new Alert(Alert.AlertType.CONFIRMATION);
        alertEmptyInfo.setTitle(title);
        alertEmptyInfo.setHeaderText(headerText);
        alertEmptyInfo.setContentText(contentText);
        Optional<ButtonType> result = alertEmptyInfo.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            return true;
        }
        return false;
    }
    
    public void alertInformation(String title, String headerText, String contentText){
        Alert alertEmptyInfo = new Alert(Alert.AlertType.INFORMATION);
        alertEmptyInfo.setTitle(title);
        alertEmptyInfo.setHeaderText(headerText);
        alertEmptyInfo.setContentText(contentText);
        alertEmptyInfo.showAndWait(); 
    }
    
    public static void alertWarning(String title, String headerText, String contentText){
        Alert alertEmptyInfo = new Alert(Alert.AlertType.WARNING);
        alertEmptyInfo.setTitle(title);
        alertEmptyInfo.setHeaderText(headerText);
        alertEmptyInfo.setContentText(contentText);
        alertEmptyInfo.showAndWait(); 
    }
}
