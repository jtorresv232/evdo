/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.dao;

import java.sql.*;
import java.util.Properties;
import java.util.ResourceBundle;
import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Jonathan
 */
public class ConnectionPool {
    
    private static DataSource dataSource = null;
    private static ResourceBundle properties;
    private Connection con;
    
    public Connection getConn(){
        try{
            boolean useDataSource = true;
            
            if (!useDataSource) {
                con = getConn(true);
            } else {
                if (dataSource == null) {
                    InitialContext initContext = new InitialContext();
                    dataSource = (DataSource) initContext.lookup("java:comp/env/consultaevaluaDocen");
                }
                con = dataSource.getConnection();
                con.setAutoCommit(true);
            }
            
        }catch (SQLException | NamingException e){
            
        } catch (Exception es) {
            
        }
        return con;
    }
    
    public Connection getConn(boolean state) throws SQLException, Exception{
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String host = "sinu.udea.red";
            String port = "1521";
            String sid = "udeadev";
            String url = "jdbc:oracle:thin@" + host + ":" + port + ":" + sid;
            Properties props = new Properties();
            props.put("password", "evaluaDocen123");
            props.put("user", "evaluaDocen");
        }catch(Exception e){
            throw new Exception(e);
        }finally {
            return con;
        }
    }

    public static ResourceBundle getProperties() {
        return properties;
    }

    public static void setProperties(ResourceBundle properties) {
        ConnectionPool.properties = properties;
    }
    
    public void close(PreparedStatement ps, ResultSet rs){
        try {
            if (!empty(con)) {
                con.close();
            }
            if (!empty(ps)) {
                ps.close();
            }

        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    public boolean empty(Object objeto) {
        if (objeto == null) {
            return true;
        }
        if (objeto instanceof String) {
            return objeto.toString().trim().equals("");
        }
        return false;
    }
}

