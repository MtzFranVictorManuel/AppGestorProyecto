package sgpca.bussinesslogic;

import java.sql.Date;
import junit.framework.Assert;
import org.junit.Test;
import sgpca.domain.ResearchProyect;

public class ResearchProyectDAOTest {
    
    @Test
    public void testSaveResearchProyect(){
        Date date = Date.valueOf("2020-09-06");
        ResearchProyectDAO rpDAO = new ResearchProyectDAO();
        ResearchProyect rp = new ResearchProyect("Mejoras de calidad", "Inserte descripcion", date, date);
       
        Assert.assertTrue(rpDAO.saveResearchProyect(rp));
    }
    
    @Test
    public void testModifyResearchProyect(){
        Date date = Date.valueOf("2020-09-06");
        ResearchProyectDAO rpDAO = new ResearchProyectDAO();
        ResearchProyect rp = new ResearchProyect("Nuevas Reglas", "Inserte descripcion", date, date);
       
        Assert.assertTrue(rpDAO.modifyResearchProyect(rp, 1));
    }
    
    @Test
    public void testDeleteResearchProyect(){
        ResearchProyectDAO rpDAO = new ResearchProyectDAO();
        ResearchProyect rp = new ResearchProyect("Mejoras de calidad", "", null, null);
       
        Assert.assertTrue(rpDAO.deleteResearchProyect(rp));
    }
    
    @Test
    public void testConsultRPDetails(){
        ResearchProyectDAO rpDAO = new ResearchProyectDAO();
        ResearchProyect recoveredRP;
        recoveredRP = rpDAO.consultRProyectDetails(13);
        String expectedRPName = "Terminar juego";
        String recoveredName = recoveredRP.getProyectTitle();
        
        Assert.assertEquals(expectedRPName, recoveredName);
    }
    
    @Test
    public void testConsultRProyectList(){
        ResearchProyectDAO rpDAO = new ResearchProyectDAO();
        Assert.assertNotNull(rpDAO.consultRProyectList());
    }
    
}