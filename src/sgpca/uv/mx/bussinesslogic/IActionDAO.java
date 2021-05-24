/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgpca.uv.mx.bussinesslogic;

import sgpca.uv.mx.domain.Action;

/**
 *
 * @author azul_
 */
public interface IActionDAO {
    public int insert(Action action, int idObjective);
    
    public Action select(int idObjective);
    
    public int update(Action action, String titulo, int idObjective);
    
    public int delete(String title, int idAccion);
}
