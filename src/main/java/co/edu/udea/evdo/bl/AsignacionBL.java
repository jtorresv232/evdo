/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.bl;

import co.edu.udea.evdo.dao.impl.AsignacionDAO;
import co.edu.udea.evdo.dto.Asignacion;
import co.edu.udea.evdo.dto.Grupo;
import co.edu.udea.evdo.dto.ws.DocenteMateriaGrupo;
import co.edu.udea.evdo.dto.ws.FacultadMares;
import co.edu.udea.evdo.dto.ws.MateriaMares;
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

    public Collection<Asignacion> getAsignaciones(int page, int size, Asignacion asig) {
        return obtenerAsignacionDAO().getAsignaciones(page, size, asig);

    }

    public int getAsigTotal(Asignacion asig) {
        return obtenerAsignacionDAO().getTotalAsigs(asig);
    }

    public Asignacion addAsignacion(Asignacion asignacion) {
        return obtenerAsignacionDAO().addAsignacion(asignacion);
    }

    public void poblarAsignaciones() {
        MaresService ms = new MaresService();
        Collection<FacultadMares> facultades = ms.consultaFacultades();
        Collection<MateriaMares> materias;
        Collection<DocenteMateriaGrupo> docentes;
        Asignacion asignacion;
        Grupo grupo;
        GrupoBL gbl = new GrupoBL();
        for (FacultadMares facultad : facultades) {
            materias = ms.consultaMaterias(Long.toString(facultad.getCodigo()));
            for (MateriaMares materia : materias) {
                for (int i = 1; i <= 10; i++) {
                    docentes = ms.consultaDocentes(Long.toString(materia.getMateria()), Integer.toString(i));
                    for (DocenteMateriaGrupo docente : docentes) {
                        asignacion = new Asignacion();
                        asignacion.setSemestre(20162);
                        asignacion.setMateria(materia.getMateria());
                        asignacion.setGrupo(i);
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
                        grupo.setSemestre(asignacion.getSemestre());
                        grupo.setMateria(asignacion.getMateria());
                        grupo.setGrupo(asignacion.getGrupo());
                        Random rand = new Random();
                        int num = rand.nextInt(20) + 15;
                        grupo.setNumEstudiantes(num);
                        gbl.addGrupo(grupo);

                    }
                }
            }
        }

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
