package sgpca.uv.mx.bussinesslogic;

import sgpca.uv.mx.domain.Members;
import sgpca.uv.mx.dataacces.ConnectDB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MembersDAO implements IMembers{
    private Connection connectionTransmission;
    Connection connect = null;
    PreparedStatement preStatement = null;
    private static final String SQL_INSERT = "INSERT INTO tbl_integrante (nombre, apellidos, cargos, fechaNacimiento, curp, email, contrasenia) VALUES (?, ?, ?, ?, ?, ?, ?);";
    private static final String SQL_SELECT = "SELECT * FROM tbl_integrante WHERE email = ? AND contrasenia = ?;";
    private static final String SQL_SELECTMEMBER = "SELECT * FROM tbl_integrante WHERE idIntegrante = ?;";
    private static final String SQL_UPDATE = "UPDATE tbl_integrante SET nombre = ?, apellidos = ?, cargos = ?, fechaNacimiento = ?, curp = ?, email = ?, contrasenia = ? WHERE idIntegrante = ?;";
    private static final String SQL_DELETE = "DELETE FROM tbl_integrante WHERE idIntegrante = ?;";

    public MembersDAO(){

    }

    public MembersDAO(Connection connectionTransmission){
        this.connectionTransmission = connectionTransmission;
    }

    @Override
    public int insert(Members member){
        connect = ConnectDB.getConexion();
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
                ConnectDB.close(preStatement);
                if(this.connectionTransmission == null){
                    ConnectDB.close(connect);
                }
            }
        }
        return rows;
    }
    
    @Override
    public int update(Members member, int idMember){
        connect = ConnectDB.getConexion();
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
                ConnectDB.close(preStatement);
                if(this.connectionTransmission == null){
                    ConnectDB.close(connect);
                }
            }
        }
        return rows;
    }
    
    @Override
    public Members select(String emailID, String passwordID){
        connect = ConnectDB.getConexion();
        Members member = null;
        if(connect != null){
            try{
                preStatement = connect.prepareStatement(SQL_SELECT);
                preStatement.setString(1, emailID);
                preStatement.setString(2, passwordID);
                ResultSet rSet = preStatement.executeQuery();
                if(rSet.next()){                    
                    member = new Members();
                    member.setIdMember(rSet.getInt("idIntegrante"));
                    member.setName(rSet.getString("nombre"));
                    member.setLastName(rSet.getString("apellidos"));
                    member.setPosition(rSet.getString("cargos"));
                    member.setBirthday(rSet.getDate("fechaNacimiento"));
                    member.setCurp(rSet.getString("curp"));
                    member.setEmail(rSet.getString("email"));
                    member.setPassword(rSet.getString("contrasenia"));
                    ConnectDB.close(rSet);
                    return member;
                }
            }
            catch(SQLException exception){
                Logger.getLogger(MembersDAO.class.getName()).log(Level.SEVERE, null, exception);
                return member;
            }
            finally{
                ConnectDB.close(preStatement);
                if(this.connectionTransmission == null){
                    ConnectDB.close(connect);
                }
            }
        }
        return member;
    }
    
    @Override
    public Members select(int idMember){
        connect = ConnectDB.getConexion();
        Members member = null;
        if(connect != null){
            try{
                preStatement = connect.prepareStatement(SQL_SELECTMEMBER);
                preStatement.setInt(1, idMember);
                ResultSet rSet = preStatement.executeQuery();
                if(rSet.next()){
                    member = new Members();
                    member.setName(rSet.getString("nombre"));
                    member.setLastName(rSet.getString("apellidos"));
                    member.setPosition(rSet.getString("cargos"));
                    member.setBirthday(rSet.getDate("fechaNacimiento"));
                    member.setCurp(rSet.getString("curp"));
                    member.setEmail(rSet.getString("email"));
                    member.setPassword(rSet.getString("contrasenia"));
                    ConnectDB.close(rSet);
                    return member;
                }
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
        return member;
    }
    
    @Override
    public int delete(int idMember){
        connect = ConnectDB.getConexion();
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
        ConnectDB.close(preStatement);
            if(this.connectionTransmission == null){
                ConnectDB.close(connect);
            } 
        }
        return rows;
    }
}
