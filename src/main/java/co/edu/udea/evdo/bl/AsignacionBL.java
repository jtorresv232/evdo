/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.bl;

import co.edu.udea.evdo.dao.impl.AsignacionDAO;
import co.edu.udea.evdo.dto.Asignacion;
import co.edu.udea.evdo.dto.Grupo;
import co.edu.udea.evdo.dto.Programa;
import co.edu.udea.evdo.dto.ws.DocenteMateriaGrupo;
import co.edu.udea.evdo.dto.ws.FacultadMares;
import co.edu.udea.evdo.dto.ws.MateriaMares;
import co.edu.udea.evdo.dto.ws.ProgramaMares;
import co.edu.udea.evdo.services.MaresService;
import co.edu.udea.evdo.ws.EncuestaClient;
import java.io.Serializable;
import java.util.Collection;
import java.util.Random;

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
        
        public Asignacion addAsignacion(Asignacion asignacion){
            return obtenerAsignacionDAO().addAsignacion(asignacion);
        }
        
        public void poblarAsignaciones(){
            MaresService ms = new MaresService();
            Collection<FacultadMares> facultades = ms.consultaFacultades();
            Collection<MateriaMares> materias;
            Collection<DocenteMateriaGrupo> docentes;
            Asignacion asignacion;
            Grupo grupo;
            GrupoBL gbl = new GrupoBL();
            for(FacultadMares facultad : facultades){
                System.out.println(facultad.getNombre());
                materias = ms.consultaMaterias(Long.toString(facultad.getCodigo()));
                for(MateriaMares materia:materias){
                    System.out.println( materia.getNombreMateria() + materia.getMateria());
                    for(int i = 1; i<=10; i++){
                        docentes = ms.consultaDocentes(Long.toString(materia.getMateria()),Integer.toString(i) );
                        for(DocenteMateriaGrupo docente: docentes){
                            asignacion = new Asignacion();
                            asignacion.setSemestre(20162);
                            asignacion.setMateria(materia.getMateria());
                            asignacion.setGrupo(i);
                            asignacion.setCedula(docente.getIdentificacionDocente());
                            asignacion.setProf_compartido(docente.getIndicadorProfesoresComparten());
                            asignacion.setProf_catedra(docente.getIndicadorCatedra());
                            asignacion.setNum_catedra(docente.getNumeroContratoCatedra());
                            asignacion.setTipo_periodo(docente.getTipoPeriodo());
                            asignacion.setNombre_docente(docente.getNombreDocente());
                            asignacion.setNombre_materia(materia.getNombreMateria());
                            asignacion.setProg(materia.getPrograma());
                            System.out.println("aprograma: "+asignacion.getPrograma());
                            System.out.println("mprograma"+ materia.getPrograma());
                            addAsignacion(asignacion);
            /*System.out.println("1: " + asignacion.getSemestre());
            System.out.println("2: " + asignacion.getMateria());
            System.out.println("3: " + asignacion.getGrupo());
            System.out.println("4: " + asignacion.getCedula());
            System.out.println("5: " + asignacion.getProf_compartido());
            System.out.println("6: " + asignacion.getProf_catedra());
            System.out.println("7: " + asignacion.getNum_catedra());
            System.out.println("8: " + asignacion.getTipo_periodo());
            System.out.println("9: " + asignacion.getNombre_docente());
            System.out.println("10: " + asignacion.getNombre_materia());
            System.out.println("11: " + asignacion.getPrograma());*/
                            grupo = new Grupo();
                            grupo.setSemestre(asignacion.getSemestre());
                            grupo.setMateria(asignacion.getMateria());
                            grupo.setGrupo(asignacion.getGrupo());
                            Random rand = new Random();
                            int num = rand.nextInt(20) + 15;
                            grupo.setNum_estudiantes(num);
                            gbl.addGrupo(grupo);
                        
                        }
                    }
                }
            }
                
            
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
