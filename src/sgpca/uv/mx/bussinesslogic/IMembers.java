/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgpca.uv.mx.bussinesslogic;

import sgpca.uv.mx.domain.Members;

/**
 *
 * @author azul_
 */
public interface IMembers {
    public int insert(Members member);
    
    public int update(Members member, int idMember);
    
    public Members select(String emailID, String passwordID);
    
    public Members select(int idMember);
    
    public int delete(int idMember);
}
