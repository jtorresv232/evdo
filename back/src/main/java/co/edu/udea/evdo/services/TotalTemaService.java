/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.services;

import co.edu.udea.evdo.bl.TotalTemaBL;
import co.edu.udea.evdo.dto.TotalTema;
import co.edu.udea.evdo.exceptions.DataNotFoundException;
import co.edu.udea.evdo.exceptions.SuccessMessage;
import java.io.Serializable;
import java.util.Collection;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Jonathan
 */
@Path("totalTemas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TotalTemaService implements Serializable{
    @GET
    public Response getTotalTemas(){
        GenericEntity<Collection<TotalTema>> entity;
        entity = new GenericEntity<Collection<TotalTema>> (TotalTemaBL.getInstance().getTotalTemas()){};
        if(entity.getEntity().isEmpty()) {
            throw new DataNotFoundException("No hay ningún tema");
        }
        return Response.ok()
                .entity(entity)
                .build();
    }
    
    @Path("calcular")
    @GET
    public Response calcularTotal(){
        TotalTemaBL.getInstance().calcularTotalTema();
        GenericEntity<SuccessMessage> entity;
        entity = new GenericEntity<SuccessMessage> (new SuccessMessage("Se han calculado todos los totales por temas")){};
        return Response.ok()
                .entity(entity)
                .build();
    }
    
    @Path("por-programa/{programa}")
    @GET
    public Response getTotalTemaPorPrograma(@PathParam("programa") long programa) {
        GenericEntity<Collection<TotalTema>> entity;
        System.out.println(programa);
        entity = new GenericEntity<Collection<TotalTema>> (TotalTemaBL.getInstance().getTotalTemaPorPrograma(programa)){};
        System.out.println(entity.getEntity().size());
        if(entity.getEntity().isEmpty()) {
            System.out.println("no hay nada");
            throw new DataNotFoundException("No hay ningún curso evaluado en el programa " + programa);
        }
        return Response.ok()
                .entity(entity)
                .build();
    }
    
    @Path("por-docente/{cedula}")
    @GET
    public Response getTotalTemaPorDocente(@PathParam("cedula") String cedula) {
        GenericEntity<Collection<TotalTema>> entity;
        entity = new GenericEntity<Collection<TotalTema>> (TotalTemaBL.getInstance().getTotalTemaPorDocente(cedula)){};
        if(entity.getEntity().isEmpty()) {
            throw new DataNotFoundException("No hay ningún curso evaluado para el docente con cedula " + cedula);
        }
        return Response.ok()
                .entity(entity)
                .build();
    }
    
    @POST
    public Response addTotalTema(TotalTema totalTema){
        GenericEntity<TotalTema> entity;
        entity = new GenericEntity<TotalTema> (TotalTemaBL.getInstance().addTotalTema(totalTema)){};
        if(entity.getEntity() == null) {
            throw new DataNotFoundException("No se ha podido agregar el total");
        }
        return Response.ok()
                .entity(entity)
                .build();
    }
}
