/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.dao.impl;

import co.edu.udea.evdo.dao.ConnectionPool;
import co.edu.udea.evdo.dto.Comentario;
import co.edu.udea.evdo.properties.Properties;
import co.edu.udea.exception.OrgSistemasSecurityException;
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
public class ComentarioDAO extends ConnectionPool {

    static final Logger logger = Logger.getLogger(ComentarioDAO.class);

    public Collection<Comentario> getComentarios(Comentario comentario) {
        CallableStatement ps = null;
        ResultSet rs = null;
        Collection<Comentario> listaComentarios = new LinkedList<>();
        Comentario coment;
        try {
            ps = getConn().prepareCall(Properties.getInstance().getEvaluacionProperties().getString("comentario.obtener_x_asignacion"));
            ps.setInt(1, comentario.getSemestre());
            ps.setInt(2, comentario.getMateria());
            ps.setInt(3, comentario.getGrupo());
            ps.setString(4, comentario.getCedula());
            ps.registerOutParameter(5, OracleTypes.CURSOR);
            ps.executeQuery();
            rs = (ResultSet) ps.getObject(5);
            if (rs != null) {
                while (rs.next()) {
                    coment = new Comentario();
                    coment.setCodigo(rs.getInt("CODIGO"));
                    coment.setAutor(rs.getString("AUTOR"));
                    coment.setEmailAutor(rs.getString("EMAIL_AUTOR"));
                    coment.setFechaComentario(rs.getDate("FECHA_COMENTARIO"));
                    coment.setComentario(rs.getString("COMENTARIO"));
                    coment.setSemestre(rs.getInt("SEMESTRE"));
                    coment.setMateria(rs.getInt("MATERIA"));
                    coment.setGrupo(rs.getInt("GRUPO"));
                    coment.setCedula(rs.getString("CEDULA"));
                    listaComentarios.add(coment);
                }
            }
        } catch (Exception e) {
            logger.error(e);
        } finally {
            close(ps);
        }
        return listaComentarios;
    }

    public Comentario addComentario(Comentario comentario) throws OrgSistemasSecurityException {
        CallableStatement ps = null;
        ResultSet rs = null;
        Comentario coment = new Comentario();
        try {
            ps = getConn().prepareCall(Properties.getInstance().getEvaluacionProperties().getString("comentario.agregar"));
            ps.setString(1, comentario.getAutor());
            ps.setString(2, comentario.getEmailAutor());
            ps.setDate(3, comentario.getFechaComentario());
            ps.setString(4, comentario.getComentario());
            ps.setInt(5, comentario.getSemestre());
            ps.setInt(6, comentario.getMateria());
            ps.setInt(7, comentario.getGrupo());
            ps.setString(8, comentario.getCedula());
            ps.registerOutParameter(9, OracleTypes.CURSOR);
            ps.executeQuery();
            rs = (ResultSet) ps.getObject(9);
            if (rs != null) {
                rs.next();
                coment.setCodigo(rs.getInt("CODIGO"));
                coment.setAutor(rs.getString("AUTOR"));
                coment.setEmailAutor(rs.getString("EMAIL_AUTOR"));
                coment.setFechaComentario(rs.getDate("FECHA_COMENTARIO"));
                coment.setComentario(rs.getString("COMENTARIO"));
                coment.setSemestre(rs.getInt("SEMESTRE"));
                coment.setMateria(rs.getInt("MATERIA"));
                coment.setGrupo(rs.getInt("GRUPO"));
                coment.setCedula(rs.getString("CEDULA"));
            }
        } catch (Exception e) {
            logger.error(e);
            return null;
        } finally {

            close(ps);
        }
        return coment;
    }
}
