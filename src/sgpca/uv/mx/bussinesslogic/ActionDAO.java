package sgpca.uv.mx.bussinesslogic;

import sgpca.uv.mx.dataacces.ConnectDB;
import sgpca.uv.mx.domain.Action;
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
public class ActionDAO implements IActionDAO{
    private Connection connectionTransmission;
    Connection connect = null;
    PreparedStatement preStatement = null;
    private static final String SQL_INSERT = "INSERT INTO tbl_accion (titulo, descripcion, fkObjetivo) VALUES (?, ?, ?);";
    private static final String SQL_SELECT = "SELECT * FROM tbl_accion WHERE fkObjetivo = ?;";
    private static final String SQL_UPDATE = "UPDATE tbl_accion SET titulo = ?, descripcion = ? WHERE titulo = ? AND fkObjetivo = ?;";
    private static final String SQL_DELETE = "DELETE FROM tbl_accion WHERE titulo = ? AND idAccion = ?;";
    
    public int insert(Action action, int idObjective){
        connect = ConnectDB.getConexion();
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
                ConnectDB.close(preStatement);
                if(this.connectionTransmission == null){
                    ConnectDB.close(connect);
                }
            }
        }
        return rows;
    }
    
    public Action select(int idObjective){
        connect = ConnectDB.getConexion();
        Action action = null;
        if(connect != null){
            try{
                preStatement = connect.prepareStatement(SQL_SELECT);
                preStatement.setInt(1, idObjective);
                ResultSet rSet = preStatement.executeQuery();
                if(rSet.next()){ 
                    action = new Action();
                    action.setIdAction(rSet.getInt("idAccion"));
                    action.setTitle(rSet.getString("titulo"));
                    action.setDescription(rSet.getString("descripcion"));
                    ConnectDB.close(rSet);
                    return action;
                }
            }
            catch (SQLException exception) {
                Logger.getLogger(ActionDAO.class.getName()).log(Level.SEVERE, null, exception);
            }
            finally{
                ConnectDB.close(preStatement);
                if(this.connectionTransmission == null){
                    ConnectDB.close(connect);
                }
            }
        }
        return action;
    }
    
    public int update(Action action, String titulo, int idObjective){
        connect = ConnectDB.getConexion();
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
                ConnectDB.close(preStatement);
                if(this.connectionTransmission == null){
                    ConnectDB.close(connect);
                }
            }
        }
        return rows;
    }
    
    public int delete(String title, int idAccion){
        connect = ConnectDB.getConexion();
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
                ConnectDB.close(preStatement);
                if(this.connectionTransmission == null){
                    ConnectDB.close(connect);
                }
            }
        }
        return rows;
    }
}
