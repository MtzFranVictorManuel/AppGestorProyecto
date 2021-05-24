/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgpca.dataaccess;

import sgpca.uv.mx.dataaccess.ConnectDB;
import java.sql.Connection;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Daniel
 */
public class ConnectDBTest {
    
    
    @Test
    public void testGetDataBaseConnection() {
       Connection currentConnection = new ConnectDB().getDataBaseConnection();
       Assert.assertNotNull(currentConnection);
    }
    
    @Test
    public void testCloseDataBaseConnection(){
        Connection currentConnection = new ConnectDB().closeDataBaseConnection();
        Assert.assertNull(currentConnection);
    }
}
