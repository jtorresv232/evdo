/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.services;

import co.edu.udea.evdo.bl.ProgramaBL;
import co.edu.udea.evdo.dto.Programa;
import co.edu.udea.evdo.exceptions.DataNotFoundException;
import co.edu.udea.evdo.exceptions.SuccessMessage;
import java.io.Serializable;
import java.util.Collection;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Jonathan
 */
@Path("programas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProgramaService implements Serializable{
    
    @Path("poblar")
    @GET
    public Response poblarProgramas(){
        ProgramaBL.getInstance().poblarProgramaes();
        GenericEntity<SuccessMessage> entity;
        entity = new GenericEntity<SuccessMessage> (new SuccessMessage("Se han poblado todas las encuestas con base en las del sistema de encuestas")){};
        return Response.ok()
                .entity(entity)
                .build();
    }
    
    @GET
    public Response getProgramas(){
        GenericEntity<Collection<Programa>> entity;
        entity = new GenericEntity<Collection<Programa>> (ProgramaBL.getInstance().getProgramas()){};
        if(entity.getEntity().isEmpty()) {
            throw new DataNotFoundException("No hay ningún programa");
        }
        return Response.ok()
                .entity(entity)
                .build();
    }
    
    @GET
    @Path("/{id}")
    public Response getProgramaPorId(@PathParam("id") long programa){
        GenericEntity<Programa> entity;
        entity = new GenericEntity<Programa> (ProgramaBL.getInstance().getProgramaPorId(programa)){};
        if(entity.getEntity() == null) {
            throw new DataNotFoundException("el programa con código " + programa + " no existe");
        }
        return Response.ok()
                .entity(entity)
                .build();
    }

    @Path("numeros")
    @GET
    public Response getNumeros(@QueryParam("programa")long programa){
        GenericEntity<Programa> entity;
        entity = new GenericEntity<Programa> (ProgramaBL.getInstance().getNumeros(programa)){};
        if(entity.getEntity() == null) {
            throw new DataNotFoundException("el programa con código " + programa + " no existe");
        }
        return Response.ok()
                .entity(entity)
                .build();
    }
    
}
