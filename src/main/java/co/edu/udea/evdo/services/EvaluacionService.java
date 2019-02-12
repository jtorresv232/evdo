/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.services;

import co.edu.udea.evdo.bl.EvaluacionBL;
import co.edu.udea.evdo.dto.Evaluacion;
import co.edu.udea.evdo.exceptions.DataNotFoundException;
import co.edu.udea.evdo.exceptions.SuccessMessage;
import java.io.Serializable;
import java.util.Collection;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Jonathan
 */
@Path("/evaluacion")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EvaluacionService implements Serializable{
    
    @GET
    public Response getEvaluaciones(){
        GenericEntity<Collection<Evaluacion>> entity;
        entity = new GenericEntity<Collection<Evaluacion>> (EvaluacionBL.getInstance().getEvaluaciones()){};
        if(entity.getEntity().isEmpty()) {
            throw new DataNotFoundException("No hay ninguna evaluación");
        }
        return Response.ok()
                .entity(entity)
                .build();
    }
    
    @POST
    public Response addEvaluacion(Evaluacion evaluacion){
        GenericEntity<Evaluacion> entity;
        entity = new GenericEntity<Evaluacion> (EvaluacionBL.getInstance().addEvaluacion(evaluacion)){};
        if(entity.getEntity() == null) {
            throw new DataNotFoundException("No se ha podido agregar la encuesta");
        }
        return Response.ok()
                .entity(entity)
                .build();
    }
    
    @PUT
    @Path("/{codigoEvaluacion}")
    public Response updateEvaluacion(Evaluacion evaluacion){
        if(EvaluacionBL.getInstance().updateEvaluacion(evaluacion) == true) {
            GenericEntity<SuccessMessage> entity;
        entity = new GenericEntity<SuccessMessage> (new SuccessMessage("Se ha actualizado la evaluación")){};
        return Response.ok()
                .entity(entity)
                .build();
        } else {
            throw new DataNotFoundException("No se ha podido actualizar la evaluación");
        }
    }
    }
