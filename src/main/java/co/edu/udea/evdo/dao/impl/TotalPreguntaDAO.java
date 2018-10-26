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

/**
 *
 * @author Jonathan
 */
public class TotalPreguntaDAO extends ConnectionPool{
    public Collection<TotalPregunta> getTotalPreguntas(){
        CallableStatement ps = null;
        ResultSet rs = null;
        Collection<TotalPregunta> listaTotales = new LinkedList<>();
        TotalPregunta total;
        try{
            ps = getConn().prepareCall(Properties.getInstance().getEvaluacionProperties().getString("totalpregunta.obtener"));
            rs = ps.executeQuery();
            if(rs!=null){
                while(rs.next()){
                    total = new TotalPregunta();
                    total.setCodigo(rs.getInt("CODIGO"));
                    total.setNumero(rs.getInt("NUMERO"));
                    total.setEvaluacion(rs.getString("EVALUACION"));
                    total.setMedia(rs.getDouble("MEDIA"));
                    total.setDesviacion(rs.getDouble("DESVIACION"));
                    total.setCoeficiente_desv(rs.getDouble("COEFICIENTE_DESV"));
                    total.setSemestre(rs.getInt("SEMESTRE"));
                    total.setMateria(rs.getInt("MATERIA"));
                    total.setGrupo(rs.getInt("GRUPO"));
                    total.setCedula(rs.getString("CEDULA"));
                    listaTotales.add(total);
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }finally{
            close(ps,rs);
        }
        return listaTotales;
    }
    
    public TotalPregunta addTotalPregunta(TotalPregunta tp){
        CallableStatement ps = null;
        ResultSet rs = null;
        TotalPregunta total = new TotalPregunta();
        try{
            ps = getConn().prepareCall(Properties.getInstance().getEvaluacionProperties().getString("totalpregunta.agregar"));
            ps.setInt(1, tp.getNumero());
            ps.setString(2, tp.getEvaluacion());
            ps.setDouble(3, tp.getMedia());
            ps.setDouble(4, tp.getDesviacion());
            ps.setDouble(5, tp.getCoeficiente_desv());
            ps.setInt(6, tp.getSemestre());
            ps.setInt(7, tp.getMateria());
            ps.setInt(8, tp.getGrupo());
            ps.setString(9, tp.getCedula());
            ps.executeQuery();
        }catch(Exception e){
            System.out.println(e);
        }finally{
            close(ps,rs);
        }
        return total;
    }
}
