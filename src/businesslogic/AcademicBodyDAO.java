package businesslogic;

import java.sql.PreparedStatement;
import dataacces.DBconnection;
import domain.AcademicBody;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



public class AcademicBodyDAO {
    private Connection connectionTransmiccion;
    private static final String SQL_INSERT = "INSERT INTO tbl_cuerpoacademico (clave, facultadInstitucional, "
            + "numeroColaboradores, fechaRegistro, gradoColsolidacion, institucionIndependencial, numeroIntegrantes, fkIntegrante) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

    public AcademicBodyDAO() {
    }

    public AcademicBodyDAO(Connection connectionTransmiccion) {
        this.connectionTransmiccion = connectionTransmiccion;
    }
    
    
    
    public int insert(AcademicBody academicBody){
        Connection connect = DBconnection.getConexion();
        PreparedStatement preStatement = null;
        int rows = 0;
        try{
            preStatement = connect.prepareStatement(SQL_INSERT);
            preStatement.setString(1, academicBody.getKeyCode());
            preStatement.setString(2, academicBody.getInstitucionalFaculty());
            preStatement.setInt(3, academicBody.getNumberCollaborators());
            preStatement.setString(4, academicBody.getRegistrationDate());
            preStatement.setString(5, academicBody.getDegreeConsolidation());
            preStatement.setString(6, academicBody.getDependecyInstitution());
            preStatement.setInt(7, academicBody.getNumberParticiplants());
            preStatement.setInt(8, academicBody.getFkMember());
        }catch(SQLException exception){
            Logger.getLogger(AcademicBodyDAO.class.getName()).log(Level.SEVERE, null, exception);
        }finally{
            DBconnection.close(preStatement);
            if(this.connectionTransmiccion == null){
                DBconnection.close(connect);
            }
        }
        
        return rows;
    }
}
