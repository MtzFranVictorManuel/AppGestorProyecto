/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgpca.uv.mx.businesslogic;

import sgpca.uv.mx.domain.ActionObjective;

/**
 *
 * @author azul_
 */
public interface IActionDAO {
    
    public int insert(ActionObjective action, int idObjective);
    
    public ActionObjective select(int idObjective);
    
    public int update(ActionObjective action, String titulo, int idObjective);
    
    public int delete(String title, int idAccion);
}
