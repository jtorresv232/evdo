/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.bl;

import co.edu.udea.evdo.dao.impl.AsignacionDAO;
import co.edu.udea.evdo.dto.Asignacion;
import co.edu.udea.evdo.ws.EncuestaClient;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author Jonathan
 */
public class AsignacionBL implements Serializable{
    private static AsignacionBL singletonInstance = new AsignacionBL();

    public AsignacionBL() {
    }
        
        public static AsignacionBL getInstance() {
        synchronized (AsignacionBL.class) {
            if (singletonInstance == null) { 
                singletonInstance = new AsignacionBL();
            }
        }
        return singletonInstance;
    }
        
        public Collection<Asignacion> getAsignaciones(){
            return obtenerAsignacionDAO().getAsignaciones();
        
        }
        
        public Asignacion updateAsignacion(Asignacion asignacion){
            return obtenerAsignacionDAO().updateAsignacion(asignacion);
        }
        
        public Asignacion updatePorcentaje(Asignacion asignacion){
            return obtenerAsignacionDAO().updatePorcentaje(asignacion);
        }
        
        private AsignacionDAO obtenerAsignacionDAO() {
        AsignacionDAO DAO = new AsignacionDAO();
        return DAO;
    }

}
