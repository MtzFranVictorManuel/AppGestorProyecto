package sgpca.uv.mx.bussinesslogic;

import java.util.List;
import sgpca.uv.mx.domain.ResearchProyect;

public interface IResearchProyectDAO {
    public boolean saveResearchProyect(ResearchProyect researchProyect);
    public boolean modifyResearchProyect(ResearchProyect researchProyect, int idRProyect);
    public boolean deleteResearchProyect(ResearchProyect researchProyect);
    public ResearchProyect consultRProyectDetails(int idRProyect);
    public List<ResearchProyect> consultRProyectList();
    public List<ResearchProyect> searchRPByName(String name);
}
