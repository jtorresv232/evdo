/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.bl;

import co.edu.udea.evdo.dao.impl.ProgramaDAO;
import co.edu.udea.evdo.dto.Programa;
import co.edu.udea.evdo.dto.ws.ProgramaMares;
import co.edu.udea.evdo.services.MaresService;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author Jonathan
 */
public class ProgramaBL implements Serializable{
    private static ProgramaBL singletonInstance = new ProgramaBL();

    public ProgramaBL() {
    }
        
        public static ProgramaBL getInstance() {
        synchronized (ProgramaBL.class) {
            if (singletonInstance == null) { 
                singletonInstance = new ProgramaBL();
            }
        }
        return singletonInstance;
    }
        
        public Programa addPrograma(Programa programa){
            return obtenerProgramaDAO().addPrograma(programa);
        }
        
        public Collection<Programa> getProgramas(){
            return obtenerProgramaDAO().getProgramas();
        }
        
        public Programa getNumeros(long programa){
            return obtenerProgramaDAO().getNumeros(programa);
        }
        
        public void poblarProgramaes(){
            MaresService ms = new MaresService();
            Collection<ProgramaMares> listaProgramaes = ms.consultaProgramas();
            Programa programa;
            for(ProgramaMares prog : listaProgramaes){
                if (prog.getEstado().equalsIgnoreCase("FUNC")) {
                    programa = new Programa();
                    programa.setPrograma(prog.getPrograma());
                    programa.setNombrePrograma(prog.getNombrePrograma());
                    programa.setTipoPrograma(prog.getTipoPrograma());
                    programa.setSede(prog.getSede());
                    programa.setFacultad(prog.getFacultad());
                    programa.setNombreFacultad(prog.getNombreFacultad());
                    programa.setCreditosGrado(prog.getCreditosGrado());
                    programa.setVersionActual(prog.getVersionActual());
                    programa.setVersiones(prog.getVersiones());
                    programa.setEstado(prog.getEstado());
                    addPrograma(programa);
                }
            }
        }
        
        private ProgramaDAO obtenerProgramaDAO() {
        ProgramaDAO DAO = new ProgramaDAO();
        return DAO;
    }

}