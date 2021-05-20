package businesslogic;
import appgestorproyecto.AlertGuis;
import dataacces.DBconnection;
import domain.AcademicBody;
import domain.Workplan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;
import javafx.collections.ObservableList;


public class WorkplanDAO implements IWorkplan{
    private Connection connectionTransmission;
    AlertGuis alert = new AlertGuis();
    Connection connect = null;
    PreparedStatement preStatement = null;
    private static final String SQL_INSERT = "INSERT INTO tbl_plantrabajo (titulo, fechaInicio, fechaFin, fkCuerpoAcademico) VALUES (?, ?, ?, ?);";
    private static final String SQL_SELECT = "SELECT * FROM tbl_plantrabajo WHERE fkCuerpoAcademico = ?;";
    private static final String SQL_UPDATE = "UPDATE tbl_plantrabajo SET titulo = ?, fechaInicio = ?, fechaFin = ? WHERE titulo = ? AND fkCuerpoAcademico = ?;";
    private static final String SQL_DELETE = "DELETE FROM tbl_plantrabajo WHERE titulo = ? AND fkCuerpoAcademico = ?";
    private static final String SQL_VALIDATE = "SELECT titulo FROM tbl_plantrabajo WHERE fkCuerpoAcademico = ? AND titulo = ?";
    private static final String SQL_GETIDWORKPLAN = "select idPlanTrabajo from tbl_plantrabajo where titulo = ? and fkCuerpoAcademico = ?;";

    public WorkplanDAO() {
    }

    public WorkplanDAO(Connection connectionTransmission) {
        this.connectionTransmission = connectionTransmission;
    }
    
    @Override
    public int insert(Workplan workplan, int idAcademic){
        connect = DBconnection.getConexion();
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
        connect = DBconnection.getConexion();
        Workplan workPlan = null;
        if(connect != null){
            try{
                preStatement = connect.prepareStatement(SQL_SELECT);
                preStatement.setInt(1, idAcademicBody);
                ResultSet rSet = preStatement.executeQuery();
                if(rSet.next()){
                    workPlan = new Workplan();
                    workPlan.setIdWorkplan(rSet.getInt("idPlanTrabajo"));
                    workPlan.setTitle(rSet.getString("titulo"));
                    workPlan.setStartDate(rSet.getString("fechaInicio"));
                    workPlan.setEndDate(rSet.getString("fechaFin"));
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
        connect = DBconnection.getConexion();
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
        connect = DBconnection.getConexion();
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
    
    @Override
    public ObservableList<String> logWorkplanList(ObservableList<String> workplanList){       
        connect = DBconnection.getConexion();
        AcademicBody academic = new AcademicBody();
        if(connect != null){
            try{
                preStatement = connect.prepareStatement(SQL_SELECT);
                preStatement.setInt(1, academic.getIdAcademicBody());
                ResultSet rSet = preStatement.executeQuery();
                while(rSet.next()){
                    workplanList.add(rSet.getString("titulo"));
                }
                return workplanList;
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
        return null;
    }
    
    @Override
    public String validateExistence(int idAcademicBody, String workplanKey){
        connect = DBconnection.getConexion();
        String workPlan = null;
        if(connect != null){
            try{
                preStatement = connect.prepareStatement(SQL_VALIDATE);
                preStatement.setInt(1, idAcademicBody);
                preStatement.setString(2, workplanKey);
                ResultSet rSet = preStatement.executeQuery();
                if(rSet.next()){
                    workPlan = rSet.getString("titulo");
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
    
    public int queryWorkplanID (String workplanID){
        connect = DBconnection.getConexion();
        int idWorkplan = 0;
        if(connect != null){
            try{
                preStatement = connect.prepareStatement(SQL_GETIDWORKPLAN);
                preStatement.setString(1, workplanID);
                preStatement.setInt(2, AcademicBody.getIdAcademicBody());
                ResultSet rSet = preStatement.executeQuery();
                if(rSet.next()){
                    idWorkplan = (rSet.getInt("idPlanTrabajo"));
                    DBconnection.close(rSet);
                    return idWorkplan;
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
        return idWorkplan;
    }
    
}
