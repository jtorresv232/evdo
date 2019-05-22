/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.dao.impl;

import co.edu.udea.evdo.dao.ConnectionPool;
import co.edu.udea.evdo.dto.UrlNotas;
import co.edu.udea.evdo.exceptions.DataNotFoundException;
import co.edu.udea.evdo.properties.Properties;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.LinkedList;
import oracle.jdbc.OracleTypes;
import org.apache.log4j.Logger;

/**
 *
 * @author JonathanTorresVelez
 */
public class UrlNotasDAO extends ConnectionPool{
    static final Logger logger = Logger.getLogger(UrlNotasDAO.class);
    
    public UrlNotas getDatos(long semestre, long materia, int grupo, String cedula) {
        CallableStatement ps = null;
        UrlNotas url = new UrlNotas();
        ResultSet rs = null;
        try{
            ps = getConn().prepareCall(Properties.getInstance().getEvaluacionProperties().getString("url.get"));
            ps.setLong(1, semestre);
            ps.setLong(2, materia);
            ps.setInt(3, grupo);
            ps.setString(4, cedula);
            ps.registerOutParameter(5, OracleTypes.CURSOR);
            ps.executeQuery();
            rs = (ResultSet) ps.getObject(5);
            if( rs != null && rs.next()) {
                url.setEncuesta(rs.getString("ENCUESTA"));
                url.setFechaInicio(rs.getDate("FECHA_ENC_INICIO"));
                url.setFechaTerminacion(rs.getDate("FECHA_ENC_FINAL"));
            }else {
                throw new DataNotFoundException("La asignación consultada no existe");
            }
        }catch(Exception e){
            logger.error(e);
        }finally{
            close(ps);
        }
        return url;
    }
    
    public Collection<UrlNotas> getDatosPorGrupo(long semestre, long materia, int grupo) {
        CallableStatement ps = null;
        ResultSet rs = null;
        Collection<UrlNotas> listaDatos = new LinkedList<>();
        UrlNotas dato;
        try{
            ps = getConn().prepareCall(Properties.getInstance().getEvaluacionProperties().getString("url.getByGrupo"));
            ps.setLong(1, semestre);
            ps.setLong(2, materia);
            ps.setInt(3, grupo);
            ps.registerOutParameter(4, OracleTypes.CURSOR);
            ps.executeQuery();
            rs = (ResultSet) ps.getObject(4);
            if(rs !=null) {
                while(rs.next()){
                dato = new UrlNotas();
                dato.setEncuesta(rs.getString("ENCUESTA"));
                dato.setFechaInicio(rs.getDate("FECHA_ENC_INICIO"));
                dato.setFechaTerminacion(rs.getDate("FECHA_ENC_FINAL"));
                dato.setCedula(rs.getString("CEDULA"));
                listaDatos.add(dato);
                }
            }
        }catch(Exception e){
            logger.error(e);
        }finally{
            close(ps);
        }
        return listaDatos;
    }

}
