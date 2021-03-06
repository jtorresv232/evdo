/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.dao.impl;

import co.edu.udea.evdo.dao.ConnectionPool;
import co.edu.udea.evdo.dto.Asignacion;
import co.edu.udea.evdo.exceptions.EvdoSQLException;
import co.edu.udea.evdo.properties.Properties;
import co.edu.udea.evdo.util.Notifications;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.util.Collection;
import java.util.LinkedList;
import oracle.jdbc.OracleTypes;
import org.apache.log4j.Logger;
import org.quartz.SchedulerException;

/**
 *
 * @author Jonathan
 */
public class AsignacionDAO extends ConnectionPool {

    private static final String SEMESTRE = "SEMESTRE";
    private static final String MATERIA = "MATERIA";
    private static final String GRUPO = "GRUPO";
    private static final String CEDULA = "CEDULA";
    private static final String PROF_COMPARTIDO = "PROF_COMPARTIDO";
    private static final String PROF_CATEDRA = "PROF_CATEDRA";
    private static final String NUM_CATEDRA = "NUM_CATEDRA";
    private static final String PORCENTAJE = "PORCENTAJE";
    private static final String ENCUESTA = "ENCUESTA";
    private static final String FECHA_ENC_INICIO = "FECHA_ENC_INICIO";
    private static final String FECHA_ENC_FINAL = "FECHA_ENC_FINAL";
    private static final String NUM_ESTUDIANTES = "NUM_ESTUDIANTES";
    private static final String ENCUESTADOS = "ENCUESTADOS";
    private static final String NOMBRE_DOCENTE = "NOMBRE_DOCENTE";
    private static final String NOMBRE_MATERIA = "NOMBRE_MATERIA";
    private static final String PROGRAMA = "PROGRAMA";

    static final Logger logger = Logger.getLogger(AsignacionDAO.class);

    public Asignacion addAsignacion(Asignacion asignacion) {
        CallableStatement ps = null;
        try {
            ps = getConn().prepareCall(Properties.getInstance().getEvaluacionProperties().getString("asignacion.agregar"));
            ps.setLong(1, asignacion.getSemestre());
            ps.setLong(2, asignacion.getMateria());
            ps.setLong(3, asignacion.getGrupo());
            ps.setString(4, asignacion.getCedula());
            ps.setString(5, asignacion.getProfCompartido());
            ps.setString(6, asignacion.getProfCatedra());
            ps.setLong(7, asignacion.getNumCatedra());
            ps.setString(8, asignacion.getTipoPeriodo());
            ps.setString(9, asignacion.getNombreDocente());
            ps.setString(10, asignacion.getNombreMateria());
            ps.setLong(11, asignacion.getPrograma());
            ps.executeQuery();
        } catch (Exception e) {
            logger.error(e);
        } finally {
            close(ps);
        }
        return asignacion;
    }

    public Collection<Asignacion> gettttt() {
        CallableStatement ps = null;
        ResultSet rs = null;
        Collection<Asignacion> listaAsignaciones = new LinkedList<>();
        Asignacion asignacion;
        try {
            ps = getConn().prepareCall(Properties.getInstance().getEvaluacionProperties().getString("asignacion.obtener"));
            ps.registerOutParameter(1, OracleTypes.CURSOR);
            ps.executeQuery();
            rs = (ResultSet) ps.getObject(1);
            if (rs != null) {
                while (rs.next()) {
                    asignacion = new Asignacion();
                    asignacion.setSemestre(rs.getLong(SEMESTRE));
                    asignacion.setMateria(rs.getLong(MATERIA));
                    asignacion.setGrupo(rs.getInt(GRUPO));
                    asignacion.setCedula(rs.getString(CEDULA));
                    asignacion.setProfCompartido(rs.getString(PROF_COMPARTIDO));
                    asignacion.setProfCatedra(rs.getString(PROF_CATEDRA));
                    asignacion.setNumCatedra(rs.getInt(NUM_CATEDRA));
                    asignacion.setPorcentaje(rs.getDouble(PORCENTAJE));
                    //asignacion.setTipo_periodo(rs.getString("TIPO_PERIODO"));
                    asignacion.setEncuesta(rs.getString(ENCUESTA));
                    asignacion.setFechaEncInicio(rs.getDate(FECHA_ENC_INICIO));
                    asignacion.setFechaEncFinal(rs.getDate(FECHA_ENC_FINAL));
                    asignacion.setEstudiantes(rs.getInt(NUM_ESTUDIANTES));
                    asignacion.setEncuestados(rs.getInt(ENCUESTADOS));
                    asignacion.setNombreDocente(rs.getString(NOMBRE_DOCENTE));
                    asignacion.setNombreMateria(rs.getString(NOMBRE_MATERIA));
                    asignacion.setPrograma(rs.getLong(PROGRAMA));
                    listaAsignaciones.add(asignacion);
                }
            }
        } catch (Exception e) {
            logger.error(e);
        } finally {
            close(ps);
        }
        return listaAsignaciones;
    }
    
