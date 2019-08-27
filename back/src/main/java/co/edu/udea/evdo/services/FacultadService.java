/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.services;

import co.edu.udea.evdo.bl.FacultadBL;
import co.edu.udea.evdo.dto.Facultad;
import co.edu.udea.evdo.exceptions.DataNotFoundException;
import co.edu.udea.evdo.exceptions.SuccessMessage;
import java.io.Serializable;
import java.util.Collection;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
@Path("facultades")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FacultadService implements Serializable {

    @Path("poblar")
    @GET
    public Response poblarFacultades() {
        FacultadBL.getInstance().poblarFacultades();
        GenericEntity<SuccessMessage> entity;
        entity = new GenericEntity<SuccessMessage> (new SuccessMessage("Se han poblado todas las facultades basadas en las existentes en mares")){};
        return Response.ok()
                .entity(entity)
                .build();
    }

    @GET
    public Response getFacultades() {
        GenericEntity<Collection<Facultad>> entity;
        entity = new GenericEntity<Collection<Facultad>> (FacultadBL.getInstance().getFacultades()){};
        if(entity.getEntity().isEmpty()) {
            throw new DataNotFoundException("No hay ninguna evaluación");
        }
        return Response.ok()
                .entity(entity)
                .build();
    }

    @PUT
    @Path("/{idFacultad}")
    public Response updateFacultad(Facultad facultad) {
        FacultadBL.getInstance().updateFacultad(facultad);
        GenericEntity<SuccessMessage> entity;
        entity = new GenericEntity<SuccessMessage> (new SuccessMessage("Se actualizado la facultad")){};
        return Response.ok()
                .entity(entity)
                .build();
    }
}
