/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.dao.impl;

import co.edu.udea.evdo.dao.ConnectionPool;
import co.edu.udea.evdo.dto.Programa;
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
public class ProgramaDAO extends ConnectionPool {

    static final Logger logger = Logger.getLogger(ProgramaDAO.class);

    public Programa getNumeros(long programa) {
        CallableStatement ps = null;
        ResultSet rs = null;
        Programa prog = new Programa();
        try {
            ps = getConn().prepareCall(Properties.getInstance().getEvaluacionProperties().getString("programas.numeros"));
            ps.setLong(1, programa);
            ps.registerOutParameter(2, OracleTypes.CURSOR);
            ps.executeQuery();
            rs = (ResultSet) ps.getObject(2);
            if (rs != null) {
                rs.next();
                prog.setEncuestados(rs.getInt("ENCUESTADOS"));
                prog.setEstudiantes(rs.getInt("ESTUDIANTES"));
                prog.setNombrePrograma(rs.getString("PROGRAMA"));
            }
        } catch (Exception e) {
            logger.error(e);
        } finally {
            close(ps);
        }
        return prog;
    }

    public Collection<Programa> getProgramas() {
        CallableStatement ps = null;
        ResultSet rs = null;
        Collection<Programa> listaProgramas = new LinkedList<>();
        Programa prog;
        try {
            ps = getConn().prepareCall(Properties.getInstance().getEvaluacionProperties().getString("programas.obtener"));
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    prog = new Programa();
                    prog.setPrograma(rs.getLong("PROGRAMA"));
                    prog.setNombrePrograma(rs.getString("NOMBRE_PROGRAMA"));
                    prog.setTipoPrograma(rs.getString("TIPO_PROGRAMA"));
                    prog.setSede(rs.getString("SEDE"));
                    prog.setFacultad(rs.getLong("FACULTAD"));
                    prog.setNombreFacultad(rs.getString("NOMBRE_FACULTAD"));
                    prog.setCreditosGrado(rs.getLong("CREDITOS_GRADOS"));
                    prog.setVersionActual(rs.getLong("VERSION_ACTUAL"));
                    prog.setVersiones(rs.getString("VERSIONES"));
                    prog.setEstado(rs.getString("ESTADO"));
                    listaProgramas.add(prog);
                }
            }
        } catch (Exception e) {
            logger.error(e);
        } finally {
            close(ps);
        }
        return listaProgramas;
    }

    public Programa addPrograma(Programa programa) {
        CallableStatement ps = null;
        try {
            ps = getConn().prepareCall(Properties.getInstance().getEvaluacionProperties().getString("programas.poblar"));
            ps.setLong(1, programa.getPrograma());
            ps.setString(2, programa.getNombrePrograma());
            ps.setString(3, programa.getTipoPrograma());
            ps.setString(4, programa.getSede());
            ps.setLong(5, programa.getFacultad());
            ps.setString(6, programa.getNombreFacultad());
            ps.setLong(7, programa.getCreditosGrado());
            ps.setLong(8, programa.getVersionActual());
            ps.setString(9, programa.getVersiones());
            ps.setString(10, programa.getEstado());
            ps.executeQuery();

        } catch (Exception e) {
            logger.error(e);
        } finally {
            close(ps);
        }
        return programa;
    }
}
