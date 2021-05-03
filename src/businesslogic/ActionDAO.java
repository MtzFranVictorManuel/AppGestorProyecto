package businesslogic;

import dataacces.DBconnection;
import domain.Action;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author azul_
 */
public class ActionDAO {
    private Connection connectionTransmission;
    Connection connect = DBconnection.getConexion();
    PreparedStatement preStatement = null;
    private static final String SQL_INSERT = "INSERT INTO tbl_accion (titulo, descripcion, fkObjetivo) VALUES (?, ?, ?);";
    private static final String SQL_SELECT = "SELECT * FROM tbl_accion WHERE fkObjetivo = ?;";
    private static final String SQL_UPDATE = "UPDATE tbl_accion SET titulo = ?, descripcion = ? WHERE titulo = ? AND fkObjetivo = ?;";
    private static final String SQL_DELETE = "DELETE FROM tbl_accion WHERE titulo = ? AND idAccion = ?;";
    
    public int insert(Action action, int idObjective){
        int rows = 0;
        if(connect != null){
            try{
                preStatement = connect.prepareStatement(SQL_INSERT);
                preStatement.setString(1, action.getTitle());
                preStatement.setString(2, action.getDescription());
                preStatement.setInt(3, idObjective);
                rows = preStatement.executeUpdate();     
            }
            catch (SQLException exception) {
                Logger.getLogger(ActionDAO.class.getName()).log(Level.SEVERE, null, exception);
            }
            finally{
                DBconnection.close(preStatement);
                if(this.connectionTransmission == null){
                    DBconnection.close(connect);
                }
            }
        }
        return rows;
    }
    
    public Action select(int idObjective){
        Action action = null;
        if(connect != null){
            try{
                preStatement = connect.prepareStatement(SQL_SELECT);
                preStatement.setInt(1, idObjective);
                ResultSet rSet = preStatement.executeQuery();
                if(rSet.next()){
                    int idAction = rSet.getInt("idAccion");
                    String title = rSet.getString("titulo");
                    String description = rSet.getString("descripcion");
                    
                    action = new Action();
                    action.setIdAction(idAction);
                    action.setTitle(title);
                    action.setDescription(description);
                    DBconnection.close(rSet);
                    return action;
                }
            }
            catch (SQLException exception) {
                Logger.getLogger(ActionDAO.class.getName()).log(Level.SEVERE, null, exception);
            }
            finally{
                DBconnection.close(preStatement);
                if(this.connectionTransmission == null){
                    DBconnection.close(connect);
                }
            }
        }
        return action;
    }
    
    public int update(Action action, String titulo, int idObjective){
        int rows = 0;
        if(connect != null){
            try{
                preStatement = connect.prepareStatement(SQL_UPDATE);
                preStatement.setString(1, action.getTitle());
                preStatement.setString(2, action.getDescription());
                preStatement.setString(3, titulo);
                preStatement.setInt(4, idObjective);
                rows = preStatement.executeUpdate();
            }
            catch (SQLException exception) {
                Logger.getLogger(ActionDAO.class.getName()).log(Level.SEVERE, null, exception);
            }
            finally{
                DBconnection.close(preStatement);
                if(this.connectionTransmission == null){
                    DBconnection.close(connect);
                }
            }
        }
        return rows;
    }
    
    public int delete(String title, int idAccion){
        int rows = 0;
        if(connect != null){
            try{
                preStatement = connect.prepareStatement(SQL_DELETE);
                preStatement.setString(1, title);
                preStatement.setInt(2, idAccion);
                rows = preStatement.executeUpdate();
            }
            catch (SQLException exception) {
                Logger.getLogger(ActionDAO.class.getName()).log(Level.SEVERE, null, exception);
            }
            finally{
                DBconnection.close(preStatement);
                if(this.connectionTransmission == null){
                    DBconnection.close(connect);
                }
            }
        }
        return rows;
    }
}
