package sgpca.dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConnectDB {
    protected Connection connection;
    private String url = "";
    private String user = "";
    private String password = "";
    
    public Connection getDataBaseConnection(){
        readConnectionData();
        connection = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = (Connection) DriverManager.getConnection(url, user, password);
        }catch(ClassNotFoundException exClassNotFoundException){
            System.out.println("Error: " + exClassNotFoundException);
        }catch(SQLException exSQL){
            System.out.println("Error: " + exSQL);
        }
        return connection;
    }
    
    public Connection closeDataBaseConnection(){
        try{
            if(connection != null){
                if(!connection.isClosed()){
                    connection.close();
                }
            }
        }catch(SQLException exSQL){
            System.out.println("Error: "+ exSQL);
        }
        
        return connection;
    }
    
    
    private void readConnectionData(){
        String file = "C:/Users/Daniel/Documents/NetBeansProjects/SGP-CA/prueba.txt";
        try{
         FileReader fileRead = new FileReader(file);  
         BufferedReader bufferRead = new BufferedReader(fileRead);
         url = bufferRead.readLine();
         user = bufferRead.readLine();
         password = bufferRead.readLine();
         bufferRead.close();
        }catch(FileNotFoundException exFNFE){
            System.out.println("Archivo no encontrado: " + exFNFE);
        }catch(IOException exIO){
            System.out.println("Error: " + exIO);
        }
    }
}
