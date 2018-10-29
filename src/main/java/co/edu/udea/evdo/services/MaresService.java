/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.services;

import co.edu.udea.evdo.dto.Grupo;
import co.edu.udea.evdo.dto.ws.DocenteMateriaGrupo;
import co.edu.udea.evdo.dto.ws.FacultadMares;
import co.edu.udea.evdo.dto.ws.MateriaMares;
import co.edu.udea.evdo.dto.ws.ProgramaMares;
import co.edu.udea.evdo.properties.Properties;
import co.edu.udea.evdo.util.ConstantesStatic;
import co.edu.udea.exception.OrgSistemasSecurityException;
import co.edu.udea.wsClient.OrgSistemasWebServiceClient;
import co.edu.udea.wsClient.dto.OrgSistemasWSRequest;
import java.util.Collection;
import java.util.LinkedList;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Jonathan
 */
@Path("mares")
public class MaresService {
    
    @GET //Cambiar a tipo post
    @Path("programas")
    @Produces(MediaType.APPLICATION_JSON)
    public static Collection<ProgramaMares> consultaProgramas() {

        OrgSistemasWSRequest result;
        Collection<ProgramaMares> listaPrograsmaDeRol = new LinkedList<>();
        String token;
        boolean esDesarrollo;

        try {
            OrgSistemasWebServiceClient wsClient = new OrgSistemasWebServiceClient(true);

            listaPrograsmaDeRol = wsClient.obtenerBean("consultaprogramasmares", "5facbdd992ecd3e667df2b544e22a80a8274fd59", ProgramaMares.class);

            /*for (OrgSistemasWSRequestObject o : result.getValues()) {

                // programaDeRol = new MaresProgramaDeRol();
                listaPrograsmaDeRol.setIdentificacionUsuario(o.get("identificacionUsuario").toString());
                listaPrograsmaDeRol.setPrograma(o.get("programa").toString());
                listaPrograsmaDeRol.setNombrePrograma(o.get("nombrePrograma").toString());
                listaPrograsmaDeRol.setTipoPrograma(o.get("tipoPrograma").toString());
                listaPrograsmaDeRol.setSede(o.get("sede").toString());
                listaPrograsmaDeRol.setFacultad(new Long(o.get("facultad").toString()));
                listaPrograsmaDeRol.setNombreFacultad(o.get("nombreFacultad").toString());
                listaPrograsmaDeRol.setCreditosGrado(new Long(o.get("creditosGrado").toString()));
                listaPrograsmaDeRol.setEstado(o.get("estado").toString());
                listaPrograsmaDeRol.setVersionActual(new Long(o.get("versionActual").toString()));
            }*/
            int cuenta = 0;
            for(ProgramaMares programa: listaPrograsmaDeRol){
                if (programa.getEstado().equalsIgnoreCase("FUNC")) {
                    System.out.println(programa.getNombrePrograma());
                    cuenta=cuenta+1;
                }
            }
            System.out.println(cuenta);
        } catch (OrgSistemasSecurityException ex) {
            return listaPrograsmaDeRol;
        }

        return listaPrograsmaDeRol;
    }
    
