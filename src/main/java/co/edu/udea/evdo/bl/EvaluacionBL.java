/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.bl;

import co.edu.udea.evdo.dao.impl.EvaluacionDAO;
import co.edu.udea.evdo.dto.Evaluacion;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author Jonathan
 */
public class EvaluacionBL implements Serializable{
    private static EvaluacionBL singletonInstance = new EvaluacionBL();

    public EvaluacionBL() {
    }
        
        public static EvaluacionBL getInstance() {
        synchronized (EvaluacionBL.class) {
            if (singletonInstance == null) { 
                singletonInstance = new EvaluacionBL();
            }
        }
        return singletonInstance;
    }
        
        public Collection<Evaluacion> getEvaluaciones(){
            return obtenerEvaluacionDAO().getEvaluaciones();
        
        }
        
        public Evaluacion addEvaluacion(Evaluacion evaluacion){
            return obtenerEvaluacionDAO().addEvalucion(evaluacion);
        }
        
        public boolean updateEvaluacion(Evaluacion evaluacion){
            return obtenerEvaluacionDAO().updateEvaluacion(evaluacion);
        }
        
        private EvaluacionDAO obtenerEvaluacionDAO() {
        EvaluacionDAO DAO = new EvaluacionDAO();
        return DAO;
    }

}
