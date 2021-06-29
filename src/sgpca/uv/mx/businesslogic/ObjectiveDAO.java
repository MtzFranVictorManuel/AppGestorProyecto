package sgpca.uv.mx.businesslogic;

import sgpca.uv.mx.domain.Objective;
import sgpca.uv.mx.dataacces.ConnectDB;
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
public class ObjectiveDAO implements IObjectiveDAO{
    private Connection connectionTransmission;
    Connection connect = null;
    PreparedStatement preStatement = null;
    private static final String SQL_INSERT = "INSERT INTO tbl_objetivo (titulo, estrategia, resultado, meta, descripcion, estadoObjetivo, fkPlanTrabajo) VALUES (?, ?, ?, ?, ?, ?, ?);";
    private static final String SQL_SELECT = "SELECT * FROM tbl_objetivo WHERE  idObjetivo = ?;";
    private static final String SQL_SELECTOBJECTIVE = "SELECT * FROM tbl_objetivo WHERE fkPlanTrabajo = ?;";
    private static final String SQL_SELECTIDOBJETIVE = "SELECT idObjetivo FROM tbl_objetivo WHERE titulo = ? AND fkPlanTrabajo = ?;";
    private static final String SQL_UPDATE = "UPDATE tbl_objetivo SET titulo = ?, estrategia = ?, resultado = ?, meta = ?, descripcion = ? WHERE titulo = ? AND fkPlanTrabajo  = ?;";
    private static final String SQL_DELETE = "DELETE FROM tbl_objetivo WHERE titulo = ? AND fkPlanTrabajo = ?;";
    private static final String SQL_SELECTESTATUSTARGET = "SELECT * FROM tbl_objetivo WHERE estadoObjetivo = ? AND fkPlanTrabajo = ?;";


    public ObjectiveDAO() {
    }

