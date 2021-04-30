/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic;

import domain.Members;
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
public class MembersDAOIT {
    
    public MembersDAOIT() {
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

    @Test
    public void testMethodInsert() {
        MembersDAO miembro = new MembersDAO();
        Members member = new Members("Miguel", "Gonzales Hernandes", "Maestro", "1990-04-04", "as3df16s5d4f", "miguel@estudio.com", "123456789");
        Assert.assertNotNull(miembro.insert(member));
    }
    
}
