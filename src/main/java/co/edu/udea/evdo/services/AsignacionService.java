/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.services;

import co.edu.udea.evdo.bl.AsignacionBL;
import co.edu.udea.evdo.dto.Asignacion;
import java.io.Serializable;
import java.util.Collection;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Jonathan
 */
@Path("/asignaciones")
public class AsignacionService implements Serializable{
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Asignacion> getAsignaciones(){
        return AsignacionBL.getInstance().getAsignaciones();
    }
    
    @PUT
    @Path("/{idAsignacion}") 
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Asignacion updateAsignacion(Asignacion asignacion){
        return AsignacionBL.getInstance().updateAsignacion(asignacion);
    }
    
    @PUT
    @Path("/porcentaje") 
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Asignacion updatePorcentaje(Asignacion asignacion){
        return AsignacionBL.getInstance().updatePorcentaje(asignacion);
    }
}
