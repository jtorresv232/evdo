/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.services;

import co.edu.udea.evdo.bl.EncuestaBL;
import co.edu.udea.evdo.dto.Encuesta;
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
@Path("encuestas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EncuestaService implements Serializable{
    
    @GET
    public Response getEncuestas(){
        GenericEntity<Collection<Encuesta>> entity;
        entity = new GenericEntity<Collection<Encuesta>> (EncuestaBL.getInstance().getEncuestas()){};
        if(entity.getEntity().isEmpty()) {
            throw new DataNotFoundException("No hay ningún comentario");
        }
        return Response.ok()
                .entity(entity)
                .build();
    }
        
    @Path("poblar")
    @GET
    public Response poblarEncuestas(){
        EncuestaBL.getInstance().poblarEncuestas();
        GenericEntity<SuccessMessage> entity;
        entity = new GenericEntity<SuccessMessage> (new SuccessMessage("Se han poblado todas las encuestas con base en las del sistema de encuestas")){};
        return Response.ok()
                .entity(entity)
                .build();
                
    }
    
    @POST
    public Response addEncuesta(Encuesta encuesta){
        GenericEntity<Encuesta> entity;
        entity = new GenericEntity<Encuesta> (EncuestaBL.getInstance().addEncuesta(encuesta)){};
        if(entity.getEntity() == null) {
            throw new DataNotFoundException("No se ha podido agregar la encuesta");
        }
        return Response.ok()
                .entity(entity)
                .build();
    }
}
