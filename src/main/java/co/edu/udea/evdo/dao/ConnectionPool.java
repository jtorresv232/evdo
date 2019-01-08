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
import org.apache.log4j.Logger;

/**
 *
 * @author Jonathan
 */
public class ConnectionPool {

    private DataSource dataSource = null;
    private static ResourceBundle properties;
    private Connection con;
    static final Logger loggerCon = Logger.getLogger(ConnectionPool.class);

    public Connection getConn() {
        try {
            boolean useDataSource = true;

            if (!useDataSource) {
                con = getConne();
            } else {
                if (dataSource == null) {
                    InitialContext initContext = new InitialContext();
                    dataSource = (DataSource) initContext.lookup("java:comp/env/consultaevaluaDocen");
                }
                con = dataSource.getConnection();
                con.setAutoCommit(true);
            }

        } catch (SQLException | NamingException e) {
            loggerCon.error(e);
        } catch (Exception es) {
            loggerCon.error(es);
        }
        return con;
    }

    public Connection getConne() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String host = "sinu.udea.red";
            String port = "1521";
            String sid = "udeadev";
            String url = "jdbc:oracle:thin@" + host + ":" + port + ":" + sid;
            Properties props = new Properties();
            props.put("password", "evaluaDocen123");
            props.put("user", "evaluaDocen");
            props.put("url", url);
        } catch (Exception e) {
            loggerCon.error(e);
        }
        return con;
    }

    public static ResourceBundle getProperties() {
        return properties;
    }

    public static void setProperties(ResourceBundle properties) {
        ConnectionPool.properties = properties;
    }

    public void close(PreparedStatement ps) {
        try {
            if (!empty(con)) {
                con.close();
            }
            if (!empty(ps)) {
                ps.close();
            }

        } catch (Exception e) {
            loggerCon.error(e);
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
