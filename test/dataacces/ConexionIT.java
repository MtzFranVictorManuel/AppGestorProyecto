/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataacces;

import sgpca.uv.mx.dataacces.ConnectDB;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 *
 * @author azul_
 */
public class ConexionIT {
    
    
    
    public ConexionIT() {
    }
    
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }


    /**
     * Test of getConexion method, of class ConnectDB.
     */
    @Test
    public void testGetConexion() throws SQLException{
        ConnectDB connect = new ConnectDB();
        Connection conectOn = connect.getConexion();
        Assert.assertNotNull(conectOn);
    }
    
    @Test
    public void testCloseConexion() throws SQLException{
        ConnectDB connect = new ConnectDB();
        Connection conectOff = connect.getConexion();
        ConnectDB.close(conectOff);
    }
}
