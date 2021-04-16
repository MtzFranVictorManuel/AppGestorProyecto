
import java.sql.SQLException;
import dataacces.Conexion;
import junit.framework.Assert;
import org.junit.Test;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author azul_
 */
public class ConeccionTest {
    @Test
    public void coneccionTest() throws SQLException{
        Conexion conexiones = new Conexion();
        conexiones.getConexion();
        Assert.assertNotNull(conexiones);
    }
}