    public ObjectiveDAO(Connection connectionTransmission) {
        this.connectionTransmission = connectionTransmission;
    }
    
    
    @Override
    public int insert(Objective objective, int idWorkplan){
        connect = ConnectDB.getConexion();
        int rows = 0;
        if(connect != null){
            try{
                preStatement = connect.prepareStatement(SQL_INSERT);
                preStatement.setString(1, objective.getTitle());
                preStatement.setString(2, objective.getStrategy());
                preStatement.setString(3, objective.getResult());
                preStatement.setString(4, objective.getGoal());
                preStatement.setString(5, objective.getDescription());
                preStatement.setString(6, objective.getTargetState());
                preStatement.setInt(7, idWorkplan);
                rows = preStatement.executeUpdate();
            }
            catch (SQLException exception) {
                Logger.getLogger(ObjectiveDAO.class.getName()).log(Level.SEVERE, null, exception);
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
    public Objective select(int idObjective){
        connect = ConnectDB.getConexion();
        Objective objective = null;
        if(connect != null){
            try{
                preStatement = connect.prepareStatement(SQL_SELECT);
                preStatement.setInt(1, idObjective);
                ResultSet resultSet = preStatement.executeQuery();
                if(resultSet.next()){
                    objective = new Objective();
                    objective.setTitle(resultSet.getString("titulo"));
                    objective.setStrategy(resultSet.getString("estrategia"));
                    objective.setResult(resultSet.getString("resultado"));
                    objective.setGoal(resultSet.getString("meta"));
                    objective.setDescription(resultSet.getString("descripcion"));
                    objective.setTargetState(resultSet.getString("estadoObjetivo"));
                    ConnectDB.close(resultSet);
                    return objective;
                }
            }
            catch (SQLException exception) {
                Logger.getLogger(ObjectiveDAO.class.getName()).log(Level.SEVERE, null, exception);
            }
            finally{
                ConnectDB.close(preStatement);
                if(this.connectionTransmission == null){
                    ConnectDB.close(connect);
                }
            }
        }
        return objective;
    }
    
    public int selectIdObject(String objectiveTitle, int idWorkplan){
        connect = ConnectDB.getConexion();
        if(connect != null){
            try{
                preStatement = connect.prepareStatement(SQL_SELECTIDOBJETIVE);
                preStatement.setString(1, objectiveTitle);
                preStatement.setInt(2, idWorkplan);
                ResultSet resultSet = preStatement.executeQuery();
                if(resultSet.next()){  
                    int idObjective = resultSet.getInt("idObjetivo");  
                    ConnectDB.close(resultSet);
                    return  idObjective;
                }
            }
            catch (SQLException exception) {
                Logger.getLogger(ObjectiveDAO.class.getName()).log(Level.SEVERE, null, exception);
            }
            finally{
                ConnectDB.close(preStatement);
                if(this.connectionTransmission == null){
                    ConnectDB.close(connect);
                }
            }
        }
        return 0;
    }
    
    @Override
    public int update(Objective objective, int idWorkplan, String title){
        connect = ConnectDB.getConexion();
        int rows = 0;
        if(connect != null){
            try{
                preStatement = connect.prepareStatement(SQL_UPDATE);
                preStatement.setString(1, objective.getTitle());
                preStatement.setString(2, objective.getStrategy());
                preStatement.setString(3, objective.getResult());
                preStatement.setString(4, objective.getGoal());
                preStatement.setString(5, objective.getDescription());
                preStatement.setString(6, title);
                preStatement.setInt(7, idWorkplan);
            }
            catch (SQLException exception) {
                Logger.getLogger(ObjectiveDAO.class.getName()).log(Level.SEVERE, null, exception);
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
    public int delete(int idWorkplan, String title){
        connect = ConnectDB.getConexion();
        int rows = 0;
        if(connect != null){
            try{
                preStatement = connect.prepareStatement(SQL_DELETE);
                preStatement.setString(1, title);
                preStatement.setInt(2, idWorkplan);
                rows = preStatement.executeUpdate();
            }
            catch (SQLException exception) {
                Logger.getLogger(ObjectiveDAO.class.getName()).log(Level.SEVERE, null, exception);
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
    public ObservableList<Objective> loadObjectivePending(ObservableList<Objective> objectivePending, String objectStatus, int idWorkplan){
        connect = ConnectDB.getConexion();
        Objective objective = null;
        if(connect != null){
            try{
                preStatement = connect.prepareStatement(SQL_SELECTESTATUSTARGET);
                preStatement.setString(1, objectStatus);
                preStatement.setInt(2, idWorkplan);
                ResultSet resultSet = preStatement.executeQuery();
                while(resultSet.next()){
                    objective = new Objective();
                    objective.setIdNoStaticObjective(resultSet.getInt("idObjetivo"));
                    objective.setTitle(resultSet.getString("titulo"));
                    objective.setStrategy(resultSet.getString("estrategia"));
                    objective.setResult(resultSet.getString("resultado"));
                    objective.setGoal(resultSet.getString("meta"));
                    objective.setDescription(resultSet.getString("descripcion"));
                    objective.setTargetState(resultSet.getString("estadoObjetivo"));
                    objectivePending.add(objective);
                }
                return objectivePending;
            }
            catch (SQLException exception) {
                Logger.getLogger(ObjectiveDAO.class.getName()).log(Level.SEVERE, null, exception);
            }
            finally{
                ConnectDB.close(preStatement);
                if(this.connectionTransmission == null){
                    ConnectDB.close(connect);
                }
            }
        }
        return null;
    }
    
    @Override
     public ObservableList<Objective> loadObjectiveComplet(ObservableList<Objective> objectivePending, String objectStatus, int idWorkplan){
        connect = ConnectDB.getConexion();
        Objective objective = null;
        if(connect != null){
            try{
                preStatement = connect.prepareStatement(SQL_SELECTESTATUSTARGET);
                preStatement.setString(1, objectStatus);
                preStatement.setInt(2, idWorkplan);
                ResultSet resultSet = preStatement.executeQuery();
                while(resultSet.next()){
                    objective = new Objective();
                    objective.setIdNoStaticObjective(resultSet.getInt("idObjetivo"));
                    objective.setTitle(resultSet.getString("titulo"));
                    objective.setStrategy(resultSet.getString("estrategia"));
                    objective.setResult(resultSet.getString("resultado"));
                    objective.setGoal(resultSet.getString("meta"));
                    objective.setDescription(resultSet.getString("descripcion"));
                    objective.setTargetState(resultSet.getString("estadoObjetivo"));
                    objectivePending.add(objective);
                }
                ConnectDB.close(resultSet);
                return objectivePending;
            }
            catch (SQLException exception) {
                Logger.getLogger(ObjectiveDAO.class.getName()).log(Level.SEVERE, null, exception);
            }
            finally{
                ConnectDB.close(preStatement);
                if(this.connectionTransmission == null){
                    ConnectDB.close(connect);
                }
            }
        }
        return null;
    }
     
    @Override
    public ObservableList<Objective> selectTableView(ObservableList<Objective> tableInfo, int idWorkplan){
        connect = ConnectDB.getConexion();
        Objective objective = null;
        if(connect != null){
            try{
                preStatement = connect.prepareStatement(SQL_SELECTOBJECTIVE);
                preStatement.setInt(1, idWorkplan);
                ResultSet rSet = preStatement.executeQuery();
                while(rSet.next()){
                    objective = new Objective();
                    objective.setTitle(rSet.getString("titulo"));
                    objective.setStrategy(rSet.getString("estrategia"));
                    objective.setResult(rSet.getString("resultado"));
                    objective.setGoal(rSet.getString("meta"));
                    objective.setDescription(rSet.getString("descripcion"));
                    objective.setTargetState(rSet.getString("estadoObjetivo"));   
                    tableInfo.add(objective);
                }
                ConnectDB.close(rSet);
                return tableInfo;
            }
            catch (SQLException exception) {
                Logger.getLogger(ObjectiveDAO.class.getName()).log(Level.SEVERE, null, exception);
            }
            finally{
                ConnectDB.close(preStatement);
                if(this.connectionTransmission == null){
                    ConnectDB.close(connect);
                }
            }
        }
        return null;
    }
}