    public void updAllAsigs(Asignacion objeto, long facultad) throws EvdoSQLException {
        CallableStatement ps = null;
        ResultSet rs = null;
        try{
            ps = getConn().prepareCall(Properties.getInstance().getEvaluacionProperties().getString("asignacion.all.actualizar"));
            if (objeto.getPrograma() > 0) {
                ps.setLong(4, objeto.getPrograma());
            } else {
                ps.setString(4, null);
            }
            if (objeto.getMateria() > 0) {
                ps.setLong(5, objeto.getMateria());
            } else {
                ps.setString(5, null);
            }
            if (objeto.getCedula() != "") {
                ps.setString(6, objeto.getCedula());
            } else {
                ps.setString(6, null);
            }
            ps.setString(1, objeto.getEncuesta());
            ps.setDate(2, objeto.getFechaEncInicio());
            ps.setDate(3, objeto.getFechaEncFinal());
            ps.setLong(7, facultad);
            if (objeto.getSemestre() > 0) {
                ps.setLong(8, objeto.getSemestre());
            } else {
                ps.setString(8, null);
            }
            if (!objeto.getTipo_programa().equalsIgnoreCase("ninguno")) {
                ps.setString(9, objeto.getTipo_programa());
            } else {
                ps.setString(9, null);
            }
            ps.executeQuery();
        }catch (Exception e){
            logger.debug(e);
            throw new EvdoSQLException();
        }finally{
            close(ps);
        }
    }
    
    public void updAllAsigsProf(Asignacion objeto, long facultad) throws EvdoSQLException {
        CallableStatement ps = null;
        ResultSet rs = null;
        try{
            ps = getConn().prepareCall(Properties.getInstance().getEvaluacionProperties().getString("asignacion.allprof.actualizar"));
            if (objeto.getPrograma() > 0) {
                ps.setLong(4, objeto.getPrograma());
            } else {
                ps.setString(4, null);
            }
            if (objeto.getMateria() > 0) {
                ps.setLong(5, objeto.getMateria());
            } else {
                ps.setString(5, null);
            }
            if (objeto.getCedula() != "") {
                ps.setString(6, objeto.getCedula());
            } else {
                ps.setString(6, null);
            }
            ps.setString(1, objeto.getEncuestaprof());
            ps.setDate(2, objeto.getFechaEncprofInicio());
            ps.setDate(3, objeto.getFechaEncprofFinal());
            ps.setLong(7, facultad);
            if (objeto.getSemestre() > 0) {
                ps.setLong(8, objeto.getSemestre());
            } else {
                ps.setString(8, null);
            }
            if (!objeto.getTipo_programa().equalsIgnoreCase("ninguno")) {
                ps.setString(9, objeto.getTipo_programa());
            } else {
                ps.setString(9, null);
            }
            ps.executeQuery();
        }catch (Exception e){
            logger.debug(e);
            throw new EvdoSQLException();
        }finally{
            close(ps);
        }
    }

    public int getTotalAsigs(long facultad, Asignacion objeto, int semestre, String tipo) {
        CallableStatement ps = null;
        ResultSet rs = null;
        int total = 0;
        try {
            ps = getConn().prepareCall(Properties.getInstance().getEvaluacionProperties().getString("asignacion.total"));
            if (facultad > 0) {
                ps.setLong(5, facultad);
            } else {
                ps.setString(5, null);
            }
            if (objeto.getPrograma() > 0) {
                ps.setLong(1, objeto.getPrograma());
            } else {
                ps.setString(1, null);
            }
            if (objeto.getMateria() > 0) {
                ps.setLong(2, objeto.getMateria());
            } else {
                ps.setString(2, null);
            }
            if (objeto.getCedula() != "") {
                ps.setString(3, objeto.getCedula());
            } else {
                ps.setString(3, null);
            }
            if (semestre > 0) {
                ps.setInt(6, semestre);
            } else {
                ps.setString(6, null);
            }
            if (!tipo.equalsIgnoreCase("ninguno")) {
                ps.setString(7, tipo);
            } else {
                ps.setString(7, null);
            }
            ps.registerOutParameter(4, OracleTypes.CURSOR);
            ps.executeQuery();
            rs = (ResultSet) ps.getObject(4);
            if (rs != null) {
                rs.next();
                total = rs.getInt("TOTAL");
            }
        } catch (Exception e) {
            logger.error(e);
        } finally {
            close(ps);
        }
        return total;
    }

