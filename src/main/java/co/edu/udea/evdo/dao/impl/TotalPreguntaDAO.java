/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.dao.impl;

import co.edu.udea.evdo.dao.ConnectionPool;
import co.edu.udea.evdo.dto.TotalPregunta;
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
public class TotalPreguntaDAO extends ConnectionPool {

    static final Logger logger = Logger.getLogger(TotalPreguntaDAO.class);

    public Collection<TotalPregunta> getTotalPreguntas() {
        CallableStatement ps = null;
        ResultSet rs = null;
        Collection<TotalPregunta> listaTotales = new LinkedList<>();
        TotalPregunta total;
        try {
            ps = getConn().prepareCall(Properties.getInstance().getEvaluacionProperties().getString("totalpregunta.obtener"));
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    total = new TotalPregunta();
                    total.setCodigo(rs.getInt("CODIGO"));
                    total.setNumero(rs.getInt("NUMERO"));
                    total.setEvaluacion(rs.getString("EVALUACION"));
                    total.setMedia(rs.getDouble("MEDIA"));
                    total.setDesviacion(rs.getDouble("DESVIACION"));
                    total.setCoeficienteDesv(rs.getDouble("COEFICIENTE_DESV"));
                    total.setSemestre(rs.getInt("SEMESTRE"));
                    total.setMateria(rs.getInt("MATERIA"));
                    total.setGrupo(rs.getInt("GRUPO"));
                    total.setCedula(rs.getString("CEDULA"));
                    listaTotales.add(total);
                }
            }
        } catch (Exception e) {
            logger.error(e);
        } finally {
            close(ps);
        }
        return listaTotales;
    }
    
    public Collection<TotalPregunta> getTotalesPorPrograma(long programa){
        CallableStatement ps = null;
        ResultSet rs = null;
        Collection<TotalPregunta> listaTotales = new LinkedList<>();
        TotalPregunta total;
        try{
            ps = getConn().prepareCall(Properties.getInstance().getEvaluacionProperties().getString("totalpregunta.programa"));
            ps.setLong(1, programa);
            ps.registerOutParameter(2, OracleTypes.CURSOR);
            ps.executeQuery();
            rs = (ResultSet) ps.getObject(2);
            if (rs != null) {
                while (rs.next()) {
                    total = new TotalPregunta();
                    total.setCedula(rs.getString("CEDULA"));
                    total.setGrupo(rs.getInt("GRUPO"));
                    total.setMateria(rs.getLong("MATERIA"));
                    total.setSemestre(rs.getLong("SEMESTRE"));
                    total.setNumero(rs.getInt("NUMERO"));
                    total.setPregunta(rs.getString("PREGUNTA"));
                    total.setMedia(rs.getDouble("MEDIA"));
                    total.setDesviacion(rs.getDouble("DESVIACION"));
                    total.setCoeficienteDesv(rs.getDouble("COEFICIENTE_DESV"));
                    listaTotales.add(total);
                }
            }
        }catch(Exception e){
            this.logger.error(e);
        }finally{
            close(ps);
            this.logger.debug("Se han consultado todos los totales por pregunta para los cursos del programa " + programa);
        }
        
        return listaTotales;
    }
    
    public Collection<TotalPregunta> getTotalesPorDocente(String cedula){
        CallableStatement ps = null;
        ResultSet rs = null;
        Collection<TotalPregunta> listaTotales = new LinkedList<>();
        TotalPregunta total;
        try{
            ps = getConn().prepareCall(Properties.getInstance().getEvaluacionProperties().getString("totalpregunta.docente"));
            ps.setString(1, cedula);
            ps.registerOutParameter(2, OracleTypes.CURSOR);
            ps.executeQuery();
            rs = (ResultSet) ps.getObject(2);
            if (rs != null) {
                while (rs.next()) {
                    total = new TotalPregunta();
                    total.setCedula(rs.getString("CEDULA"));
                    total.setGrupo(rs.getInt("GRUPO"));
                    total.setMateria(rs.getLong("MATERIA"));
                    total.setSemestre(rs.getLong("SEMESTRE"));
                    total.setNumero(rs.getInt("NUMERO"));
                    total.setPregunta(rs.getString("PREGUNTA"));
                    total.setMedia(rs.getDouble("MEDIA"));
                    total.setDesviacion(rs.getDouble("DESVIACION"));
                    total.setCoeficienteDesv(rs.getDouble("COEFICIENTE_DESV"));
                    listaTotales.add(total);
                }
            }
        }catch(Exception e){
            this.logger.error(e);
        }finally{
            close(ps);
            this.logger.debug("Se han consultado todos los totales por pregunta para los cursos del docente " + cedula);
        }
        
        return listaTotales;
    }

    public TotalPregunta addTotalPregunta(TotalPregunta tp) {
        CallableStatement ps = null;
        TotalPregunta total = new TotalPregunta();
        try {
            ps = getConn().prepareCall(Properties.getInstance().getEvaluacionProperties().getString("totalpregunta.agregar"));
            ps.setInt(1, tp.getNumero());
            ps.setString(2, tp.getEvaluacion());
            ps.setDouble(3, tp.getMedia());
            ps.setDouble(4, tp.getDesviacion());
            ps.setDouble(5, tp.getCoeficienteDesv());
            ps.setLong(6, tp.getSemestre());
            ps.setLong(7, tp.getMateria());
            ps.setInt(8, tp.getGrupo());
            ps.setString(9, tp.getCedula());
            ps.executeQuery();
        } catch (Exception e) {
            logger.error(e);
        } finally {
            close(ps);
        }
        return total;
    }
}
