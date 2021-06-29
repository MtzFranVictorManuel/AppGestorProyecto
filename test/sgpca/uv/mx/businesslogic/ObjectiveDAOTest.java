package sgpca.uv.mx.businesslogic;

import sgpca.uv.mx.businesslogic.ObjectiveDAO;
import sgpca.uv.mx.domain.Objective;
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
public class ObjectiveDAOTest {
    
    public ObjectiveDAOTest() {
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
    public void insertTest() {
        System.out.println("insert");
        ObjectiveDAO objectiveInsert = new ObjectiveDAO();
        //Objective objective = new Objective("Presentar Ia", "Ser implementada en la facultad", "buenos", "Satisfacer la necesidad", "Es una prueba");
        //Assert.assertNotNull(objectiveInsert.insert(objective, 1));
    }

    /**
     * Test of select method, of class ObjectiveDAO.
     */
    @Test
    public void selectTest() {
        System.out.println("select");
        ObjectiveDAO objectiveSelect = new ObjectiveDAO();
        Assert.assertNotNull(objectiveSelect.select(1));
    }

    /**
     * Test of update method, of class ObjectiveDAO.
     */
    @Test
    public void updateTest() {
        System.out.println("update");
        ObjectiveDAO objectiveUpdate = new ObjectiveDAO();
        Objective objective = new Objective("unesco", "el implentar la limpieza", "", "Es crear una aplicacion que nos permita limpiar", "Es un pequeño proyecto que pide que limpies", "Pendiente");
        Assert.assertNotNull(objectiveUpdate.update(objective, 2, "unesco"));
    }

    /**
     * Test of delete method, of class ObjectiveDAO.
     */
    @Test
    public void deleteTest() {
        System.out.println("delete");
        ObjectiveDAO objectiveDelete = new ObjectiveDAO();
        Assert.assertNotNull(objectiveDelete.delete(1, "Fue modificado"));
    }
    
    @Test
    public void getIdObjective(){
        ObjectiveDAO objetiveGetID = new ObjectiveDAO();
        Objective.setIdObjective(objetiveGetID.selectIdObject("Videojuegos", 1));
        System.out.println(Objective.getIdObjective());
        Assert.assertNotNull(Objective.getIdObjective());
    }
    
}
