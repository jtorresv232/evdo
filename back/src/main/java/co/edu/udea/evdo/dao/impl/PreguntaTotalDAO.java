/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.dao.impl;

import co.edu.udea.evdo.dao.ConnectionPool;
import co.edu.udea.evdo.dto.TotalPregunta;
import co.edu.udea.evdo.properties.Properties;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import org.apache.log4j.Logger;

/**
 *
 * @author JonathanTorresVelez
 */
public class PreguntaTotalDAO extends ConnectionPool{
    static final Logger logger = Logger.getLogger(PreguntaTotalDAO.class);
    
    public TotalPregunta addTotal(TotalPregunta total) {
        logger.debug("agregando tema");
        CallableStatement ps = null;
        try{
            ps = getConn().prepareCall(Properties.getInstance().getEvaluacionProperties().getString("totalpregunta.agregar"));
            ps.setInt(1, total.getNumero());
            ps.setString(2, total.getEvaluacion());
            BigDecimal media = new BigDecimal(total.getMedia());
            BigDecimal Desviacion = new BigDecimal(total.getDesviacion());
            BigDecimal Coef = new BigDecimal(total.getCoeficienteDesv());
            ps.setFloat(3, media.floatValue());
            ps.setFloat(4, Desviacion.floatValue());
            ps.setFloat(5, Coef.floatValue());
            ps.setLong(6, total.getSemestre());
            ps.setLong(7, total.getMateria());
            ps.setInt(8, total.getGrupo());
            ps.setString(9, total.getCedula());
            ps.executeUpdate();
            getConn().commit();
        }catch(Exception e){
            logger.error(e);
        }finally{
            close(ps);
        }
        return total;
    }
}
