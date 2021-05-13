/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic;

import domain.Action;
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
public class ActionDAOtest {
    
    public ActionDAOtest() {
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
     * Test of insert method, of class ActionDAO.
     */
    @Test
    public void testMethodInsert() {
        System.out.println("insert");
        ActionDAO actionInsert = new ActionDAO();
        Action action = new Action("Nueva accion", "Su descripcion");
        Assert.assertNotNull(actionInsert.insert(action, 1));
    }

    /**
     * Test of select method, of class ActionDAO.
     */
    @Test
    public void testMethodSelect() {
        System.out.println("select");
        ActionDAO actionSelect = new ActionDAO();
        Assert.assertNotNull(actionSelect.select(1));
    }

    /**
     * Test of update method, of class ActionDAO.
     */
    @Test
    public void testMethodUpdate() {
        System.out.println("update");
        ActionDAO actionUpdate = new ActionDAO();
        Action action = new Action("Actualizado", "Actualizado");
        Assert.assertNotNull(actionUpdate.update(action, "Nueva accion", 1));
    }

    /**
     * Test of delete method, of class ActionDAO.
     */
    @Test
    public void testMethodDelete() {
        System.out.println("delete");
        ActionDAO actionDelete = new ActionDAO();
        Assert.assertNotNull(actionDelete.delete("Actualizado", 1));
    }
    
}