    @GET //Cambiar a tipo post
    @Path("facultades")
    @Produces(MediaType.APPLICATION_JSON)
    public static Collection<FacultadMares> consultaFacultades() {

        OrgSistemasWSRequest result;
        Collection<FacultadMares> listaPrograsmaDeRol = new LinkedList<>();
        String token;
        boolean esDesarrollo;
        

        try {
            OrgSistemasWebServiceClient wsClient = new OrgSistemasWebServiceClient(true);

            listaPrograsmaDeRol = wsClient.obtenerBean("consultafacultadesmares", "5facbdd992ecd3e667df2b544e22a80a8274fd59", FacultadMares.class);
        } catch (OrgSistemasSecurityException ex) {
            return listaPrograsmaDeRol;
        }
        return listaPrograsmaDeRol;
    }
    
    
    @GET //Cambiar a tipo post
    @Path("materias")
    @Produces(MediaType.APPLICATION_JSON)
    public static Collection<MateriaMares> consultaMaterias(@QueryParam("facultad")String facultad) {

        OrgSistemasWSRequest result;
        Collection<MateriaMares> listaPrograsmaDeRol = new LinkedList<>();
        String token;
        boolean esDesarrollo;

        try {
            OrgSistemasWebServiceClient wsClient = new OrgSistemasWebServiceClient(true);
            wsClient.addParam("facultad", facultad);
            //wsClient.addParam("programa", "504");
            wsClient.addParam("tiposPrograma", "DOCTORAD,ESPECIAL,MAESTRIA");
            listaPrograsmaDeRol = wsClient.obtenerBean("consultarmateriasprogramaversion", "5facbdd992ecd3e667df2b544e22a80a8274fd59", MateriaMares.class);
            int cuenta = 0;
            listaPrograsmaDeRol.removeIf(x -> x.getIndicadorVersionActual().equalsIgnoreCase("N"));
            /*for(MateriaMares materia: listaPrograsmaDeRol){
                if (!materia.getIndicadorVersionActual().equalsIgnoreCase("N")) {
                    System.out.println(materia.getNombreMateria());
                    cuenta=cuenta+1;
                }
            }
            System.out.println(cuenta);*/
        } catch (OrgSistemasSecurityException ex) {
            return listaPrograsmaDeRol;
        }

        return listaPrograsmaDeRol;
    }
    
    @GET //Cambiar a tipo post
    @Path("progs")
    @Produces(MediaType.APPLICATION_JSON)
    public static Collection<ProgramaMares> consultaProgramasXMateria(@QueryParam("materia")String materia) {

        OrgSistemasWSRequest result;
        Collection<ProgramaMares> listaPrograsmaDeRol = new LinkedList<>();
        String token;
        boolean esDesarrollo;

        try {
            OrgSistemasWebServiceClient wsClient = new OrgSistemasWebServiceClient(true);
            wsClient.addParam("dodigoMateria", materia);
            //wsClient.addParam("programa", "504");
            listaPrograsmaDeRol = wsClient.obtenerBean("consultarprogramasdemateriamares", "5facbdd992ecd3e667df2b544e22a80a8274fd59", ProgramaMares.class);
            
            /*for(MateriaMares materia: listaPrograsmaDeRol){
                if (!materia.getIndicadorVersionActual().equalsIgnoreCase("N")) {
                    System.out.println(materia.getNombreMateria());
                    cuenta=cuenta+1;
                }
            }
            System.out.println(cuenta);*/
        } catch (OrgSistemasSecurityException ex) {
            return listaPrograsmaDeRol;
        }

        return listaPrograsmaDeRol;
    }
    
    @GET //Cambiar a tipo post
    @Path("docentes")
    public Collection<DocenteMateriaGrupo> consultaDocentes(@QueryParam("materia")String materia, @QueryParam("grupo")String grupo){
        OrgSistemasWSRequest result;
        Collection<DocenteMateriaGrupo> listaPrograsmaDeRol = new LinkedList<>();
        String token;
        boolean esDesarrollo;

        try {
            OrgSistemasWebServiceClient wsClient = new OrgSistemasWebServiceClient(true);
            wsClient.addParam("periodo", "20162");
            wsClient.addParam("codigoMateria", materia);
            wsClient.addParam("grupo", grupo);
            listaPrograsmaDeRol = wsClient.obtenerBean("consultardocentesmateriagrupomares", "5facbdd992ecd3e667df2b544e22a80a8274fd59", DocenteMateriaGrupo.class);
        } catch (OrgSistemasSecurityException ex) {
            return listaPrograsmaDeRol;
        }

        return listaPrograsmaDeRol;
    }
    
    @GET
    @Path("estudiantes")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Grupo> getEstudiantes(){
        OrgSistemasWSRequest result;
        Collection<Grupo> listaPrograsmaDeRol = new LinkedList<>();
        String token;
        boolean esDesarrollo;

        try {
            OrgSistemasWebServiceClient wsClient = new OrgSistemasWebServiceClient(true);
            wsClient.addParam("materia", "2508107");
            //wsClient.addParam("grupo", "1");
            listaPrograsmaDeRol = wsClient.obtenerBean("consultaestudiantesmatriculadosmares", "5facbdd992ecd3e667df2b544e22a80a8274fd59", Grupo.class);
        } catch (OrgSistemasSecurityException ex) {
            return listaPrograsmaDeRol;
        }

        return listaPrograsmaDeRol;
    }

}
