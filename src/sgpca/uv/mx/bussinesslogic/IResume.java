package sgpca.uv.mx.bussinesslogic;

import sgpca.uv.mx.domain.Resume;

/**
 *
 * @author azul_
 */
public interface IResume {
    public int insert(Resume resumeMember);
    
    public Resume select(int idMember);
    
    public int update(Resume resumeMember, int idMember); 
}
