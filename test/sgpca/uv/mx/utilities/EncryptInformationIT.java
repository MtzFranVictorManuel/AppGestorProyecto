/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgpca.uv.mx.utilities;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author azul_
 */
public class EncryptInformationIT {
    
    public EncryptInformationIT() {
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
     * Test of ecnode method, of class EncryptInformation.
     */
    @Test
    public void testEcnode() {
        System.out.println("ecnode");
        String secretKey = "qwerty";
        String cadena = "hola";
        EncryptInformation instance = new EncryptInformation();
        String expResult = "hola";
        String result = instance.ecnode(secretKey, cadena);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of deecnode method, of class EncryptInformation.
     */
    @Test
    public void testDeecnode() {
        System.out.println("deecnode");
        String secretKey = "";
        String cadenaEncriptada = "";
        EncryptInformation instance = new EncryptInformation();
        String expResult = "";
        String result = instance.deecnode(secretKey, cadenaEncriptada);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
