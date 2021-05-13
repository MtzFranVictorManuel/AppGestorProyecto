
package appgestorproyecto;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author azul_
 */
public class FXMLRunMain extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        try{
        Parent root = FXMLLoader.load(getClass().getResource("gui/ScreenLogin.fxml"));       
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
