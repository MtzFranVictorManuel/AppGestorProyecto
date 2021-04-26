package businesslogic;

import domain.Members;
import dataacces.DBconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MembersDAO {
    private Connection connectionTrasmission;
    private static final String SQL_INSERT = "INSERT INTO tbl_integrante (nombre, apellidos, cargo, fechaNacimiento, curp, email, password) VALUES (?, ?, ?, ?, ?, ?, ?);";
    private static final String SQL_SELECTMEMBER = "SELECT * FROM tbl_integrante;";

    public MembersDAO(){

    }

    public MembersDAO(Connection connectionTrasmission){
        this.connectionTrasmission = connectionTrasmission;
    }

    public int insert(Members member){
        Connection connect = DBconnection.getConexion();
        PreparedStatement preStatement = null;
        int rows = 0;
        try {
            preStatement = connect.prepareStatement(SQL_INSERT);
            preStatement.setString(1, member.getName());
            preStatement.setString(2, member.getLastName());
            preStatement.setString(3, member.getPosition());
            preStatement.setString(4, member.getBirthday());
            preStatement.setString(5, member.getCurp());
            preStatement.setString(6, member.getEmail());
            preStatement.setString(7, member.getPassword());
            rows = preStatement.executeUpdate();
        } catch (SQLException exception) {
            Logger.getLogger(MembersDAO.class.getName()).log(Level.SEVERE, null, exception);
        }finally{
            DBconnection.close(preStatement);
            if(this.connectionTrasmission == null){
                DBconnection.close(connect);
            }
        }
        return rows;
    }
}
