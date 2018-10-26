/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.dao.impl;

import co.edu.udea.evdo.dao.ConnectionPool;
import co.edu.udea.evdo.dto.Pregunta;
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
public class PreguntaDAO extends ConnectionPool{
    public Collection<Pregunta> getPreguntas(){
        CallableStatement ps = null;
        ResultSet rs = null;
        Collection<Pregunta> listaPreguntas = new LinkedList<>();
        Pregunta pregunta;
        try{
            ps = getConn().prepareCall(Properties.getInstance().getEvaluacionProperties().getString("pregunta.obtener"));
            rs = ps.executeQuery();
            if(rs!=null){
                while(rs.next()){
                    pregunta = new Pregunta();
                    pregunta.setNumero(rs.getInt("NUMERO"));
                    pregunta.setPregunta(rs.getString("PREGUNTA"));
                    pregunta.setTema(rs.getInt("TEMA"));
                    listaPreguntas.add(pregunta);
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }finally{
            close(ps,rs);
        }
        return listaPreguntas;
    }
    
    public Pregunta addPregunta(Pregunta preg){
        CallableStatement ps = null;
        ResultSet rs = null;
        Pregunta pregunta = new Pregunta();
        try{
            ps = getConn().prepareCall(Properties.getInstance().getEvaluacionProperties().getString("pregunta.agregar"));
            ps.setInt(1, preg.getNumero());
            ps.setString(3, preg.getPregunta());
            ps.setInt(2, preg.getTema());
            ps.executeQuery();
        }catch(Exception e){
            System.out.println(e);
        }finally{
            close(ps,rs);
        }
        return pregunta;
    }
    
    public boolean preguntaExiste(int numero){
        CallableStatement ps = null;
        ResultSet rs = null;
        boolean existe = false;
        try{
            ps = getConn().prepareCall(Properties.getInstance().getEvaluacionProperties().getString("pregunta.existe"));
            ps.setInt(1, numero);
            rs = ps.executeQuery();
            if(rs!=null){
                existe = true;
            }
        }catch(Exception e){
            System.out.println(e);
        }finally{
            close(ps,rs);
        }
        return existe;
    }
    
}
