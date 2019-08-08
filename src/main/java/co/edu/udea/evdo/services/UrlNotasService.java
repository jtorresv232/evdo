/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.services;

import co.edu.udea.evdo.bl.UrlNotasBL;
import co.edu.udea.evdo.dto.UrlNotas;
import co.edu.udea.evdo.exceptions.DataNotFoundException;
import java.io.Serializable;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author JonathanTorresVelez
 */
@Path("url")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UrlNotasService implements Serializable{
    @Context HttpServletRequest request;
    
    @GET
    @Path("{semestre}/{materia}/{grupo}/{cedula}")
    public Response getDatos(@PathParam("semestre") long semestre,
            @PathParam("materia") long materia,
            @PathParam("grupo") int grupo,
            @PathParam("cedula") String cedula) {
        String ipAddress = request.getRemoteAddr();
        System.out.println(ipAddress);
        GenericEntity<UrlNotas> entity;
        entity = new GenericEntity<UrlNotas> (UrlNotasBL.getInstance().getDatos(semestre, materia, grupo, cedula)){};
        if(entity.getEntity().getEncuesta() == null) {
            throw new DataNotFoundException("No hay ningún asignación que corresponda a los datos buscados");
        }
        return Response.ok()
                .entity(entity)
                .build();
    }
    
    public static String getDatosJsp(@PathParam("semestre") long semestre,
            @PathParam("materia") long materia,
            @PathParam("grupo") int grupo) {
        String texto = UrlNotasBL.getInstance().getDatosJsp(semestre, materia, grupo);
        return texto;
    }
    
    @GET
    @Path("{semestre}/{materia}/{grupo}")
    public Response getDatos(@PathParam("semestre") long semestre,
            @PathParam("materia") long materia,
            @PathParam("grupo") int grupo) {
        String ipAddress = request.getRemoteAddr();
        GenericEntity<Collection<UrlNotas>> entity;
        entity = new GenericEntity<Collection<UrlNotas>> (UrlNotasBL.getInstance().getDatosPorGrupo(semestre, materia, grupo)){};
        if(entity.getEntity().isEmpty()) {
            throw new DataNotFoundException("La busqueda de encuestas de esta asignación no ha arrojado resultados");
        }
        return Response.ok()
                .entity(entity)
                .build();
    }
    
    public static String getTodos(@PathParam("string") String string) {
        String entity = UrlNotasBL.getInstance().getAllData(string);
        return entity;
    }
}
