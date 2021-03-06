/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.dao.impl;

import co.edu.udea.evdo.dao.ConnectionPool;
import co.edu.udea.evdo.dto.Grupo;
import co.edu.udea.evdo.dto.GrupoXMateria;
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
public class GrupoDAO extends ConnectionPool {

    static final Logger logger = Logger.getLogger(GrupoDAO.class);

    public Collection<Grupo> getGrupos() {
        CallableStatement ps = null;
        ResultSet rs = null;
        Collection<Grupo> listaGrupos = new LinkedList<>();
        Grupo grupo;
        try {
            ps = getConn().prepareCall(Properties.getInstance().getEvaluacionProperties().getString("grupo.obtener"));
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    grupo = new Grupo();

                    grupo.setSemestre(rs.getLong("SEMESTRE"));
                    grupo.setMateria(rs.getLong("MATERIA"));
                    grupo.setGrupo(rs.getInt("GRUPO"));
                    grupo.setNumEstudiantes(rs.getInt("NUM_ESTUDIANTES"));
                    listaGrupos.add(grupo);
                }
            }
        } catch (Exception e) {
            logger.error(e);
        } finally {
            close(ps);
        }
        return listaGrupos;
    }

    public Grupo addGrupo(Grupo grupo) {
        CallableStatement ps = null;
        try {
            ps = getConn().prepareCall(Properties.getInstance().getEvaluacionProperties().getString("grupo.agregar"));
            ps.setLong(1, grupo.getSemestre());
            ps.setLong(2, grupo.getMateria());
            ps.setInt(3, grupo.getGrupo());
            ps.setInt(4, grupo.getNumEstudiantes());
            ps.executeQuery();
        } catch (Exception e) {
            logger.error(e);
        } finally {
            close(ps);
        }
        return grupo;
    }
    
    public Collection<GrupoXMateria> getGruposXMateria(long semestre, long materia) {
        CallableStatement ps = null;
        ResultSet rs = null;
        Collection<GrupoXMateria> listaGrupos = new LinkedList<>();
        GrupoXMateria grupo;
        try {
            ps = getConn().prepareCall(Properties.getInstance().getEvaluacionProperties().getString("grupo.obtenerXmateria"));
            ps.setLong(1, semestre);
            ps.setLong(2, materia);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    grupo = new GrupoXMateria();

                    grupo.setSemestre(rs.getLong("SEMESTRE"));
                    grupo.setMateria(rs.getLong("MATERIA"));
                    grupo.setGrupo(rs.getInt("GRUPO"));
                    listaGrupos.add(grupo);
                }
            }
        } catch (Exception e) {
            logger.error(e);
        } finally {
            close(ps);
        }
        return listaGrupos;
    }

}
