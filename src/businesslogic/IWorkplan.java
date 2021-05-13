package businesslogic;

import domain.Workplan;

/**
 *
 * @author azul_
 */
public interface IWorkplan {
    
    public int insert(Workplan workplan, int idAcademic);
    
    public Workplan select(int idAcademicBody);
    
    public int update(Workplan workPlan, String titel, int idAcademicBody);
    
    public int delete(String titel, int idAcademicBody);
}
