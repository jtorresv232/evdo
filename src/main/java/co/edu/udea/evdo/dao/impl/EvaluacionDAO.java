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

/**
 *
 * @author Jonathan
 */
public class EvaluacionDAO extends ConnectionPool{
    
    public Collection<Evaluacion> getEvaluaciones(){
        CallableStatement ps = null;
        ResultSet rs = null;
        Collection<Evaluacion> listaEvaluaciones = new LinkedList<>();
        Evaluacion eval;
        try{
            ps = getConn().prepareCall(Properties.getInstance().getEvaluacionProperties().getString("evaluacion.obtener"));
            rs = ps.executeQuery();
            if(rs!=null){
                while(rs.next()){
                    eval = new Evaluacion();
                    eval.setCodigo(rs.getString("CODIGO"));
                    eval.setSemestre(rs.getInt("SEMESTRE"));
                    eval.setPorcentaje(rs.getDouble("PORCENTAJE"));
                    eval.setPorcentajeprofesor(rs.getDouble("PORCENTAJEPROFESOR"));
                    listaEvaluaciones.add(eval);
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }finally{
            close(ps,rs);
        }
        return listaEvaluaciones;
    }
    
    public Evaluacion addEvalucion(Evaluacion evaluacion){
        CallableStatement ps = null;
        ResultSet rs = null;
        Evaluacion eval = new Evaluacion();
        try{
            ps = getConn().prepareCall(Properties.getInstance().getEvaluacionProperties().getString("evaluacion.agregar"));
            ps.setString(1, evaluacion.getCodigo());
            ps.setInt(2, evaluacion.getSemestre());
            ps.setDouble(3, evaluacion.getPorcentaje());
            ps.setDouble(4, evaluacion.getPorcentajeprofesor());
            ps.registerOutParameter(5, OracleTypes.CURSOR);
            ps.executeQuery();
            rs = (ResultSet) ps.getObject(5);
            if(rs!=null){
                rs.next();
                eval.setCodigo(rs.getString("CODIGO"));
                eval.setSemestre(rs.getInt("SEMESTRE"));
                eval.setPorcentaje(rs.getDouble("PORCENTAJE"));
                eval.setPorcentajeprofesor(rs.getDouble("PORCENTAJEPROFESOR"));
                
            }
        }catch(Exception e){
            System.out.println(e);
        }finally{
            close(ps,rs);
        }
        return eval;
    }
    
    public boolean updateEvaluacion(Evaluacion evaluacion){
        CallableStatement ps = null;
        ResultSet rs = null;
        boolean actualizado = false;
        try{
            ps = getConn().prepareCall(Properties.getInstance().getEvaluacionProperties().getString("evaluacion.actualizar"));
            ps.setString(1, evaluacion.getCodigo());
            ps.setInt(2, evaluacion.getSemestre());
            ps.setDouble(3, evaluacion.getPorcentaje());
            ps.setDouble(4, evaluacion.getPorcentajeprofesor());
            actualizado = ps.executeUpdate() > 0;
        }catch(Exception e){
            System.out.println(e);
        }finally{
            close(ps,rs);
        }
        return actualizado;
    }
}
