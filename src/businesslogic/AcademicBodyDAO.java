package businesslogic;

import java.sql.PreparedStatement;
import dataacces.DBconnection;
import domain.AcademicBody;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



public class AcademicBodyDAO {
    private Connection connectionTransmission;
    Connection connect = DBconnection.getConexion();
    PreparedStatement preStatement = null;
    private static final String SQL_INSERT = "INSERT INTO tbl_cuerpoacademico (clave, facultadInstitucional, "
            + "numeroColaboradores, fechaRegistro, gradoColsolidacion, institucionIndependencial, numeroIntegrantes, fkIntegrante) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String SQL_SELECT = "SELECT * FROM tbl_cuerpoacademico WHERE fkIntegrante = ?;";
    private static final String SQL_UPDATE = "UPDATE tbl_cuerpoacademico SET clave = ?, facultadInstitucional = ?, "
            + "numeroColaboradores = ?, fechaRegistro = ?, gradoColsolidacion = ?, institucionIndependencial = ?, numeroIntegrantes = ? WHERE clave = ?;";


    public AcademicBodyDAO() {
    }

    public AcademicBodyDAO(Connection connectionTransmission) {
        this.connectionTransmission = connectionTransmission;
    }
    
    
    
    public int insert(AcademicBody academicBody){
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
    
    public void select(int idMemeber){
        if(connect != null){
            try{
                preStatement = connect.prepareStatement(SQL_SELECT);
                preStatement.setInt(1, idMemeber);
                ResultSet rSet = preStatement.executeQuery();
                if(rSet.next()){
                    String keyCode = rSet.getString("clave");
                    String institucionalFaculty = rSet.getString("facultadInstitucional");
                    int numberCollaborators = rSet.getInt("numeroColaboradores");
                    Date registrationDate = rSet.getDate("fechaRegistro");
                    String degreeConsolidation = rSet.getString("gradoColsolidacion");
                    String dependecyInstitution = rSet.getString("institucionIndependencial");
                    int numberParticipants = rSet.getInt("numeroIntegrantes");
                    int fkMember = rSet.getInt("fkIntegrante");
                    
                    AcademicBody academic = new AcademicBody();
                    academic.setKeyCode(keyCode);
                    academic.setInstitucionalFaculty(institucionalFaculty);
                    academic.setNumberCollaborators(numberCollaborators);
                    academic.setRegistrationDate(registrationDate);
                    academic.setDegreeConsolidation(degreeConsolidation);
                    academic.setDependecyInstitution(dependecyInstitution);
                    academic.setNumberParticipants(numberParticipants);
                    academic.setFkMember(fkMember);
                    DBconnection.close(rSet);
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
    }
    
    public int update (AcademicBody academic){
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
                preStatement.setInt(8, academic.getFkMember());
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
