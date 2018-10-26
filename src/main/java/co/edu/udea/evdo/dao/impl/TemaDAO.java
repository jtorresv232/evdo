/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.dao.impl;

import co.edu.udea.evdo.dao.ConnectionPool;
import co.edu.udea.evdo.dto.Tema;
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
public class TemaDAO extends ConnectionPool{
    public Collection<Tema> getTemas(){
        CallableStatement ps = null;
        ResultSet rs = null;
        Collection<Tema> listaTemas = new LinkedList<>();
        Tema tema;
        try{
            ps = getConn().prepareCall(Properties.getInstance().getEvaluacionProperties().getString("tema.obtener"));
            rs = ps.executeQuery();
            if(rs!=null){
                while(rs.next()){
                    tema = new Tema();
                    tema.setTema(rs.getInt("CODIGO"));
                    tema.setDescripcion(rs.getString("DESCRIPCION"));
                    listaTemas.add(tema);
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }finally{
            close(ps,rs);
        }
        return listaTemas;
    }
    
    public Tema addTema(Tema tem){
        CallableStatement ps = null;
        ResultSet rs = null;
        Tema tema = new Tema();
        try{
            ps = getConn().prepareCall(Properties.getInstance().getEvaluacionProperties().getString("tema.agregar"));
            ps.setInt(1, tem.getTema());
            ps.setString(2, tem.getDescripcion());
            ps.executeQuery();
            
        }catch(Exception e){
            System.out.println(e);
        }finally{
            close(ps,rs);
        }
        return tema;
    }
    
    public boolean temaExiste(int tema){
        CallableStatement ps = null;
        ResultSet rs = null;
        boolean existe = false;
        try{
            ps = getConn().prepareCall(Properties.getInstance().getEvaluacionProperties().getString("tema.existe"));
            ps.setInt(1, tema);
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
