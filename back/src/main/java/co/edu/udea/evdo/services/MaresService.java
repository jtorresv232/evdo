/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.services;

import co.edu.udea.evdo.dto.Grupo;
import co.edu.udea.evdo.dto.ws.CalendarioPrograma;
import co.edu.udea.evdo.dto.ws.DocenteMateriaGrupo;
import co.edu.udea.evdo.dto.ws.EstudianteMatriculado;
import co.edu.udea.evdo.dto.ws.FacultadMares;
import co.edu.udea.evdo.dto.ws.MateriaMares;
import co.edu.udea.evdo.dto.ws.ProgramaMares;
import co.edu.udea.exception.OrgSistemasSecurityException;
import co.edu.udea.wsClient.OrgSistemasWebServiceClient;
import java.util.Collection;
import java.util.LinkedList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;

/**
 *
 * @author Jonathan
 */
@Path("mares")
public class MaresService {
    
    static final Logger logger = Logger.getLogger(MaresService.class);

    //private static final String TOKEN = "25f9e794323b453885f5181f1b624d0b";
    private static final String TOKEN = "25f9e794323b453885f5181f1b624d0b";

    @GET //Cambiar a tipo post
    @Path("programas")
    @Produces(MediaType.APPLICATION_JSON)
    public static Collection<ProgramaMares> consultaProgramas() {

        Collection<ProgramaMares> listaPrograsmaDeRol = new LinkedList<>();

        try {
            OrgSistemasWebServiceClient wsClient = new OrgSistemasWebServiceClient(true);

            listaPrograsmaDeRol = wsClient.obtenerBean("consultaprogramasmares", TOKEN, ProgramaMares.class);
            int cuenta = 0;
            for (ProgramaMares programa : listaPrograsmaDeRol) {
                if (programa.getEstado().equalsIgnoreCase("FUNC")) {
                    cuenta = cuenta + 1;
                }
            }
        } catch (OrgSistemasSecurityException ex) {
            return listaPrograsmaDeRol;
        }

        return listaPrograsmaDeRol;
    }

    @GET //Cambiar a tipo post
    @Path("facultades")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<FacultadMares> consultaFacultades() {

        Collection<FacultadMares> listaPrograsmaDeRol = new LinkedList<>();
        try {
            OrgSistemasWebServiceClient wsClient = new OrgSistemasWebServiceClient(true);

            listaPrograsmaDeRol = wsClient.obtenerBean("consultafacultadesmares", TOKEN, FacultadMares.class);
        } catch (OrgSistemasSecurityException ex) {
            return listaPrograsmaDeRol;
        }
        return listaPrograsmaDeRol;
    }

    @GET //Cambiar a tipo post
    @Path("materias")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<MateriaMares> consultaMaterias(@QueryParam("facultad") String facultad) {

        Collection<MateriaMares> listaPrograsmaDeRol = new LinkedList<>();
        try {
            OrgSistemasWebServiceClient wsClient = new OrgSistemasWebServiceClient(true);
            wsClient.addParam("facultad", facultad);
            //wsClient.addParam("programa", "504");
            wsClient.addParam("tiposPrograma", "DOCTORAD,ESPECIAL,MAESTRIA,PREGRADO");
            listaPrograsmaDeRol = wsClient.obtenerBean("consultarmateriasprogramaversion", TOKEN, MateriaMares.class);
            listaPrograsmaDeRol.removeIf(x -> x.getIndicadorVersionActual().equalsIgnoreCase("N"));
        } catch (OrgSistemasSecurityException ex) {
            return listaPrograsmaDeRol;
        }

        return listaPrograsmaDeRol;
    }

    @GET //Cambiar a tipo post
    @Path("progs")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<ProgramaMares> consultaProgramasXMateria(@QueryParam("materia") String materia) {

        Collection<ProgramaMares> listaPrograsmaDeRol = new LinkedList<>();

        try {
            OrgSistemasWebServiceClient wsClient = new OrgSistemasWebServiceClient(true);
            wsClient.addParam("codigoMateria", materia);
            //wsClient.addParam("programa", "504");
            listaPrograsmaDeRol = wsClient.obtenerBean("consultarprogramasdemateriamares", TOKEN, ProgramaMares.class);
        } catch (OrgSistemasSecurityException ex) {
            return listaPrograsmaDeRol;
        }

        return listaPrograsmaDeRol;
    }

    @GET //Cambiar a tipo post
    @Path("docentes")
    public Collection<DocenteMateriaGrupo> consultaDocentes(@QueryParam("materia") String materia, @QueryParam("grupo") String grupo, @QueryParam("semestre") String semestre) {
        Collection<DocenteMateriaGrupo> listaPrograsmaDeRol = new LinkedList<>();

        try {
            OrgSistemasWebServiceClient wsClient = new OrgSistemasWebServiceClient(true);
            wsClient.addParam("periodo", semestre);
            wsClient.addParam("codigoMateria", materia);
            wsClient.addParam("grupo", grupo);
            listaPrograsmaDeRol = wsClient.obtenerBean("consultardocentesmateriagrupomares", TOKEN, DocenteMateriaGrupo.class);
        } catch (OrgSistemasSecurityException ex) {
            return listaPrograsmaDeRol;
        }

        return listaPrograsmaDeRol;
    }

    @GET
    @Path("estudiantes/{materia}/{grupo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<EstudianteMatriculado> getEstudiantes(@PathParam("materia") String materia, @PathParam("grupo") String grupo) {
        Collection<EstudianteMatriculado> listaEstudiantes = new LinkedList<>();
        System.out.println(listaEstudiantes.size());
        try {
            OrgSistemasWebServiceClient wsClient = new OrgSistemasWebServiceClient(true);
            wsClient.addParam("materia", materia);
            wsClient.addParam("grupo", grupo);
            listaEstudiantes = wsClient.obtenerBean("consultaestudiantesmatriculadosmares", TOKEN, EstudianteMatriculado.class);
        } catch (OrgSistemasSecurityException ex) {
            System.err.println(ex);
        }

        return listaEstudiantes;
    }
    
    @GET
    @Path("/calendario")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<CalendarioPrograma> getCalendario(@QueryParam("semestre") long semestre,@QueryParam("programa") long programa) {
        Collection<CalendarioPrograma> listaCalendarios = new LinkedList<>();
        try{
            OrgSistemasWebServiceClient wsClient = new OrgSistemasWebServiceClient(true);
            wsClient.addParam("semestre", String.valueOf(semestre));
            wsClient.addParam("programa", String.valueOf(programa));
            listaCalendarios = wsClient.obtenerBean("consultarcalendarioprograma", TOKEN, CalendarioPrograma.class);
        } catch (OrgSistemasSecurityException ex) {
            System.err.println(ex);
            logger.error(ex);
        }
        return listaCalendarios;
    }

}
