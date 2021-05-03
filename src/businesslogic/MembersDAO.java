package businesslogic;

import domain.Members;
import dataacces.DBconnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MembersDAO implements IMembers{
    private Connection connectionTransmission;
    Connection connect = DBconnection.getConexion();
    PreparedStatement preStatement = null;
    private static final String SQL_INSERT = "INSERT INTO tbl_integrante (nombre, apellidos, cargos, fechaNacimiento, curp, email, contrasenia) VALUES (?, ?, ?, ?, ?, ?, ?);";
    private static final String SQL_SELECT = "SELECT * FROM tbl_integrante WHERE nombre = ? AND idIntegrante = ?;";
    private static final String SQL_UPDATE = "UPDATE tbl_integrante SET nombre = ?, apellidos = ?, cargos = ?, fechaNacimiento = ?, curp = ?, email = ?, contrasenia = ? WHERE idIntegrante = ?;";
    private static final String SQL_DELETE = "DELETE FROM tbl_integrante WHERE idIntegrante = ?;";

    public MembersDAO(){

    }

    public MembersDAO(Connection connectionTransmission){
        this.connectionTransmission = connectionTransmission;
    }

    @Override
    public int insert(Members member){
        int rows = 0;
        if(connect != null){
            try {
                preStatement = connect.prepareStatement(SQL_INSERT);
                preStatement.setString(1, member.getName());
                preStatement.setString(2, member.getLastName());
                preStatement.setString(3, member.getPosition());
                preStatement.setDate(4, member.getBirthday());
                preStatement.setString(5, member.getCurp());
                preStatement.setString(6, member.getEmail());
                preStatement.setString(7, member.getPassword());
                rows = preStatement.executeUpdate();
            } catch (SQLException exception) {
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
    
    @Override
    public int update(Members member, int idMember){
        int rows = 0;
        if(connect != null){
            try{
                preStatement = connect.prepareStatement(SQL_UPDATE);
                preStatement.setString(1, member.getName());
                preStatement.setString(2, member.getLastName());
                preStatement.setString(3, member.getPosition());
                preStatement.setDate(4, member.getBirthday());
                preStatement.setString(5, member.getCurp());
                preStatement.setString(6, member.getEmail());
                preStatement.setString(7, member.getPassword());
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
    
    @Override
    public Members select(String nameMember, int idMember){
        Members member = null;
        if(connect != null){
            try{
                preStatement = connect.prepareStatement(SQL_SELECT);
                preStatement.setString(1, nameMember);
                preStatement.setInt(2, idMember);
                ResultSet rSet = preStatement.executeQuery();
                if(rSet.next()){
                    String name = rSet.getString("nombre");
                    String lastName = rSet.getString("apellidos");
                    String position = rSet.getString("cargos");
                    Date birthday = rSet.getDate("fechaNacimiento");
                    String curp = rSet.getString("curp");
                    String email = rSet.getString("email");
                    String password = rSet.getString("contrasenia");
                    
                    member = new Members();
                    member.setName(name);
                    member.setLastName(lastName);
                    member.setPosition(position);
                    member.setBirthday(birthday);
                    member.setCurp(curp);
                    member.setEmail(email);
                    member.setPassword(password);
                    DBconnection.close(rSet);
                    return member;
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
        return member;
    }
    
    @Override
    public int delete(int idMember){
        int rows = 0;
        try{
            preStatement = connect.prepareStatement(SQL_DELETE);
            preStatement.setInt(1, idMember);
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
        return rows;
    }
}
