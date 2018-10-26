/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.services;

import co.edu.udea.evdo.bl.EvaluacionBL;
import co.edu.udea.evdo.dto.Evaluacion;
import java.io.Serializable;
import java.util.Collection;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Jonathan
 */
@Path("/evaluacion")
public class EvaluacionService implements Serializable{
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Evaluacion> getEvaluaciones(){
        return EvaluacionBL.getInstance().getEvaluaciones();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Evaluacion addEvaluacion(Evaluacion evaluacion){
        return EvaluacionBL.getInstance().addEvaluacion(evaluacion);
    }
    
    @PUT
    @Path("/{codigoEvaluacion}") 
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean updateEvaluacion(Evaluacion evaluacion){
        return EvaluacionBL.getInstance().updateEvaluacion(evaluacion);
    }
    }
