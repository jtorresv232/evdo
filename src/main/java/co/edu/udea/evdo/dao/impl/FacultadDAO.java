/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.dao.impl;

import co.edu.udea.evdo.dao.ConnectionPool;
import co.edu.udea.evdo.dto.Facultad;
import co.edu.udea.evdo.properties.Properties;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.LinkedList;
import org.apache.log4j.Logger;

/**
 *
 * @author Jonathan
 */
public class FacultadDAO extends ConnectionPool {
    
    static final Logger logger = Logger.getLogger(FacultadDAO.class);
    
    public Facultad addFacultad(Facultad facultad) {
        CallableStatement ps = null;
        try {
            ps = getConn().prepareCall(Properties.getInstance().getEvaluacionProperties().getString("facultades.poblar"));
            ps.setLong(1, facultad.getCodigo());
            ps.setString(2, facultad.getNombre());
            ps.executeQuery();
        } catch (Exception e) {
            logger.error(e);
        } finally {
            close(ps);
        }
        return facultad;
    }
    
    public Collection<Facultad> getFacultades() {
        CallableStatement ps = null;
        ResultSet rs = null;
        Collection<Facultad> facultades = new LinkedList<>();
        Facultad facultad;
        try {
            ps = getConn().prepareCall(Properties.getInstance().getEvaluacionProperties().getString("facultades.obtener"));
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    facultad = new Facultad();
                    facultad.setCodigo(rs.getLong("CODIGO"));
                    facultad.setNombre(rs.getString("NOMBRE"));
                    facultad.setEmail(rs.getString("EMAIL"));
                    facultades.add(facultad);
                }
            }
        } catch (Exception e) {
            logger.error(e);
        } finally {
            close(ps);
        }
        return facultades;
    }
    
    public String updateFacultad(Facultad facultad) {
        CallableStatement ps = null;
        try {
            ps = getConn().prepareCall(Properties.getInstance().getEvaluacionProperties().getString("facultad.actualizar"));
            ps.setLong(1, facultad.getCodigo());
            ps.setString(2, facultad.getEmail());
            ps.executeQuery();
        } catch (Exception e) {
            logger.error(e);
        } finally {
            close(ps);
        }
        return "aprobado";
    }
}
