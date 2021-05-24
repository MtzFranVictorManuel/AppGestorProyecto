package sgpca.uv.mx.businesslogic;

import java.sql.PreparedStatement;
import sgpca.uv.mx.dataacces.DBconnection;
import sgpca.uv.mx.domain.AcademicBody;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



public class AcademicBodyDAO implements IAcademicBody{
    private Connection connectionTransmission;
    Connection connect = null;
    PreparedStatement preStatement = null;
    private static final String SQL_INSERT = "INSERT INTO tbl_cuerpoacademico (clave, facultadInstitucional, "
            + "numeroColaboradores, fechaRegistro, gradoColsolidacion, institucionIndependencial, numeroIntegrantes, fkIntegrante) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String SQL_SELECT = "SELECT * FROM tbl_cuerpoacademico WHERE fkIntegrante = ?;";
    private static final String SQL_UPDATE = "UPDATE tbl_cuerpoacademico SET clave = ?, facultadInstitucional = ?, "
            + "numeroColaboradores = ?, fechaRegistro = ?, gradoColsolidacion = ?, institucionIndependencial = ?, numeroIntegrantes = ? WHERE fkIntegrante = ?;";
        


    public AcademicBodyDAO() {
    }

    public AcademicBodyDAO(Connection connectionTransmission) {
        this.connectionTransmission = connectionTransmission;
    }
    
    
    
    @Override
    public int insert(AcademicBody academicBody){
        connect = DBconnection.getConexion();
        int rows = 0;
        try{
            preStatement = connect.prepareStatement(SQL_INSERT);
            preStatement.setString(1, academicBody.getKeyCode());
            preStatement.setString(2, academicBody.getInstitucionalFaculty());
            preStatement.setInt(3, academicBody.getNumberCollaborators());
            preStatement.setDate(4, academicBody.getRegistrationDate());
            preStatement.setString(5, academicBody.getDegreeConsolidation());
            preStatement.setString(6, academicBody.getDependecyInstitution());
            preStatement.setInt(7, academicBody.getNumberParticipants());
            preStatement.setInt(8, academicBody.getFkMember());
        }catch(SQLException exception){
            Logger.getLogger(AcademicBodyDAO.class.getName()).log(Level.SEVERE, null, exception);
        }finally{
            DBconnection.close(preStatement);
            if(this.connectionTransmission == null){
                DBconnection.close(connect);
            }
        }
        
        return rows;
    }
    
    @Override
    public AcademicBody select(int idMemeber){
        connect = DBconnection.getConexion();
        AcademicBody academic = null;
        if(connect != null){
            try{
                preStatement = connect.prepareStatement(SQL_SELECT);
                preStatement.setInt(1, idMemeber);
                ResultSet rSet = preStatement.executeQuery();
                if(rSet.next()){
                    academic = new AcademicBody();
                    academic.setIdAcademicBody(rSet.getInt("idCuerpoAcademico"));
                    academic.setKeyCode(rSet.getString("clave"));
                    academic.setInstitucionalFaculty(rSet.getString("facultadInstitucional"));
                    academic.setNumberCollaborators(rSet.getInt("numeroColaboradores"));
                    academic.setRegistrationDate(rSet.getDate("fechaRegistro"));
                    academic.setDegreeConsolidation(rSet.getString("gradoColsolidacion"));
                    academic.setDependecyInstitution(rSet.getString("institucionIndependencial"));
                    academic.setNumberParticipants(rSet.getInt("numeroIntegrantes"));
                    academic.setFkMember(rSet.getInt("fkIntegrante"));
                    DBconnection.close(rSet);
                    return academic;
                }
            }
            catch(SQLException exception){
                Logger.getLogger(MembersDAO.class.getName()).log(Level.SEVERE, null, exception);
            }
            finally{
                DBconnection.close(preStatement);
                if(this.connectionTransmission == null){
                    DBconnection.close(connect);
                }
            }
        }
        return academic;
    }
    
    @Override
    public int update (AcademicBody academic, int idMember){
        connect = DBconnection.getConexion();
        int rows = 0;
        if(connect != null){
            try{
                preStatement = connect.prepareStatement(SQL_UPDATE);
                preStatement.setString(1, academic.getKeyCode());
                preStatement.setString(2, academic.getInstitucionalFaculty());
                preStatement.setInt(3, academic.getNumberCollaborators());
                preStatement.setDate(4, academic.getRegistrationDate());
                preStatement.setString(5, academic.getDegreeConsolidation());
                preStatement.setString(6, academic.getDependecyInstitution());
                preStatement.setInt(7, academic.getNumberParticipants());
                preStatement.setInt(8, idMember);
                rows = preStatement.executeUpdate();
            }
            catch(SQLException exception){
                Logger.getLogger(MembersDAO.class.getName()).log(Level.SEVERE, null, exception);
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
