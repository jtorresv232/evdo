/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.dao.impl;

import co.edu.udea.evdo.dao.ConnectionPool;
import co.edu.udea.evdo.dto.Evaluacion;
import co.edu.udea.evdo.properties.Properties;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.LinkedList;
import oracle.jdbc.OracleTypes;
import org.apache.log4j.Logger;

/**
 *
 * @author Jonathan
 */
public class EvaluacionDAO extends ConnectionPool {
    
    static final Logger logger = Logger.getLogger(EvaluacionDAO.class);
    
    public Collection<Evaluacion> getEvaluaciones() {
        CallableStatement ps = null;
        ResultSet rs = null;
        Collection<Evaluacion> listaEvaluaciones = new LinkedList<>();
        Evaluacion eval;
        try {
            ps = getConn().prepareCall(Properties.getInstance().getEvaluacionProperties().getString("evaluacion.obtener"));
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    eval = new Evaluacion();
                    eval.setCodigo(rs.getString("CODIGO"));
                    eval.setSemestre(rs.getInt("SEMESTRE"));
                    eval.setPorcentaje(rs.getDouble("PORCENTAJE"));
                    eval.setPorcentajeprofesor(rs.getDouble("PORCENTAJEPROFESOR"));
                    eval.setCargado(rs.getBoolean("CARGADO"));
                    listaEvaluaciones.add(eval);
                }
            }
        } catch (Exception e) {
            logger.error(e);
        } finally {
            close(ps);
        }
        return listaEvaluaciones;
    }
    
    public Evaluacion getEvaluacion(int semestre) {
        CallableStatement ps = null;
        ResultSet rs = null;
        Evaluacion eval = new Evaluacion();
        try {
            ps = getConn().prepareCall(Properties.getInstance().getEvaluacionProperties().getString("evaluacion.get"));
            ps.setString(1, "eval" + semestre);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    eval.setCodigo(rs.getString("CODIGO"));
                    eval.setSemestre(rs.getInt("SEMESTRE"));
                    eval.setPorcentaje(rs.getDouble("PORCENTAJE"));
                    eval.setPorcentajeprofesor(rs.getDouble("PORCENTAJEPROFESOR"));
                    eval.setCargado(rs.getBoolean("CARGADO"));
                }
            }
        } catch (Exception e) {
            logger.error(e);
        } finally {
            close(ps);
        }
        return eval;
    }
    
    public Evaluacion addEvalucion(Evaluacion evaluacion) {
        CallableStatement ps = null;
        ResultSet rs = null;
        Evaluacion eval = new Evaluacion();
        try {
            ps = getConn().prepareCall(Properties.getInstance().getEvaluacionProperties().getString("evaluacion.agregar"));
            ps.setString(1, evaluacion.getCodigo());
            ps.setInt(2, evaluacion.getSemestre());
            ps.setDouble(3, evaluacion.getPorcentaje());
            ps.setBoolean(4, evaluacion.isCargado());
            ps.registerOutParameter(5, OracleTypes.CURSOR);
            ps.executeQuery();
            rs = (ResultSet) ps.getObject(5);
            if (rs != null) {
                rs.next();
                eval.setCodigo(rs.getString("CODIGO"));
                eval.setSemestre(rs.getInt("SEMESTRE"));
                eval.setPorcentaje(rs.getDouble("PORCENTAJE"));
                eval.setCargado(rs.getBoolean("CARGADO"));
                
            }
        } catch (Exception e) {
            logger.error(e);
        } finally {
            close(ps);
        }
        return eval;
    }
    
    public boolean updateEvaluacion(Evaluacion evaluacion) {
        CallableStatement ps = null;
        boolean actualizado = false;
        try {
            ps = getConn().prepareCall(Properties.getInstance().getEvaluacionProperties().getString("evaluacion.actualizar"));
            ps.setDouble(1, evaluacion.getPorcentaje());
            ps.setBoolean(2, evaluacion.isCargado());
            ps.setString(3, evaluacion.getCodigo());
            actualizado = ps.executeUpdate() > 0;
        } catch (Exception e) {
            logger.error(e);
        } finally {
            close(ps);
        }
        return actualizado;
    }
}
