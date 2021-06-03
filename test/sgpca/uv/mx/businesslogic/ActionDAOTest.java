/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgpca.uv.mx.businesslogic;

import sgpca.uv.mx.businesslogic.ActionObjectiveDAO;
import sgpca.uv.mx.domain.ActionObjective;
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
public class ActionDAOTest {
    
    public ActionDAOTest() {
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
     * Test of insert method, of class ActionObjectiveDAO.
     */
    @Test
    public void insertTest() {
        System.out.println("insert");
        ActionObjectiveDAO actionInsert = new ActionObjectiveDAO();
        ActionObjective action = new ActionObjective("Nueva accion", "Su descripcion");
        Assert.assertNotNull(actionInsert.insert(action, 1));
    }

    /**
     * Test of select method, of class ActionObjectiveDAO.
     */
    @Test
    public void selectTest() {
        System.out.println("select");
        ActionObjectiveDAO actionSelect = new ActionObjectiveDAO();
        Assert.assertNotNull(actionSelect.select(1));
    }

    /**
     * Test of update method, of class ActionObjectiveDAO.
     */
    @Test
    public void updateTest() {
        System.out.println("update");
        ActionObjectiveDAO actionUpdate = new ActionObjectiveDAO();
        ActionObjective action = new ActionObjective("Actualizado", "Actualizado");
        Assert.assertNotNull(actionUpdate.update(action, "Nueva accion", 1));
    }

    /**
     * Test of delete method, of class ActionObjectiveDAO.
     */
    @Test
    public void deleteTest() {
        System.out.println("delete");
        ActionObjectiveDAO actionDelete = new ActionObjectiveDAO();
        Assert.assertNotNull(actionDelete.delete("Actualizado", 1));
    }
    
    
}
