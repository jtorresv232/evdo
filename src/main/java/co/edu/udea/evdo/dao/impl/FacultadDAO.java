/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.dao.impl;

import co.edu.udea.evdo.dao.ConnectionPool;
import co.edu.udea.evdo.dto.Facultad;
import co.edu.udea.evdo.properties.Properties;
import java.sql.CallableStatement;
import java.sql.ResultSet;

/**
 *
 * @author Jonathan
 */
public class FacultadDAO extends ConnectionPool{
    
    public Facultad addFacultad(Facultad facultad){
        CallableStatement ps = null;
        ResultSet rs = null;
        Facultad facult;
        try{
            ps = getConn().prepareCall(Properties.getInstance().getEvaluacionProperties().getString("facultades.poblar"));
            ps.setLong(1, facultad.getCodigo());
            ps.setString(2, facultad.getNombre());
            ps.executeQuery();
        }catch(Exception e){
            System.err.println(e);
        }finally{
            close(ps,rs);
        }
        return facultad;
    }
}
