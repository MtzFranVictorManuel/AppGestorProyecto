package sgpca.uv.mx.businesslogic;

import sgpca.uv.mx.domain.Members;
import sgpca.uv.mx.dataacces.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MembersDAO implements IMembers{
    private Connection connectionTransmission;
    Connection connect = null;
    PreparedStatement preStatement = null;
    private static final String SQL_INSERT = "INSERT INTO tbl_integrante (nombre, apellidos, numeroTelefono, cargos, fechaNacimiento, curp, email, contrasenia) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
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
                preStatement.setString(3, member.getPhoneNumber());
                preStatement.setString(4, member.getPosition());
                preStatement.setDate(5, member.getBirthday());
                preStatement.setString(6, member.getCurp());
                preStatement.setString(7, member.getEmail());
                preStatement.setString(8, member.getPassword());
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
                preStatement.setString(3, member.getPhoneNumber());
                preStatement.setString(4, member.getPosition());
                preStatement.setDate(5, member.getBirthday());
                preStatement.setString(6, member.getCurp());
                preStatement.setString(7, member.getEmail());
                preStatement.setString(8, member.getPassword());
                preStatement.setInt(9, idMember);
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
                ResultSet resultSet = preStatement.executeQuery();
                if(resultSet.next()){                    
                    member = new Members();
                    member.setIdMember(resultSet.getInt("idIntegrante"));
                    member.setName(resultSet.getString("nombre"));
                    member.setLastName(resultSet.getString("apellidos"));
                    member.setPhoneNumber(resultSet.getString("numeroTelefono"));
                    member.setPosition(resultSet.getString("cargos"));
                    member.setBirthday(resultSet.getDate("fechaNacimiento"));
                    member.setCurp(resultSet.getString("curp"));
                    member.setEmail(resultSet.getString("email"));
                    member.setPassword(resultSet.getString("contrasenia"));
                    ConnectDB.close(resultSet);
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
