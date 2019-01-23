/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.dao.impl;

import co.edu.udea.evdo.dao.ConnectionPool;
import co.edu.udea.evdo.dto.Materia;
import co.edu.udea.evdo.properties.Properties;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.LinkedList;
import org.apache.log4j.Logger;

/**
 *
 * @author Jonathan
 */
public class MateriaDAO extends ConnectionPool{
    
    static final Logger logger = Logger.getLogger(MateriaDAO.class);
    
    public Collection<Materia> getMaterias(long semestre, long programa){
        CallableStatement ps = null;
        ResultSet rs = null;
        Collection<Materia> listaMaterias = new LinkedList<>();
        Materia materia;
        try{
            ps = getConn().prepareCall(Properties.getInstance().getEvaluacionProperties().getString("materia.obtener"));
            ps.setLong(1, programa);
            ps.setLong(2, semestre);
            rs = ps.executeQuery();
            if(rs != null){
                while(rs.next()){
                    materia = new Materia();
                    materia.setMateria(rs.getInt("MATERIA"));
                    materia.setNombreMateria(rs.getString("NOMBRE_MATERIA"));
                    listaMaterias.add(materia);
                }
            }
        }catch(Exception e){
            this.logger.error(e);
        }finally{
            this.logger.debug("Se consultaron todas las materias del programa: " + programa);
            close(ps);
        }
        return listaMaterias;
    }
}
