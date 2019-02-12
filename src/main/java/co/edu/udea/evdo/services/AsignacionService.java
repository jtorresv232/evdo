/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.services;

import co.edu.udea.evdo.bl.AsignacionBL;
import co.edu.udea.evdo.dto.Asignacion;
import co.edu.udea.evdo.exceptions.DataNotFoundException;
import co.edu.udea.evdo.exceptions.SuccessMessage;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Collection;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.quartz.SchedulerException;

/**
 *
 * @author Jonathan
 */
@Path("/asignaciones")
public class AsignacionService implements Serializable {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAsignaciones(@QueryParam("page") int page, @QueryParam("size") int size, Asignacion asig) {
        GenericEntity<Collection<Asignacion>> entity;
        entity = new GenericEntity<Collection<Asignacion>> (AsignacionBL.getInstance().getAsignaciones(page, size, asig)){};
        if(entity.getEntity().isEmpty()) {
            throw new DataNotFoundException("No hay asignaciones por mostrar");
        }
        return Response.ok()
                .entity(entity)
                .build();
    }

    @Path("total")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public int getAsigTotal(Asignacion asig) {
        return AsignacionBL.getInstance().getAsigTotal(asig);
    }

    @PUT
    @Path("/{idAsignacion}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAsignacion(Asignacion asignacion) throws SchedulerException, ParseException {
        GenericEntity<Asignacion> entity;
        entity = new GenericEntity<Asignacion> (AsignacionBL.getInstance().updateAsignacion(asignacion)){};
        if(entity.getEntity().getSemestre() == 0) {
            throw new DataNotFoundException("No hay asignaciones por mostrar");
        }
        return Response.ok()
                .entity(entity)
                .build();
    }
    
    @PUT
    @Path("/updateProf/{idAsignacion}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAsignacionProf(Asignacion asignacion) {
        GenericEntity<Asignacion> entity;
        entity = new GenericEntity<Asignacion> (AsignacionBL.getInstance().updateAsignacionProf(asignacion)){};
        if(entity.getEntity() == null) {
            throw new DataNotFoundException("No hay asignaciones por mostrar");
        }
        return Response.ok()
                .entity(entity)
                .build();
    }

    @PUT
    @Path("/porcentaje")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePorcentaje(Asignacion asignacion) {
        GenericEntity<Asignacion> entity;
        entity = new GenericEntity<Asignacion> (AsignacionBL.getInstance().updatePorcentaje(asignacion)){};
        if(entity.getEntity() == null) {
            throw new DataNotFoundException("No hay asignaciones por mostrar");
        }
        return Response.ok()
                .entity(entity)
                .build();
    }

    @Path("/poblar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response poblarAsignaciones() {
        AsignacionBL.getInstance().poblarAsignaciones();
        GenericEntity<SuccessMessage> entity;
        entity = new GenericEntity<SuccessMessage> (new SuccessMessage("Se ha ejecutado el procedimiento correctamente")){};
        return Response.ok()
                .entity(entity)
                .build();
        
    }
}
