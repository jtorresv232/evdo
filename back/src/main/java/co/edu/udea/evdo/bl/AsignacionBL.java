/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.bl;

import co.edu.udea.evdo.dao.impl.AsignacionDAO;
import co.edu.udea.evdo.dto.Asignacion;
import co.edu.udea.evdo.dto.Evaluacion;
import co.edu.udea.evdo.dto.Grupo;
import co.edu.udea.evdo.dto.ws.DocenteMateriaGrupo;
import co.edu.udea.evdo.dto.ws.FacultadMares;
import co.edu.udea.evdo.dto.ws.MateriaMares;
import co.edu.udea.evdo.exceptions.EvdoSQLException;
import co.edu.udea.evdo.services.MaresService;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Collection;
import java.util.Random;
import org.quartz.SchedulerException;

/**
 *
 * @author Jonathan
 */
public class AsignacionBL implements Serializable {

    private static AsignacionBL singletonInstance = new AsignacionBL();

    public AsignacionBL() {
        // empty constructor
    }

    public static AsignacionBL getInstance() {
        synchronized (AsignacionBL.class) {
            if (singletonInstance == null) {
                singletonInstance = new AsignacionBL();
            }
        }
        return singletonInstance;
    }

    public Collection<Asignacion> getAsignaciones(int page, int size, long facultad, int semestre, String tipo, Asignacion asig) {
        return obtenerAsignacionDAO().getAsignaciones(page, size, facultad, semestre, tipo, asig);

    }

    public int getAsigTotal(long facultad, Asignacion asig, int semestre, String tipo) {
        return obtenerAsignacionDAO().getTotalAsigs( facultad, asig, semestre, tipo);
    }

    public Asignacion addAsignacion(Asignacion asignacion) {
        return obtenerAsignacionDAO().addAsignacion(asignacion);
    }
    
    public void updAllAsigs(Asignacion asignacion, long facultad) throws EvdoSQLException {
        obtenerAsignacionDAO().updAllAsigs(asignacion, facultad);
    }
    
    public void updAllAsigsProf(Asignacion asignacion, long facultad) throws EvdoSQLException {
        obtenerAsignacionDAO().updAllAsigsProf(asignacion, facultad);
    }
    
    public void actualizarEvaluacion(String codigo, double porcentaje, boolean cargado){
        Evaluacion eval = new Evaluacion();
        eval.setCargado(cargado);
        eval.setPorcentaje(porcentaje);
        eval.setCodigo(codigo);
        EvaluacionBL.getInstance().updateEvaluacion(eval);
    }

    public void poblarAsignaciones(int semestre) {
        String codigoEvaluacion = "eval" + semestre;
        MaresService ms = new MaresService();
        Collection<FacultadMares> facultades = ms.consultaFacultades();
        Collection<MateriaMares> materias;
        Collection<DocenteMateriaGrupo> docentes;
        Asignacion asignacion;
        Grupo grupo;
        GrupoBL gbl = new GrupoBL();
        double numFacultades = facultades.size();
        double actualFacultad = 1;
        for (FacultadMares facultad : facultades) {
            materias = ms.consultaMaterias(Long.toString(facultad.getCodigo()));
            double numMaterias = materias.size();
            double actualMateria = 1;
            double porcentaje = 0;
            for (MateriaMares materia : materias) {
                int iGrupo = 1;
                int maxEstudiantes = ms.getEstudiantes(Long.toString(materia.getMateria()), Integer.toString(0)).size();
                int sumaEstudiantes = 0;
                boolean tieneGrupos = maxEstudiantes > sumaEstudiantes;
                while(sumaEstudiantes < maxEstudiantes && iGrupo < 100){
                //for (int i = 1; i <= 10; i++) {
                    docentes = ms.consultaDocentes(Long.toString(materia.getMateria()), Integer.toString(iGrupo), Integer.toString(semestre));
                    for (DocenteMateriaGrupo docente : docentes) {
                        asignacion = new Asignacion();
                        asignacion.setSemestre(semestre);
                        asignacion.setMateria(materia.getMateria());
                        asignacion.setGrupo(iGrupo);
                        asignacion.setCedula(docente.getIdentificacionDocente());
                        asignacion.setProfCompartido(docente.getIndicadorProfesoresComparten());
                        asignacion.setProfCatedra(docente.getIndicadorCatedra());
                        asignacion.setNumCatedra(docente.getNumeroContratoCatedra());
                        asignacion.setTipoPeriodo(docente.getTipoPeriodo());
                        asignacion.setNombreDocente(docente.getNombreDocente());
                        asignacion.setNombreMateria(materia.getNombreMateria());
                        asignacion.setPrograma(materia.getPrograma());
                        addAsignacion(asignacion);
                        grupo = new Grupo();
                        grupo.setSemestre(semestre);
                        grupo.setMateria(asignacion.getMateria());
                        grupo.setGrupo(asignacion.getGrupo());
//                        Random rand = new Random();
//                        int num = rand.nextInt(20) + 15;
                        int numEstudiantes = ms.getEstudiantes(Long.toString(asignacion.getMateria()), Integer.toString(asignacion.getGrupo())).size();
                        grupo.setNumEstudiantes(numEstudiantes);
                        gbl.addGrupo(grupo);
                        sumaEstudiantes = sumaEstudiantes + numEstudiantes;
                        
                    }
                    iGrupo = iGrupo + 1;
                //}
                }
                porcentaje = ((actualFacultad/numFacultades)*(actualMateria/numMaterias))*100;
                actualizarEvaluacion(codigoEvaluacion,porcentaje,false);
                actualMateria = actualMateria +1;
            }
            porcentaje = (actualFacultad/numFacultades)* 100;
            actualizarEvaluacion(codigoEvaluacion,porcentaje,false);
            actualFacultad = actualFacultad +1;
        }
        actualizarEvaluacion(codigoEvaluacion,100,true);
        

    }

    public Asignacion updateAsignacion(Asignacion asignacion) throws SchedulerException, ParseException {
        return obtenerAsignacionDAO().updateAsignacion(asignacion);
    }
    
    public Asignacion updateAsignacionProf(Asignacion asignacion) {
        return obtenerAsignacionDAO().updateAsignacionProf(asignacion);
    }

    public Asignacion updatePorcentaje(Asignacion asignacion) {
        return obtenerAsignacionDAO().updatePorcentaje(asignacion);
    }

    private AsignacionDAO obtenerAsignacionDAO() {
        return new AsignacionDAO();
    }

}
