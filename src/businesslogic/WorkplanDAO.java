package businesslogic;
import dataacces.DBconnection;
import domain.Workplan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;


public class WorkplanDAO {
    private Connection connectionTransmission;
    private static final String SQL_INSERT = "INSERT INTO tbl_plantrabajo (titulo, fechaInicio, fechaFin) VALUES (?, ?, ?);";

    public WorkplanDAO() {
    }

    public WorkplanDAO(Connection connectionTransmission) {
        this.connectionTransmission = connectionTransmission;
    }
    
    public int insert(Workplan workplan){
        Connection connect = DBconnection.getConexion();
        PreparedStatement preStatement = null;
        int rows = 0;
        try{
            preStatement = connect.prepareStatement(SQL_INSERT);
            preStatement.setString(1, workplan.getIdWorkplan());
            preStatement.setString(2, workplan.getStartDate());
            preStatement.setString(3, workplan.getEndDate());
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
    
}
