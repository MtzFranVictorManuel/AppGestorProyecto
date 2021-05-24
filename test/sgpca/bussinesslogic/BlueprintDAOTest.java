package sgpca.bussinesslogic;

import sgpca.uv.mx.bussinesslogic.BlueprintDAO;
import java.sql.Date;
import org.junit.Assert;
import org.junit.Test;
import sgpca.uv.mx.domain.Blueprint;

public class BlueprintDAOTest {
    
    @Test
    public void testSaveBlueprint(){
        Date date = Date.valueOf("2020-09-06");
        BlueprintDAO blueDAO = new BlueprintDAO();
        Blueprint print = new Blueprint("Tesis chida", date, "3 meses", "Inserte descripcion");
        Assert.assertTrue(blueDAO.saveBlueprint(print));
    }
    
    @Test
    public void testModifyBlueprint(){
        Date date = Date.valueOf("2020-09-06");
        BlueprintDAO blueDAO = new BlueprintDAO();
        Blueprint print = new Blueprint("Videojuegos", date, "9 meses", "Inserte descripcion");
        Assert.assertTrue(blueDAO.modifyBlueprint(print, 16));
    }
    
    @Test
    public void testDeleteBlueprint(){
        BlueprintDAO blueDAO = new BlueprintDAO();
        Blueprint print = new Blueprint("Tesis chida", null, "", "");
        Assert.assertTrue(blueDAO.deleteBlueprint(print));
    }
    
    @Test 
    public void testConsultBlueprintDetails(){
        BlueprintDAO bpDAO = new BlueprintDAO();
        Blueprint recoveredBP;
        recoveredBP = bpDAO.consultBlueprintDetails(15);
        String expectedEvidenceName = "Harry Potter";
        String recoveredName = recoveredBP.getBlueprintTitle();
        
        Assert.assertEquals(expectedEvidenceName, recoveredName);
    }
    
    @Test
    public void testConsultBlueprintList(){
        BlueprintDAO bpDAO = new BlueprintDAO();
        Assert.assertNotNull(bpDAO.consultBlueprintList());
    }
}
