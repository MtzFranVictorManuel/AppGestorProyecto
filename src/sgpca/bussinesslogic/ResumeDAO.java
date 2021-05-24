package sgpca.uv.mx.businesslogic;

import sgpca.uv.mx.dataacces.ConnectDB;
import sgpca.uv.mx.domain.Members;
import sgpca.uv.mx.domain.Resume;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ResumeDAO implements IResume{
    private Connection connectionTransmission;
    Connection connect = null;
    PreparedStatement preStatement = null;
    private static final String SQL_INSERT  = "INSERT INTO tbl_curriculum (nombre, mision, vision, objetivoGeneral, fkIntegrante) VALUES (?, ?, ?, ?, ?);";
    private static final String SQL_SELECT = "SELECT * FROM tbl_curriculum WHERE fkIntegrante = ?;";
    private static final String SQL_UPDATE = "UPDATE tbl_curriculum SET nombre = ?, mision = ?, vision = ?, objetivoGeneral = ? WHERE fkIntegrante = ?;";
    
    public ResumeDAO() {
    }

    public ResumeDAO(Connection connectionTransmission) {
        this.connectionTransmission = connectionTransmission;
    }
    
    @Override
    public int insert(Resume resumeMember){
        connect = ConnectDB.getConexion();
        int rows = 0;
        if(connect != null){
            try{
                preStatement = connect.prepareStatement(SQL_INSERT);
                preStatement.setString(1, resumeMember.getNameResume());
                preStatement.setString(2, resumeMember.getMission());
                preStatement.setString(3, resumeMember.getVision());
                preStatement.setString(4, resumeMember.getGeneralObjetive());
                preStatement.setInt(5, resumeMember.getFkMember());
                rows = preStatement.executeUpdate();
            }
            catch(SQLException exception){
                Logger.getLogger(ResumeDAO.class.getName()).log(Level.SEVERE, null, exception);
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
    public Resume select(int idMember){
        connect = ConnectDB.getConexion();
        Resume resume = null;
        if(connect != null){
            try{
                preStatement = connect.prepareStatement(SQL_SELECT);
                preStatement.setInt(1, idMember);
                ResultSet rSet = preStatement.executeQuery();
                if(rSet.next()){
                    resume = new Resume();
                    resume.setNameResume(rSet.getString("nombre"));
                    resume.setMission(rSet.getString("mision"));
                    resume.setVision(rSet.getString("vision"));
                    resume.setGeneralObjetive(rSet.getString("objetivoGeneral"));
                    ConnectDB.close(rSet);
                    return resume;
                }
            }
            catch(SQLException exception){
                Logger.getLogger(ResumeDAO.class.getName()).log(Level.SEVERE, null, exception);
            }
            finally{
                ConnectDB.close(preStatement);
                if(this.connectionTransmission == null){
                    ConnectDB.close(connect);
                }
            }
        }
        return resume;
    }
    
    @Override
    public int update(Resume resumeMember, int idMember){
        connect = ConnectDB.getConexion();
        int rows = 0;
        if(connect != null){
            try{
                preStatement = connect.prepareStatement(SQL_UPDATE);
                preStatement.setString(1, resumeMember.getNameResume());
                preStatement.setString(2, resumeMember.getMission());
                preStatement.setString(3, resumeMember.getVision());
                preStatement.setString(4, resumeMember.getGeneralObjetive());
                preStatement.setInt(5, idMember);
                rows = preStatement.executeUpdate();
            }
            catch(SQLException exception){
                Logger.getLogger(MembersDAO.class.getName()).log(Level.SEVERE, null, exception);
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
