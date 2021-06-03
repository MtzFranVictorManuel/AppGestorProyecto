package sgpca.uv.mx.businesslogic;

import sgpca.uv.mx.businesslogic.AcademicBodyDAO;
import sgpca.uv.mx.domain.AcademicBody;
import java.sql.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

/**
 *
 * @author azul_
 */
public class AcademicBodyDAOTest {
    
    public AcademicBodyDAOTest() {
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
     * Test of insert method, of class AcademicBodyDAO.
     */
    @Test
    public void insertTest() {
        System.out.println("insert");
        AcademicBody academic = new AcademicBody("Acs25", "Faculatas de fisica", 4, Date.valueOf("2021-05-03"), "Licenciatura", "UV", 2, 5);
        AcademicBodyDAO academicInsert = new AcademicBodyDAO();
        Assert.assertNotNull(academicInsert.insert(academic));
    }

    /**
     * Test of select method, of class AcademicBodyDAO.
     */
    @Test
    public void selectTest() {
        System.out.println("select");
        AcademicBodyDAO academicSelect = new AcademicBodyDAO();
        Assert.assertNotNull(academicSelect.select(1));
    }

    /**
     * Test of update method, of class AcademicBodyDAO.
     */
    @Test
    public void updateTest() {
        System.out.println("update");
        AcademicBody academic = new AcademicBody("Acs125", "Facultad de estadistica", 2, Date.valueOf("2021-05-02"), "Licenciatura", "UV", 3);
        AcademicBodyDAO academicUpdate = new AcademicBodyDAO();
        Assert.assertNotNull(academicUpdate.update(academic, 1));
    }
    
}
