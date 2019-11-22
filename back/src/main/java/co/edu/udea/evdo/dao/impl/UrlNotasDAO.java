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
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ArrayDescriptor;
import org.apache.log4j.Logger;
import oracle.sql.ARRAY;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang.ArrayUtils;

/**
 *
 * @author JonathanTorresVelez
 */
public class UrlNotasDAO extends ConnectionPool{
    static final Logger logger = Logger.getLogger(UrlNotasDAO.class);
    OracleConnection conn;
    
    
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
                url.setPunto(rs.getInt("PUNTO"));
                url.setMetadato(rs.getString("METADATO"));
            }else {
                throw new DataNotFoundException("La asignación getConn()sultada no existe");
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
                dato.setPunto(rs.getInt("PUNTO"));
                dato.setMetadato(rs.getString("METADATO"));
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
    
    public Collection<UrlNotas> getTodos(String semestres, String materias, String grupos){
        CallableStatement ps = null;
        ResultSet rs = null;
        
        Collection<UrlNotas> listaDatos = new LinkedList<>();
        UrlNotas dato;
        try {
//            Class.forName("oracle.jdbc.OracleDriver");			
//			Connection getConn() = DriverManager.getConnection("jdbc:oracle:thin:@172.19.0.8:1521:udeadev","evaluaDocen","evaluaDocen123");
            if(getConn().isWrapperFor(OracleConnection.class)){
            }
            ps = getConn().prepareCall(Properties.getInstance().getEvaluacionProperties().getString("url.get.todos"));
            
            //ARRAY cedulasArray = new ARRAY(arrDescText, getConn(), cedulas);
            ps.setString(1, semestres);
            ps.setString(2, materias);
            ps.setString(3, grupos);
            //ps.setArray(4, cedulasArray);
            ps.registerOutParameter(4, OracleTypes.CURSOR);
            ps.executeQuery();
            rs = (ResultSet) ps.getObject(4);
            if(rs != null) {
                while(rs.next()){
                dato = new UrlNotas();
                dato.setEncuesta(rs.getString("ENCUESTA"));
                dato.setFechaInicio(rs.getDate("FECHA_ENC_INICIO"));
                dato.setFechaTerminacion(rs.getDate("FECHA_ENC_FINAL"));
                dato.setCedula(rs.getString("CEDULA"));
                dato.setPunto(rs.getInt("PUNTO"));
                dato.setMetadato(rs.getString("METADATO"));
                dato.setMateria(rs.getLong("MATERIA"));
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
