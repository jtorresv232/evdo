/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.services;

import co.edu.udea.evdo.bl.PreguntaBL;
import co.edu.udea.evdo.dto.Pregunta;
import co.edu.udea.evdo.exceptions.DataNotFoundException;
import co.edu.udea.evdo.exceptions.SuccessMessage;
import java.io.Serializable;
import java.util.Collection;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Jonathan
 */
@Path("preguntas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PreguntaService implements Serializable{
    
    @GET
    public Response getPreguntas(){
        GenericEntity<Collection<Pregunta>> entity;
        entity = new GenericEntity<Collection<Pregunta>> (PreguntaBL.getInstance().getPreguntas()){};
        if(entity.getEntity().isEmpty()) {
            throw new DataNotFoundException("No hay ninguna evaluación");
        }
        return Response.ok()
                .entity(entity)
                .build();
    }
    
    @POST
    public Response addPregunta(Pregunta pregunta){
        GenericEntity<Pregunta> entity;
        entity = new GenericEntity<Pregunta> (PreguntaBL.getInstance().addPregunta(pregunta)){};
        if(entity.getEntity() == null) {
            throw new DataNotFoundException("No se ha podido agregar la pregunta");
        }
        return Response.ok()
                .entity(entity)
                .build();
    }
    
    @Path("poblar")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response poblarPreguntas(){
        PreguntaBL.getInstance().poblarPreguntas();
        GenericEntity<SuccessMessage> entity;
        entity = new GenericEntity<SuccessMessage> (new SuccessMessage("Se han poblado todas las preguntas basadas en el sistema de encuestas")){};
        return Response.ok()
                .entity(entity)
                .build();
    }
}
