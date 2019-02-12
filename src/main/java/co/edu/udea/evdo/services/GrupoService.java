/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.services;

import co.edu.udea.evdo.bl.GrupoBL;
import co.edu.udea.evdo.dto.Grupo;
import co.edu.udea.evdo.exceptions.DataNotFoundException;
import java.io.Serializable;
import java.util.Collection;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Jonathan
 */
@Path("/grupos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GrupoService implements Serializable{
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGrupos(){
        GenericEntity<Collection<Grupo>> entity;
        entity = new GenericEntity<Collection<Grupo>> (GrupoBL.getInstance().getGrupos()){};
        if(entity.getEntity().isEmpty()) {
            throw new DataNotFoundException("No hay ningún grupo");
        }
        return Response.ok()
                .entity(entity)
                .build();
    }
}
