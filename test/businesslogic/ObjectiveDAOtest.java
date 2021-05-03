package businesslogic;

import domain.Objective;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author azul_
 */
public class ObjectiveDAOtest {
    
    public ObjectiveDAOtest() {
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
     * Test of insert method, of class ObjectiveDAO.
     */
    @Test
    public void testMethodInsert() {
        System.out.println("insert");
        ObjectiveDAO objectiveInsert = new ObjectiveDAO();
        Objective objective = new Objective("Presentar Ia", "Ser implementada en la facultad", "buenos", "Satisfacer la necesidad", "Es una prueba");
        Assert.assertNotNull(objectiveInsert.insert(objective, 1));
    }

    /**
     * Test of select method, of class ObjectiveDAO.
     */
    @Test
    public void testMethodSelect() {
        System.out.println("select");
        ObjectiveDAO objectiveSelect = new ObjectiveDAO();
        Assert.assertNotNull(objectiveSelect.select(1));
    }

    /**
     * Test of update method, of class ObjectiveDAO.
     */
    @Test
    public void testMethodUpdate() {
        System.out.println("update");
        ObjectiveDAO objectiveUpdate = new ObjectiveDAO();
        Objective objective = new Objective("Fue modificado", "Fue modificado", "Fue modificado", "Fue modificado", "Fue modificado");
        Assert.assertNotNull(objectiveUpdate.update(objective, 1, "Presentar Ia"));
    }

    /**
     * Test of delete method, of class ObjectiveDAO.
     */
    @Test
    public void testMethodDelete() {
        System.out.println("delete");
        ObjectiveDAO objectiveDelete = new ObjectiveDAO();
        Assert.assertNotNull(objectiveDelete.delete(1, "Fue modificado"));
    }
    
}
