/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.bl;

import co.edu.udea.evdo.dao.impl.FacultadDAO;
import co.edu.udea.evdo.dto.Facultad;
import co.edu.udea.evdo.dto.ws.FacultadMares;
import co.edu.udea.evdo.services.MaresService;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author Jonathan
 */
public class FacultadBL implements Serializable{
    private static FacultadBL singletonInstance = new FacultadBL();

    public FacultadBL() {
    }
        
        public static FacultadBL getInstance() {
        synchronized (FacultadBL.class) {
            if (singletonInstance == null) { 
                singletonInstance = new FacultadBL();
            }
        }
        return singletonInstance;
    }
        
        public Facultad addFacultad(Facultad facultad){
            return obtenerFacultadDAO().addFacultad(facultad);
        }
        
        public Collection<Facultad> getFacultades(){
            return obtenerFacultadDAO().getFacultades();
        }
        
        public String updateFacultad(Facultad facultad){
            return obtenerFacultadDAO().updateFacultad(facultad);
        }
        
        public void poblarFacultades(){
            MaresService ms = new MaresService();
            Collection<FacultadMares> listaFacultades = ms.consultaFacultades();
            Facultad facultad;
            for(FacultadMares facult : listaFacultades){
                facultad = new Facultad();
                facultad.setCodigo(facult.getCodigo());
                facultad.setNombre(facult.getNombre());
                addFacultad(facultad);
            }
        }
        
        private FacultadDAO obtenerFacultadDAO() {
        FacultadDAO DAO = new FacultadDAO();
        return DAO;
    }

}