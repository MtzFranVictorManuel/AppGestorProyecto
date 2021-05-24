/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgpca.uv.mx.businesslogic;

import sgpca.uv.mx.domain.Objective;

/**
 *
 * @author azul_
 */
public interface IObjectiveDAO {
    public int insert(Objective objective, int idWorkplan);
    
    public Objective select(int idWorkplan);
     
    public int update(Objective objective, int idWorkplan, String title);
    
    public int delete(int idWorkplan, String title);
}
