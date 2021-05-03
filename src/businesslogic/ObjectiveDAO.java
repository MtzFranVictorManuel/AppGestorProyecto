package businesslogic;

import domain.Objective;
import dataacces.DBconnection;
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
public class ObjectiveDAO implements IObjectiveDAO{
    private Connection connectionTransmission;
    Connection connect = DBconnection.getConexion();
    PreparedStatement preStatement = null;
    private static final String SQL_INSERT = "INSERT INTO tbl_objetivo (titulo, estrategia, resultado, meta, descripcion, fkPlanTrabajo) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String SQL_SELECT = "SELECT * FROM tbl_objetivo WHERE  fkPlanTrabajo = ?;";
    private static final String SQL_UPDATE = "UPDATE tbl_objetivo SET titulo = ?, estrategia = ?, resultado = ?, meta = ?, descripcion = ? WHERE titulo = ? AND fkPlanTrabajo  = ?;";
    private static final String SQL_DELETE = "DELETE FROM tbl_objetivo WHERE titulo = ? AND fkPlanTrabajo = ?;";

    public ObjectiveDAO() {
    }

    public ObjectiveDAO(Connection connectionTransmission) {
        this.connectionTransmission = connectionTransmission;
    }
    
    
    @Override
    public int insert(Objective objective, int idWorkplan){
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
        Objective objective = null;
        if(connect != null){
            try{
                preStatement = connect.prepareStatement(SQL_SELECT);
                preStatement.setInt(1, idWorkplan);
                ResultSet rSet = preStatement.executeQuery();
                if(rSet.next()){
                    String  title = rSet.getString("titulo");
                    String  strategy = rSet.getString("estrategia");
                    String  outcome = rSet.getString("resultado");
                    String  goal = rSet.getString("meta");
                    String  description = rSet.getString("descripcion");
                    
                    objective = new Objective();
                    objective.setTitle(title);
                    objective.setStrategy(strategy);
                    objective.setOutcome(outcome);
                    objective.setGoal(goal);
                    objective.setDescription(description);
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
    
}
