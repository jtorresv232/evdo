/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.dao.impl;

import co.edu.udea.evdo.dao.ConnectionPool;
import co.edu.udea.evdo.dto.Encuesta;
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
public class EncuestaDAO extends ConnectionPool {

    static final Logger logger = Logger.getLogger(EncuestaDAO.class);

    public Collection<Encuesta> getEncuestas() {
        CallableStatement ps = null;
        ResultSet rs = null;
        Collection<Encuesta> listaEncuestas = new LinkedList<>();
        Encuesta encuesta;
        try {
            ps = getConn().prepareCall(Properties.getInstance().getEvaluacionProperties().getString("encuesta.obtener"));
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    encuesta = new Encuesta();
                    encuesta.setIdentificacion(rs.getString("IDENTIFICACION"));
                    encuesta.setNombre(rs.getString("NOMBRE"));
                    encuesta.setFechaInicio(rs.getDate("FECHAINICIO"));
                    encuesta.setFechaTerminacion(rs.getDate("FECHATERMINACION"));
                    encuesta.setEvaluacion(rs.getString("EVALUACION"));
                    listaEncuestas.add(encuesta);
                }
            }
        } catch (Exception e) {
            logger.error(e);
        } finally {
            close(ps);
        }
        return listaEncuestas;
    }

    public Encuesta addEncuesta(Encuesta enc) {
        CallableStatement ps = null;
        try {
            ps = getConn().prepareCall(Properties.getInstance().getEvaluacionProperties().getString("encuesta.agregar"));
            ps.setString(1, enc.getIdentificacion());
            ps.setString(2, enc.getNombre());
            ps.setDate(3, enc.getFechaInicio());
            ps.setDate(4, enc.getFechaTerminacion());
            ps.setString(5, enc.getEvaluacion());
            ps.registerOutParameter(6, OracleTypes.CURSOR);
            ps.executeQuery();
        } catch (Exception e) {
            logger.error(e);
        } finally {
            close(ps);
        }
        return enc;
    }
}
