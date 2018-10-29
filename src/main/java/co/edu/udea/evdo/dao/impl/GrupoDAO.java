/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.dao.impl;

import co.edu.udea.evdo.dao.ConnectionPool;
import co.edu.udea.evdo.dto.Grupo;
import co.edu.udea.evdo.properties.Properties;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.LinkedList;

/**
 *
 * @author Jonathan
 */
public class GrupoDAO extends ConnectionPool{
    
    public Collection<Grupo> getGrupos(){
        CallableStatement ps = null;
        ResultSet rs = null;
        Collection<Grupo> listaGrupos = new LinkedList<>();
        Grupo grupo;
        try{
            ps = getConn().prepareCall(Properties.getInstance().getEvaluacionProperties().getString("grupo.obtener"));
            rs = ps.executeQuery();
            if(rs!=null){
                while(rs.next()){
                    grupo = new Grupo();
                    
                    grupo.setSemestre(rs.getLong("SEMESTRE"));
                    grupo.setMateria(rs.getLong("MATERIA"));
                    grupo.setGrupo(rs.getInt("GRUPO"));
                    grupo.setNum_estudiantes(rs.getInt("NUM_ESTUDIANTES"));
                    listaGrupos.add(grupo);
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }finally{
            close(ps,rs);
        }
        return listaGrupos;
    }
    
    public Grupo addGrupo(Grupo grupo){
        CallableStatement ps = null;
        ResultSet rs = null;
        try{
            ps = getConn().prepareCall(Properties.getInstance().getEvaluacionProperties().getString("grupo.agregar"));
            ps.setLong(1, grupo.getSemestre());
            ps.setLong(2, grupo.getMateria());
            ps.setInt(3, grupo.getGrupo());
            ps.setInt(4, grupo.getNum_estudiantes());
            ps.executeQuery();
        }catch(Exception e ){
            System.err.println(e);
        }finally{
            close(ps,rs);
        }
        return grupo;
    }
    
}
