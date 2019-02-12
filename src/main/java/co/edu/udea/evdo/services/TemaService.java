/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.services;

import co.edu.udea.evdo.bl.TemaBL;
import co.edu.udea.evdo.dto.Tema;
import co.edu.udea.evdo.exceptions.DataNotFoundException;
import co.edu.udea.evdo.exceptions.SuccessMessage;
import co.edu.udea.evdo.util.Notifications;
import java.text.ParseException;
import java.util.Collection;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.quartz.SchedulerException;

/**
 *
 * @author Jonathan
 */
@Path("temas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TemaService {
    
    @GET
    public Response getTemas(){
        GenericEntity<Collection<Tema>> entity;
        entity = new GenericEntity<Collection<Tema>> (TemaBL.getInstance().getTemas()){};
        if(entity.getEntity().isEmpty()) {
            throw new DataNotFoundException("No hay ningún tema");
        }
        return Response.ok()
                .entity(entity)
                .build();
    }
    
    
    @Path("poblar")
    @GET
    public Response poblarTemas(){
        TemaBL.getInstance().poblarTemas();
        GenericEntity<SuccessMessage> entity;
        entity = new GenericEntity<SuccessMessage> (new SuccessMessage("Se han poblado todos los temas con base en las del sistema de encuestas")){};
        return Response.ok()
                .entity(entity)
                .build();
    }
    
    @POST
    public Tema addTema(Tema tema){
        return TemaBL.getInstance().addTema(tema);
    }
    
    @Path("notificar")
    @GET
    public void notificar() throws SchedulerException, ParseException{
        Notifications.getInstance().notificar();
    }
}
