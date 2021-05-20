package businesslogic;

import domain.Objective;
import dataacces.DBconnection;
import domain.Workplan;
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
    private static final String SQL_INSERT = "INSERT INTO tbl_objetivo (titulo, estrategia, resultado, meta, descripcion, fkPlanTrabajo) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String SQL_SELECT = "SELECT * FROM tbl_objetivo WHERE  fkPlanTrabajo = ?;";
    private static final String SQL_UPDATE = "UPDATE tbl_objetivo SET titulo = ?, estrategia = ?, resultado = ?, meta = ?, descripcion = ? WHERE titulo = ? AND fkPlanTrabajo  = ?;";
    private static final String SQL_DELETE = "DELETE FROM tbl_objetivo WHERE titulo = ? AND fkPlanTrabajo = ?;";
    private static final String SQL_SELECTPENDING = "select * from tbl_objetivo where estadoObjetivo = ? and fkPlanTrabajo = ?;";
    private static final String SQL_SELECTCOMPLETED = "SELECT titulo FROM tbl_objetivo WHERE estadoObjetivo = ? AND fkPlanTrabajo = ?;";


    public ObjectiveDAO() {
    }

    public ObjectiveDAO(Connection connectionTransmission) {
        this.connectionTransmission = connectionTransmission;
    }
    
    
    @Override
    public int insert(Objective objective, int idWorkplan){
        connect = DBconnection.getConexion();
        int rows = 0;
        if(connect != null){
            try{
                preStatement = connect.prepareStatement(SQL_INSERT);
                preStatement.setString(1, objective.getTitle());
                preStatement.setString(2, objective.getStrategy());
                preStatement.setString(3, objective.getOutcome());
                preStatement.setString(4, objective.getGoal());
                preStatement.setString(5, objective.getDescription());
                preStatement.setInt(6, idWorkplan);
                rows = preStatement.executeUpdate();
            }
            catch (SQLException exception) {
                Logger.getLogger(ObjectiveDAO.class.getName()).log(Level.SEVERE, null, exception);
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
    
    @Override
    public Objective select(int idWorkplan){
        connect = DBconnection.getConexion();
        Objective objective = null;
        if(connect != null){
            try{
                preStatement = connect.prepareStatement(SQL_SELECT);
                preStatement.setInt(1, idWorkplan);
                ResultSet rSet = preStatement.executeQuery();
                if(rSet.next()){
                    objective = new Objective();
                    objective.setIdObjective(rSet.getInt("idObjetivo"));
                    objective.setTitle(rSet.getString("titulo"));
                    objective.setStrategy(rSet.getString("estrategia"));
                    objective.setOutcome(rSet.getString("resultado"));
                    objective.setGoal(rSet.getString("meta"));
                    objective.setDescription(rSet.getString("descripcion"));
                    objective.setTargetState(rSet.getString("estadoObjetivo"));
                    DBconnection.close(rSet);
                    return objective;
                }
            }
            catch (SQLException exception) {
                Logger.getLogger(ObjectiveDAO.class.getName()).log(Level.SEVERE, null, exception);
            }
            finally{
                DBconnection.close(preStatement);
                if(this.connectionTransmission == null){
                    DBconnection.close(connect);
                }
            }
        }
        return objective;
    }
    
    @Override
    public int update(Objective objective, int idWorkplan, String title){
        connect = DBconnection.getConexion();
        int rows = 0;
        if(connect != null){
            try{
                preStatement = connect.prepareStatement(SQL_UPDATE);
                preStatement.setString(1, objective.getTitle());
                preStatement.setString(2, objective.getStrategy());
                preStatement.setString(3, objective.getOutcome());
                preStatement.setString(4, objective.getGoal());
                preStatement.setString(5, objective.getDescription());
                preStatement.setString(6, title);
                preStatement.setInt(7, idWorkplan);
            }
            catch (SQLException exception) {
                Logger.getLogger(ObjectiveDAO.class.getName()).log(Level.SEVERE, null, exception);
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
    
    @Override
    public int delete(int idWorkplan, String title){
        connect = DBconnection.getConexion();
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
                DBconnection.close(preStatement);
                if(this.connectionTransmission == null){
                    DBconnection.close(connect);
                }
            }
        }
        return rows;
    }
    
    public ObservableList<Objective> loadObjectivePending(ObservableList<Objective> objectivePending, String objectStatus, int idWorkplan){
        connect = DBconnection.getConexion();
        Objective objectiveObject = null;
        if(connect != null){
            try{
                preStatement = connect.prepareStatement(SQL_SELECTPENDING);
                preStatement.setString(1, objectStatus);
                preStatement.setInt(2, idWorkplan);
                ResultSet rSet = preStatement.executeQuery();
                while(rSet.next()){
                    objectiveObject = new Objective();
                    objectiveObject.setTitle(rSet.getString("titulo"));
                    objectivePending.add(objectiveObject);
                }
                return objectivePending;
            }
            catch (SQLException exception) {
                Logger.getLogger(ObjectiveDAO.class.getName()).log(Level.SEVERE, null, exception);
            }
            finally{
                DBconnection.close(preStatement);
                if(this.connectionTransmission == null){
                    DBconnection.close(connect);
                }
            }
        }
        return null;
    }
    
     public ObservableList<Objective> loadObjectiveComplet(ObservableList<Objective> objectivePending, String objectStatus, int idWorkplan){
        connect = DBconnection.getConexion();
        Objective objectiveObject = null;
        if(connect != null){
            try{
                preStatement = connect.prepareStatement(SQL_SELECTPENDING);
                preStatement.setString(1, objectStatus);
                preStatement.setInt(2, idWorkplan);
                ResultSet rSet = preStatement.executeQuery();
                while(rSet.next()){
                    objectiveObject = new Objective();
                    objectiveObject.setTitle(rSet.getString("titulo"));
                    objectivePending.add(objectiveObject);
                }
                return objectivePending;
            }
            catch (SQLException exception) {
                Logger.getLogger(ObjectiveDAO.class.getName()).log(Level.SEVERE, null, exception);
            }
            finally{
                DBconnection.close(preStatement);
                if(this.connectionTransmission == null){
                    DBconnection.close(connect);
                }
            }
        }
        return null;
    }
}
