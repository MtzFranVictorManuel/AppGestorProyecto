package sgpca.uv.mx.dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author azul_
 */
public class DBconnection {
    private static final String JDBC_USER="root";
    private static final String JDBC_DATABASE="pruebasgp";
    private static final String JDBC_PASSWORD="Q6spEd1=pHA";
    private static final String JDBC_URL = "jdbc:mysql://localhost/pruebasgp?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    public static Connection getConexion(){
        Connection connect = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        }
        catch(ClassNotFoundException excep){
            System.out.println("Error de conexion " + excep.getMessage());
        }
        catch(SQLException excep){
            System.out.println("Error de conexion " + excep.getMessage());
        }
        return connect;
    }

    public static void close(ResultSet rSet){
        try{
            if(rSet != null){
                rSet.close();
            }
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }

    public static void close (PreparedStatement pStatement){
        try{
            if(pStatement != null){
                pStatement.close();
            }
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }

    public static void close(Connection connect){
        try{
            if(connect != null){
                connect.close();
            }
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
}
