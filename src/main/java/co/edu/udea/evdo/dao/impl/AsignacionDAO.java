/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.dao.impl;

import co.edu.udea.evdo.dao.ConnectionPool;
import co.edu.udea.evdo.dto.Asignacion;
import co.edu.udea.evdo.properties.Properties;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.LinkedList;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Jonathan
 */
public class AsignacionDAO extends ConnectionPool{
    
    public Asignacion addAsignacion(Asignacion asignacion){
        CallableStatement ps = null;
        ResultSet rs = null;
        try{
            ps = getConn().prepareCall(Properties.getInstance().getEvaluacionProperties().getString("asignacion.agregar"));
            System.out.println("1: " + asignacion.getSemestre());
            System.out.println("2: " + asignacion.getMateria());
            System.out.println("3: " + asignacion.getGrupo());
            System.out.println("4: " + asignacion.getCedula());
            System.out.println("5: " + asignacion.getProf_compartido());
            System.out.println("6: " + asignacion.getProf_catedra());
            System.out.println("7: " + asignacion.getNum_catedra());
            System.out.println("8: " + asignacion.getTipo_periodo());
            System.out.println("9: " + asignacion.getNombre_docente());
            System.out.println("10: " + asignacion.getNombre_materia());
            System.out.println("11: " + asignacion.getPrograma());
            ps.setLong(1, asignacion.getSemestre());
            ps.setLong(2, asignacion.getMateria());
            ps.setLong(3, asignacion.getGrupo());
            ps.setString(4, asignacion.getCedula());
            ps.setString(5, asignacion.getProf_compartido());
            ps.setString(6, asignacion.getProf_catedra());
            ps.setLong(7, asignacion.getNum_catedra());
            ps.setString(8, asignacion.getTipo_periodo());
            ps.setString(9, asignacion.getNombre_docente());
            ps.setString(10, asignacion.getNombre_materia());
            ps.setLong(11, asignacion.getPrograma());
            ps.executeQuery();
        }catch(Exception e){
            System.out.println("error");
            System.out.println(asignacion);
            System.out.println("1: " + asignacion.getSemestre());
            System.out.println("2: " + asignacion.getMateria());
            System.out.println("3: " + asignacion.getGrupo());
            System.out.println("4: " + asignacion.getCedula());
            System.out.println("5: " + asignacion.getProf_compartido());
            System.out.println("6: " + asignacion.getProf_catedra());
            System.out.println("7: " + asignacion.getNum_catedra());
            System.out.println("8: " + asignacion.getTipo_periodo());
            System.out.println("9: " + asignacion.getNombre_docente());
            System.out.println("10: " + asignacion.getNombre_materia());
            System.out.println("11: " + asignacion.getPrograma());
            System.err.println(e);
        }finally{
            close(ps,rs);
        }
        return asignacion;
    }
    public Collection<Asignacion> gettttt(){
        CallableStatement ps = null;
        ResultSet rs = null;
        Collection<Asignacion> listaAsignaciones = new LinkedList<>();
        Asignacion asignacion;
        try{
            ps = getConn().prepareCall(Properties.getInstance().getEvaluacionProperties().getString("asignacion.obtener"));
            ps.registerOutParameter(1, OracleTypes.CURSOR);
            ps.executeQuery();
            rs = (ResultSet) ps.getObject(1);
            if(rs!=null){
                while(rs.next()){
                    asignacion = new Asignacion();
                    asignacion.setSemestre(rs.getLong("SEMESTRE"));
                    asignacion.setMateria(rs.getLong("MATERIA"));
                    asignacion.setGrupo(rs.getInt("GRUPO"));
                    asignacion.setCedula(rs.getString("CEDULA"));
                    asignacion.setProf_compartido(rs.getString("PROF_COMPARTIDO"));
                    asignacion.setProf_catedra(rs.getString("PROF_CATEDRA"));
                    asignacion.setNum_catedra(rs.getInt("NUM_CATEDRA"));
                    asignacion.setPorcentaje(rs.getDouble("PORCENTAJE"));
                    //asignacion.setTipo_periodo(rs.getString("TIPO_PERIODO"));
                    asignacion.setEncuesta(rs.getString("ENCUESTA"));
                    asignacion.setFecha_enc_inicio(rs.getDate("FECHA_ENC_INICIO"));
                    asignacion.setFecha_enc_final(rs.getDate("FECHA_ENC_FINAL"));
                    asignacion.setEstudiantes(rs.getInt("NUM_ESTUDIANTES"));
                    asignacion.setEncuestados(rs.getInt("ENCUESTADOS"));
                    asignacion.setNombre_docente(rs.getString("NOMBRE_DOCENTE"));
                    asignacion.setNombre_materia(rs.getString("NOMBRE_MATERIA"));
                    asignacion.setPrograma(rs.getLong("PROGRAMA"));
                    listaAsignaciones.add(asignacion);
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }finally{
            close(ps,rs);
        }
        return listaAsignaciones;
    }
    
    public int getTotalAsigs(Asignacion objeto){
        CallableStatement ps = null;
        ResultSet rs = null;
        int total = 0;
        try{
            ps = getConn().prepareCall(Properties.getInstance().getEvaluacionProperties().getString("asignacion.total"));
            if(objeto.getPrograma() > 0){
                ps.setLong(1, objeto.getPrograma());
            }else{
                ps.setString(1, null);
            }
            if(objeto.getMateria()> 0){
                ps.setLong(2, objeto.getMateria());
            }else{
                ps.setString(2, null);
            }
            if(objeto.getCedula() != ""){
                ps.setString(3, objeto.getCedula());
            }else{
                ps.setString(3, null);
            }
            ps.registerOutParameter(4, OracleTypes.CURSOR);
            ps.executeQuery();
            rs = (ResultSet) ps.getObject(4);
            if(rs!=null){
                rs.next();
                total = rs.getInt("TOTAL");
            }
        }catch(Exception e){
            System.out.println(e);
        }finally{
            close(ps,rs);
        }
        return total;
    }
    
    public Collection<Asignacion> getAsignaciones(int page, int size, Asignacion objeto){
        CallableStatement ps = null;
        ResultSet rs = null;
        Collection<Asignacion> listaAsignaciones = new LinkedList<>();
        Asignacion asignacion;
                    System.out.println(objeto.getPrograma());

        try{
            ps = getConn().prepareCall(Properties.getInstance().getEvaluacionProperties().getString("asignacion.obtener"));
            if(objeto.getPrograma() > 0){
                ps.setLong(3, objeto.getPrograma());
            }else{
                ps.setString(3, null);
            }
            if(objeto.getMateria()> 0){
                ps.setLong(4, objeto.getMateria());
            }else{
                ps.setString(4, null);
            }
            if(objeto.getCedula() != ""){
                ps.setString(5, objeto.getCedula());
            }else{
                ps.setString(5, null);
            }
            
            ps.setInt(1, page);
            ps.setInt(2, size);
            ps.registerOutParameter(6, OracleTypes.CURSOR);
            ps.executeQuery();
            rs = (ResultSet) ps.getObject(6);
            if(rs!=null){
                while(rs.next()){
                    asignacion = new Asignacion();
                    asignacion.setSemestre(rs.getLong("SEMESTRE"));
                    asignacion.setMateria(rs.getLong("MATERIA"));
                    asignacion.setGrupo(rs.getInt("GRUPO"));
                    asignacion.setCedula(rs.getString("CEDULA"));
                    asignacion.setProf_compartido(rs.getString("PROF_COMPARTIDO"));
                    asignacion.setProf_catedra(rs.getString("PROF_CATEDRA"));
                    asignacion.setNum_catedra(rs.getInt("NUM_CATEDRA"));
                    asignacion.setPorcentaje(rs.getDouble("PORCENTAJE"));
                    //asignacion.setTipo_periodo(rs.getString("TIPO_PERIODO"));
                    asignacion.setEncuesta(rs.getString("ENCUESTA"));
                    asignacion.setFecha_enc_inicio(rs.getDate("FECHA_ENC_INICIO"));
                    asignacion.setFecha_enc_final(rs.getDate("FECHA_ENC_FINAL"));
                    asignacion.setEstudiantes(rs.getInt("NUM_ESTUDIANTES"));
                    asignacion.setEncuestados(rs.getInt("ENCUESTADOS"));
                    asignacion.setNombre_docente(rs.getString("NOMBRE_DOCENTE"));
                    asignacion.setNombre_materia(rs.getString("NOMBRE_MATERIA"));
                    asignacion.setPrograma(rs.getLong("PROGRAMA"));
                    asignacion.setTotal(rs.getInt("TOTAL"));
                    listaAsignaciones.add(asignacion);
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }finally{
            close(ps,rs);
        }
        return listaAsignaciones;
    }
    
    public Asignacion updatePorcentaje(Asignacion asig){
        CallableStatement ps = null;
        ResultSet rs = null;
        Asignacion asignacion = new Asignacion();
        double porcentaje = 0;
        double encuestados = ((double)asig.getEncuestados());
        encuestados = encuestados * 100;
        double estudiantes = ((double)asig.getEstudiantes());
        porcentaje = (encuestados/estudiantes);
        System.out.println(asig.getEstudiantes());
        System.out.println(asig.getEncuestados());
        System.out.println(porcentaje);
        try{
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
           if(rs!=null){
               rs.next();
               System.out.println(rs);
               asignacion.setPorcentaje(porcentaje);
               asignacion.setEncuestados(asig.getEncuestados());
           }
        }catch(Exception e){
            System.out.println(e);
        }finally{
            close(ps,rs);
        }
        return asignacion;
    }
    
    public Asignacion updateAsignacion(Asignacion asig){
        CallableStatement ps = null;
        ResultSet rs = null;
        Asignacion asignacion = new Asignacion();
        try{
            ps = getConn().prepareCall(Properties.getInstance().getEvaluacionProperties().getString("asignacion.actualizar"));
            ps.setLong(1, asig.getSemestre());
            ps.setLong(2, asig.getMateria());
            ps.setInt(3, asig.getGrupo());
            ps.setString(4, asig.getCedula());
            ps.setString(5, asig.getEncuesta());
            ps.setDate(6, asig.getFecha_enc_inicio());
            ps.setDate(7, asig.getFecha_enc_final());
            ps.registerOutParameter(8, OracleTypes.CURSOR);
            ps.executeQuery();
            rs = (ResultSet) ps.getObject(8);
            if(rs!=null){
                rs.next();
                asignacion.setSemestre(rs.getInt("SEMESTRE"));
                asignacion.setMateria(rs.getInt("MATERIA"));
                asignacion.setGrupo(rs.getInt("GRUPO"));
                asignacion.setCedula(rs.getString("CEDULA"));
                asignacion.setProf_compartido(rs.getString("PROF_COMPARTIDO"));
                asignacion.setProf_catedra(rs.getString("PROF_CATEDRA"));
                asignacion.setNum_catedra(rs.getInt("NUM_CATEDRA"));
                asignacion.setPorcentaje(rs.getDouble("PORCENTAJE"));
                asignacion.setTipo_periodo(rs.getString("TIPO_PERIODO"));
                asignacion.setEncuesta(rs.getString("ENCUESTA"));
                asignacion.setFecha_enc_inicio(rs.getDate("FECHA_ENC_INICIO"));
                asignacion.setFecha_enc_final(rs.getDate("FECHA_ENC_FINAL"));
                
            }
        }catch(Exception e){
            System.out.println(e);
        }finally{
            close(ps,rs);
        }
        return asignacion;
    }
}
