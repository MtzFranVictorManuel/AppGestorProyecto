/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgpca.uv.mx.businesslogic;

import sgpca.uv.mx.domain.AcademicBody;

/**
 *
 * @author azul_
 */
public interface IAcademicBody {
    
    public int insert(AcademicBody academicBody);
    
    public AcademicBody select(int idMemeber);
    
    public int update (AcademicBody academic, int idMember);
    
    
}
