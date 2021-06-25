package sgpca.uv.mx.businesslogic;

import sgpca.uv.mx.domain.Resume;

/**
 *
 * @author azul_
 */
public interface IResume {
    public int insert(Resume resumeMember, int idMember);
    
    public Resume select(int idMember);
    
    public int update(Resume resumeMember, int idMember); 
}
