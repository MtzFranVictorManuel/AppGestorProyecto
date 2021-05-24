package sgpca.uv.mx.businesslogic;

import sgpca.uv.mx.domain.Workplan;
import javafx.collections.ObservableList;

/**
 *
 * @author azul_
 */
public interface IWorkplan {
    
    public int insert(Workplan workplan, int idAcademic);
    
    public Workplan select(int idAcademicBody);
    
    public int update(Workplan workPlan, String titel, int idAcademicBody);
    
    public int delete(String titel, int idAcademicBody);
    
    public ObservableList<String> logWorkplanList(ObservableList<String> workplanList);
    
    public String validateExistence(int idAcademicBody, String workplanKey);
}