    public Collection<Asignacion> getAsignaciones(int page, int size, long facultad,int semestre, String tipo, Asignacion objeto) {
        CallableStatement ps = null;
        ResultSet rs = null;
        Collection<Asignacion> listaAsignaciones = new LinkedList<>();
        Asignacion asignacion;
        try {
            ps = getConn().prepareCall(Properties.getInstance().getEvaluacionProperties().getString("asignacion.obtener"));
            if (objeto.getPrograma() > 0) {
                ps.setLong(3, objeto.getPrograma());
            } else {
                ps.setString(3, null);
            }
            if (facultad > 0) {
                ps.setLong(7, facultad);
            } else {
                ps.setString(7, null);
            }
            if (objeto.getMateria() > 0) {
                ps.setLong(4, objeto.getMateria());
            } else {
                ps.setString(4, null);
            }
            if (objeto.getCedula() != "") {
                ps.setString(5, objeto.getCedula());
            } else {
                ps.setString(5, null);
            }
            if (semestre > 0) {
                ps.setInt(8, semestre);
            } else {
                ps.setString(8, null);
            }
            if (!tipo.equalsIgnoreCase("ninguno")) {
                ps.setString(9, objeto.getTipo_programa());
            } else {
                ps.setString(9, null);
            }

            ps.setInt(1, page);
            ps.setInt(2, size);
            ps.registerOutParameter(6, OracleTypes.CURSOR);
            ps.executeQuery();
            rs = (ResultSet) ps.getObject(6);
            if (rs != null) {
                while (rs.next()) {
                    asignacion = new Asignacion();
                    asignacion.setSemestre(rs.getLong(SEMESTRE));
                    asignacion.setMateria(rs.getLong(MATERIA));
                    asignacion.setGrupo(rs.getInt(GRUPO));
                    asignacion.setCedula(rs.getString(CEDULA));
                    asignacion.setProfCompartido(rs.getString(PROF_COMPARTIDO));
                    asignacion.setProfCatedra(rs.getString(PROF_CATEDRA));
                    asignacion.setNumCatedra(rs.getInt(NUM_CATEDRA));
                    asignacion.setPorcentaje(rs.getDouble(PORCENTAJE));
                    //asignacion.setTipo_periodo(rs.getString("TIPO_PERIODO"));
                    asignacion.setEncuesta(rs.getString(ENCUESTA));
                    asignacion.setFechaEncInicio(rs.getDate(FECHA_ENC_INICIO));
                    asignacion.setFechaEncFinal(rs.getDate(FECHA_ENC_FINAL));
                    asignacion.setEstudiantes(rs.getInt(NUM_ESTUDIANTES));
                    asignacion.setEncuestados(rs.getInt(ENCUESTADOS));
                    asignacion.setNombreDocente(rs.getString(NOMBRE_DOCENTE));
                    asignacion.setNombreMateria(rs.getString(NOMBRE_MATERIA));
                    asignacion.setPrograma(rs.getLong(PROGRAMA));
                    asignacion.setTotal(rs.getInt("TOTAL"));
                    asignacion.setComentarios(rs.getInt("COMENTARIOS"));
                    asignacion.setEncuestaprof(rs.getString("ENCUESTAPROF"));
                    asignacion.setFechaEncprofInicio(rs.getDate("FECHA_ENCPROF_INICIO"));
                    asignacion.setFechaEncprofFinal(rs.getDate("FECHA_ENCPROF_FINAL"));
                    asignacion.setRespondida_prof(rs.getString("RESPONDIDA_PROF"));
                    listaAsignaciones.add(asignacion);
                }
            }
        } catch (Exception e) {
            logger.error(e);
        } finally {
            close(ps);
        }
        return listaAsignaciones;
    }

    public Asignacion updatePorcentaje(Asignacion asig) {
        CallableStatement ps = null;
        ResultSet rs = null;
        Asignacion asignacion = new Asignacion();
        double porcentaje = 0;
        double encuestados = ((double) asig.getEncuestados());
        encuestados = encuestados * 100;
        double estudiantes = ((double) asig.getEstudiantes());
        porcentaje = (encuestados / estudiantes);
        try {
            ps = getConn().prepareCall(Properties.getInstance().getEvaluacionProperties().getString("asignacion.porcentaje"));
            ps.setLong(1, asig.getSemestre());
            ps.setLong(2, asig.getMateria());
            ps.setInt(3, asig.getGrupo());
            ps.setString(4, asig.getCedula());
            ps.setDouble(5, porcentaje);
            ps.registerOutParameter(6, OracleTypes.CURSOR);
            ps.setInt(7, asig.getEncuestados());
            ps.executeQuery();
            rs = (ResultSet) ps.getObject(6);
            if (rs != null) {
                rs.next();
                asignacion.setPorcentaje(porcentaje);
                asignacion.setEncuestados(asig.getEncuestados());
            }
        } catch (Exception e) {
            logger.error(e);
        } finally {
            close(ps);
        }
        return asignacion;
    }

