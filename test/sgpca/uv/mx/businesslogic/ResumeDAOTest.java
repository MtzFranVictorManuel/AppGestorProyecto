package sgpca.uv.mx.businesslogic;

import sgpca.uv.mx.businesslogic.ResumeDAO;
import sgpca.uv.mx.domain.Resume;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author 
 */
public class ResumeDAOTest {
    
    public ResumeDAOTest() {
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
     * Test of insert method, of class ResumeDAO.
     */
    @Test
    public void insertTest() {
        System.out.println("insert");
        ResumeDAO resume = new ResumeDAO();
        Resume resumeMember = new Resume("Hola nuevo", "Es crear una prueba", "Y que esta sea ejecurada", "Hola", 1);
        Assert.assertNotNull(resume.insert(resumeMember));
    }

    /**
     * Test of select method, of class ResumeDAO.
     */
    @Test
    public void selectTest() {
        System.out.println("select");
        ResumeDAO resume = new ResumeDAO();
        resume.select(1);
        Assert.assertNotNull(resume);
    }

    /**
     * Test of update method, of class ResumeDAO.
     */
    @Test
    public void updateTest() {
        System.out.println("update");
        ResumeDAO resume = new ResumeDAO();
        Resume resumeMember = new Resume("lo cambie", "Es cambiar a nuevo", "Y ver si funciona", "", 1);
        
    }
    
}
