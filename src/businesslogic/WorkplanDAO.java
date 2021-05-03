package businesslogic;
import dataacces.DBconnection;
import domain.Workplan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;


public class WorkplanDAO implements IWorkplan{
    private Connection connectionTransmission;
    Connection connect = DBconnection.getConexion();
    PreparedStatement preStatement = null;
    private static final String SQL_INSERT = "INSERT INTO tbl_plantrabajo (titulo, fechaInicio, fechaFin, fkCuerpoAcademico) VALUES (?, ?, ?, ?);";
    private static final String SQL_SELECT = "SELECT * FROM tbl_plantrabajo WHERE fkCuerpoAcademico = ?;";
    private static final String SQL_UPDATE = "UPDATE tbl_plantrabajo SET titulo = ?, fechaInicio = ?, fechaFin = ? WHERE titulo = ? AND fkCuerpoAcademico = ?;";
    private static final String SQL_DELETE = "DELETE FROM tbl_plantrabajo WHERE titulo = ? AND fkCuerpoAcademico = ?";

    public WorkplanDAO() {
    }

    public WorkplanDAO(Connection connectionTransmission) {
        this.connectionTransmission = connectionTransmission;
    }
    
    @Override
    public int insert(Workplan workplan, int idAcademic){
        int rows = 0;
        try{
            preStatement = connect.prepareStatement(SQL_INSERT);
            preStatement.setString(1, workplan.getTitle());
            preStatement.setString(2, workplan.getStartDate());
            preStatement.setString(3, workplan.getEndDate());
            preStatement.setInt(4, idAcademic);
            rows = preStatement.executeUpdate();
        }
        catch(SQLException exception){
            Logger.getLogger(WorkplanDAO.class.getName()).log(Level.SEVERE, null, exception);
        }
        finally{
            DBconnection.close(preStatement);
            if(this.connectionTransmission == null){
                DBconnection.close(connect); 
            }
        }
        return rows;
    }
     
    @Override
    public Workplan select(int idAcademicBody){
        Workplan workPlan = null;
        if(connect != null){
            try{
                preStatement = connect.prepareStatement(SQL_SELECT);
                preStatement.setInt(1, idAcademicBody);
                ResultSet rSet = preStatement.executeQuery();
                if(rSet.next()){
                    int idWorkplan = rSet.getInt("idPlanTrabajo");
                    String title = rSet.getString("titulo");
                    String startDate = rSet.getString("fechaInicio");
                    String endDate = rSet.getString("fechaFin");
                    
                    workPlan = new Workplan();
                    workPlan.setIdWorkplan(idWorkplan);
                    workPlan.setTitle(title);
                    workPlan.setStartDate(startDate);
                    workPlan.setEndDate(endDate);
                    DBconnection.close(rSet);
                    return workPlan;
                }
            }
            catch(SQLException exception){
                Logger.getLogger(WorkplanDAO.class.getName()).log(Level.SEVERE, null, exception);
            }
            finally{
                DBconnection.close(preStatement);
                if(this.connectionTransmission == null){
                    DBconnection.close(connect);
                }
            }
        }
        return workPlan;
    }
    
    @Override
    public int update(Workplan workPlan, String titel, int idAcademicBody){
        int rows = 0;
        if(connect != null){
            try{
                preStatement = connect.prepareStatement(SQL_UPDATE);
                preStatement.setString(1, workPlan.getTitle());
                preStatement.setString(2, workPlan.getStartDate());
                preStatement.setString(3, workPlan.getEndDate());
                preStatement.setString(4, titel);
                preStatement.setInt(5, idAcademicBody);
                rows = preStatement.executeUpdate();
            }
            catch(SQLException exception){
                Logger.getLogger(WorkplanDAO.class.getName()).log(Level.SEVERE, null, exception);
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
    public int delete(String title, int idAcademicBody){
        int rows = 0;
        if(connect != null){
            try{
                preStatement = connect.prepareStatement(SQL_DELETE);
                preStatement.setString(1, title);
                preStatement.setInt(2, idAcademicBody);
                rows = preStatement.executeUpdate();
            }
            catch(SQLException exception){
                Logger.getLogger(WorkplanDAO.class.getName()).log(Level.SEVERE, null, exception);
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
