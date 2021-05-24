package sgpca.bussinesslogic;

import java.util.List;
import sgpca.domain.Evidence;

public interface IEvidenceDAO {
    public boolean saveEvidence(Evidence evidence);
    public boolean modifyEvidence(Evidence evidence, int idEvidence);
    public boolean deleteEvidence(Evidence evidence);
    public Evidence consultEvidenceDetails(int idEvidence);
    public List<Evidence> consultEvidenceList();
    public List<Evidence> searchEvidenceByName(String evidenceName);
}
