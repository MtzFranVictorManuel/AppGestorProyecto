package businesslogic;

import sgpca.uv.mx.businesslogic.MembersDAO;
import sgpca.uv.mx.domain.Members;
import java.sql.Date;
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
public class MembersDAOtest {
    
    public MembersDAOtest() {
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
        MembersDAO memberInsert = new MembersDAO();
        Members member = new Members("Miguel", "Gonzales Hernandes", "Maestro", Date.valueOf("1995-05-15"), "as3df16s5d4f", "miguel@estudio.com", "123456789");
        Assert.assertNotNull(memberInsert.insert(member));
    }
    
    @Test
    public void testMethodSelect(){
        MembersDAO memberSelect = new MembersDAO();
        Assert.assertNotNull(memberSelect.select("zs18019639@estudiantes.uv.mx", "556699"));
    }
    
    @Test
    public void testMethodUpdate(){
        MembersDAO memberUpdate = new MembersDAO();
        Members members = new Members("Eduardo", "Gonzales", "Doctorado", Date.valueOf("1985-05-25"), "sad23f1", "Gonzales@Estudiante.com", "101010");
        Assert.assertNotNull(memberUpdate.update(members, 3));
    }
    
    @Test
    public void testMethodDelete(){
        MembersDAO memberDelete = new MembersDAO();
        Assert.assertNotNull(memberDelete.delete(4));
    }
}
