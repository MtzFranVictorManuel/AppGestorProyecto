package sgpca.uv.mx.businesslogic;

import sgpca.uv.mx.businesslogic.WorkplanDAO;
import sgpca.uv.mx.domain.Workplan;
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
public class WorkplanDAOTest {
    
    public WorkplanDAOTest() {
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
     * Test of insert method, of class WorkplanDAO.
     */
    @Test
    public void insertTest() {
        WorkplanDAO workPlantInsert = new WorkplanDAO();
        Workplan workPlan = new Workplan("facISO00111", "2021-05-01", "2022-01-25", 2);
        Assert.assertNotNull(workPlantInsert.insert(workPlan, 2));
    }
    
    /**
     * Test of select method, of class WorkplanDAO.
     */
    @Test
    public void selectTest(){
        WorkplanDAO workPlanSelect = new WorkplanDAO();
        Assert.assertNotNull(workPlanSelect.select(1));
    }
    
    /**
     * Test of  method update, of class WorkplanDAO.
     */
    @Test
    public void updateTest(){
        WorkplanDAO workPlanUpdate = new WorkplanDAO();
        Workplan workPlan = new Workplan("ISO-0014", "2020-05-01", "2021-06-30");
        Assert.assertNotNull(workPlanUpdate.update(workPlan, "facISO00111", 2));
    }
    
    /**
     * Test of  method delete, of class WorkplanDAO.
     */
    @Test
    public void deleteTest(){
        WorkplanDAO workPlanDelete = new WorkplanDAO();
        Assert.assertNotNull(workPlanDelete.delete("ISO-0014", 2));
    }
}
