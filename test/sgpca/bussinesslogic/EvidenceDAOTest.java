package sgpca.bussinesslogic;

import java.sql.Date;
import junit.framework.Assert;
import org.junit.Test;
import sgpca.domain.Evidence;

/**
 *
 * @author Daniel
 */
public class EvidenceDAOTest {
    
    @Test
    public void testSaveEvidence() {
        Date date = Date.valueOf("2020-05-11");
        EvidenceDAO evidencedao = new EvidenceDAO();
        Evidence objEvidence = new Evidence("Inteligencia Artificial", "Pepe el toro", date, "Book");
        Assert.assertTrue(evidencedao.saveEvidence(objEvidence));
    }
    
    @Test
    public void testModifyEvidence(){
        Date date = Date.valueOf("2020-05-11");
        EvidenceDAO evidencedao = new EvidenceDAO();
        Evidence objEvidence = new Evidence("Programacion defensiva", "Jaime Tun", date, "Magazine");
        Assert.assertTrue(evidencedao.modifyEvidence(objEvidence, 23));
    }
    
    @Test
    public void testDeleteEvidence(){
        EvidenceDAO evidencedao = new EvidenceDAO();
        Evidence objEvidence = new Evidence("Inteligencia Artificial", "", null, "");
        Assert.assertTrue(evidencedao.deleteEvidence(objEvidence));
    }
    
    @Test
    public void testConsultEvidenceDetails(){
        EvidenceDAO evidencedao = new EvidenceDAO();
        Evidence recoveredEvidence;
        recoveredEvidence = evidencedao.consultEvidenceDetails(23);
        String expectedEvidenceName = "Programacion defensiva";
        String recoveredName = recoveredEvidence.getEvidenceName();
        
        Assert.assertEquals(expectedEvidenceName, recoveredName);
    }
    
    @Test
    public void testConsultEvidenceList(){
        EvidenceDAO evidencedao = new EvidenceDAO();
        Assert.assertNotNull(evidencedao.consultEvidenceList());
    }

    
}
