/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.util;

import co.edu.udea.evdo.dao.ConnectionPool;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

/**
 *
 * @author JonathanTorresVelez
 */
public class testConnection extends ConnectionPool{
    private static testConnection singletonInstance = new testConnection();
    
    static final Logger logger = Logger.getLogger(testConnection.class);
    
    public String test() {
        CallableStatement ps = null;
        ResultSet rs = null;
        String status = "STATUS: ";
        try {
            ps = getConn().prepareCall("SELECT monitoreodb.mon_status_conexion status FROM dual");
            rs = ps.executeQuery();
            if (rs != null) {
                rs.next();
                status = status + rs.getString("STATUS");
            }
        } catch(Exception e) {
            logger.error(e);
        }
        return status;
    }
    
    public static String getStatus() {
       return getInstance().test();
    }
    
    public static testConnection getInstance() {
        synchronized (testConnection.class) {
            if (singletonInstance == null) {
                singletonInstance = new testConnection();
            }
        }
        return singletonInstance;
    }
    
}
