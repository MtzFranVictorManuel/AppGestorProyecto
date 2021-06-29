package sgpca.uv.mx.businesslogic;

import sgpca.uv.mx.dataacces.ConnectDB;
import sgpca.uv.mx.domain.ActionObjective;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;

/**
 *
 * @author azul_
 */
public class ActionObjectiveDAO implements IActionObjectiveDAO{
    private Connection connectionTransmission;
    Connection connect = null;
    PreparedStatement preStatement = null;
    private static final String SQL_INSERT = "INSERT INTO tbl_accion (titulo, descripcion, fkObjetivo, resultado) VALUES (?, ?, ?, ?);";
    private static final String SQL_SELECT = "SELECT * FROM tbl_accion WHERE fkObjetivo = ?;";
    private static final String SQL_UPDATE = "UPDATE tbl_accion SET titulo = ?, descripcion = ? WHERE titulo = ? AND fkObjetivo = ?;";
    private static final String SQL_DELETE = "DELETE FROM tbl_accion WHERE idAccion = ?;";
    
    /**
     *
     * @param action
     * @param idObjective
     * @return
     */
    @Override
    public int insert(ActionObjective action, int idObjective){
        connect = ConnectDB.getConexion();
        int rows = 0;
        if(connect != null){
            try{
                preStatement = connect.prepareStatement(SQL_INSERT);
                preStatement.setString(1, action.getTitle());
                preStatement.setString(2, action.getDescription());
                preStatement.setInt(3, idObjective);
                preStatement.setString(4, action.getResult());
                rows = preStatement.executeUpdate();     
            }
            catch (SQLException exception) {
                Logger.getLogger(ActionObjectiveDAO.class.getName()).log(Level.SEVERE, null, exception);
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
    
    @Override
    public ActionObjective select(int idObjective){
        connect = ConnectDB.getConexion();
        ActionObjective action = null;
        if(connect != null){
            try{
                preStatement = connect.prepareStatement(SQL_SELECT);
                preStatement.setInt(1, idObjective);
                ResultSet rSet = preStatement.executeQuery();
                if(rSet.next()){ 
                    action = new ActionObjective();
                    action.setIdAction(rSet.getInt("idAccion"));
                    action.setTitle(rSet.getString("titulo"));
                    action.setDescription(rSet.getString("descripcion"));
                    ConnectDB.close(rSet);
                    return action;
                }
            }
            catch (SQLException exception) {
                Logger.getLogger(ActionObjectiveDAO.class.getName()).log(Level.SEVERE, null, exception);
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
    
    @Override
    public int update(ActionObjective action, String titulo, int idObjective){
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
                Logger.getLogger(ActionObjectiveDAO.class.getName()).log(Level.SEVERE, null, exception);
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
    
    @Override
    public int delete(int idAccion){
        connect = ConnectDB.getConexion();
        int rows = 0;
        if(connect != null){
            try{
                preStatement = connect.prepareStatement(SQL_DELETE);
                preStatement.setInt(1, idAccion);
                rows = preStatement.executeUpdate();
            }
            catch (SQLException exception) {
                Logger.getLogger(ActionObjectiveDAO.class.getName()).log(Level.SEVERE, null, exception);
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
    
    public ObservableList<ActionObjective> loadList(ObservableList<ActionObjective> listActionObjective, int idObjective){
        connect = ConnectDB.getConexion();
        ActionObjective action = null;
        if(connect != null){
            try{
                preStatement = connect.prepareStatement(SQL_SELECT);
                preStatement.setInt(1, idObjective);
                ResultSet resultSet = preStatement.executeQuery();
                while(resultSet.next()){ 
                    action = new ActionObjective();
                    action.setIdAction(resultSet.getInt("idAccion"));
                    action.setTitle(resultSet.getString("titulo"));
                    action.setDescription(resultSet.getString("descripcion"));
                    action.setResult(resultSet.getString("resultado"));
                    listActionObjective.add(action);
                }
                ConnectDB.close(resultSet);
                return listActionObjective;
            }
            catch (SQLException exception) {
                Logger.getLogger(ActionObjectiveDAO.class.getName()).log(Level.SEVERE, null, exception);
            }
            finally{
                ConnectDB.close(preStatement);
                if(this.connectionTransmission == null){
                    ConnectDB.close(connect);
                }
            }
        }
        return listActionObjective;
    }
}