    public Asignacion updateAsignacion(Asignacion asig) throws SchedulerException, ParseException {
        CallableStatement ps = null;
        ResultSet rs = null;
        Asignacion asignacion = new Asignacion();
        try {
            ps = getConn().prepareCall(Properties.getInstance().getEvaluacionProperties().getString("asignacion.actualizar"));
            ps.setLong(1, asig.getSemestre());
            ps.setLong(2, asig.getMateria());
            ps.setInt(3, asig.getGrupo());
            ps.setString(4, asig.getCedula());
            ps.setString(5, asig.getEncuesta());
            ps.setDate(6, asig.getFechaEncInicio());
            ps.setDate(7, asig.getFechaEncFinal());
            ps.registerOutParameter(8, OracleTypes.CURSOR);
            ps.executeQuery();
            rs = (ResultSet) ps.getObject(8);
            if (rs != null) {
                rs.next();
                asignacion.setSemestre(rs.getInt(SEMESTRE));
                asignacion.setMateria(rs.getInt(MATERIA));
                asignacion.setGrupo(rs.getInt(GRUPO));
                asignacion.setCedula(rs.getString(CEDULA));
                asignacion.setProfCompartido(rs.getString(PROF_COMPARTIDO));
                asignacion.setProfCatedra(rs.getString(PROF_CATEDRA));
                asignacion.setNumCatedra(rs.getInt(NUM_CATEDRA));
                asignacion.setPorcentaje(rs.getDouble(PORCENTAJE));
                asignacion.setTipoPeriodo(rs.getString("TIPO_PERIODO"));
                asignacion.setEncuesta(rs.getString(ENCUESTA));
                asignacion.setFechaEncInicio(rs.getDate(FECHA_ENC_INICIO));
                asignacion.setFechaEncFinal(rs.getDate(FECHA_ENC_FINAL));

            }
        } catch (Exception e) {
            logger.error(e);
        } finally {
            close(ps);
        }
        return asignacion;
    }
    
    public Asignacion updateAsignacionProf(Asignacion asig) {
        CallableStatement ps = null;
        ResultSet rs = null;
        Asignacion asignacion = new Asignacion();
        try {
            ps = getConn().prepareCall(Properties.getInstance().getEvaluacionProperties().getString("asignacion.actualizarProf"));
            ps.setLong(1, asig.getSemestre());
            ps.setLong(2, asig.getMateria());
            ps.setInt(3, asig.getGrupo());
            ps.setString(4, asig.getCedula());
            ps.setString(5, asig.getEncuestaprof());
            ps.setDate(6, asig.getFechaEncprofInicio());
            ps.setDate(7, asig.getFechaEncprofFinal());
            ps.registerOutParameter(8, OracleTypes.CURSOR);
            ps.executeQuery();
            rs = (ResultSet) ps.getObject(8);
            if (rs != null) {
                rs.next();
                asignacion.setSemestre(rs.getInt(SEMESTRE));
                asignacion.setMateria(rs.getInt(MATERIA));
                asignacion.setGrupo(rs.getInt(GRUPO));
                asignacion.setCedula(rs.getString(CEDULA));
                asignacion.setProfCompartido(rs.getString(PROF_COMPARTIDO));
                asignacion.setProfCatedra(rs.getString(PROF_CATEDRA));
                asignacion.setNumCatedra(rs.getInt(NUM_CATEDRA));
                asignacion.setPorcentaje(rs.getDouble(PORCENTAJE));
                asignacion.setTipoPeriodo(rs.getString("TIPO_PERIODO"));
                asignacion.setEncuestaprof(rs.getString("ENCUESTAPROF"));
                asignacion.setFechaEncInicio(rs.getDate("FECHA_ENCPROF_INICIO"));
                asignacion.setFechaEncFinal(rs.getDate("FECHA_ENCPROF_FINAL"));

            }
        } catch (Exception e) {
            logger.error(e);
        } finally {
            close(ps);
        }
        return asignacion;
    }
    
}
