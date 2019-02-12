/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.services;

import co.edu.udea.evdo.bl.TotalPreguntaBL;
import co.edu.udea.evdo.dto.TotalPregunta;
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
@Path("totalPreguntas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TotalPreguntaService implements Serializable{
    
    @GET
    public Collection<TotalPregunta> getTotalPreguntas(){
        return TotalPreguntaBL.getInstance().getTotalPreguntas();
    }
    
    @Path("calcular")
    @GET
    public Response calcularTotal(){
        TotalPreguntaBL.getInstance().calcularTotalPregunta();
        GenericEntity<SuccessMessage> entity;
        entity = new GenericEntity<SuccessMessage> (new SuccessMessage("Se han calculado todos los totales por pregunta")){};
        return Response.ok()
                .entity(entity)
                .build();
    }
    
    @Path("por-programa/{programa}")
    @GET
    public Response getTotalPreguntasPorPrograma(@PathParam("programa") long programa) {
        GenericEntity<Collection<TotalPregunta>> entity;
        entity = new GenericEntity<Collection<TotalPregunta>> (TotalPreguntaBL.getInstance().getTotalPreguntasPorPrograma(programa)){};
        if(entity.getEntity().isEmpty()) {
            throw new DataNotFoundException("No hay ningún curso evaluado en el programa " + programa);
        }
        return Response.ok()
                .entity(entity)
                .build();
    }
    
    @Path("por-docente/{cedula}")
    @GET
    public Response getTotalPreguntasPorDocente(@PathParam("cedula") String cedula) {
        GenericEntity<Collection<TotalPregunta>> entity;
        entity = new GenericEntity<Collection<TotalPregunta>> (TotalPreguntaBL.getInstance().getTotalPreguntasPorDocente(cedula)){};
        if(entity.getEntity().isEmpty()) {
            throw new DataNotFoundException("No hay ningún curso evaluado del profesor con cedula" + cedula);
        }
        return Response.ok()
                .entity(entity)
                .build();
    }
    
    @POST
    public Response addTotalPregunta(TotalPregunta total){
        GenericEntity<TotalPregunta> entity;
        entity = new GenericEntity<TotalPregunta> (TotalPreguntaBL.getInstance().addTotalPregunta(total)){};
        if(entity.getEntity() == null) {
            throw new DataNotFoundException("No se ha podido agregar el total");
        }
        return Response.ok()
                .entity(entity)
                .build();
    }
}
