/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.services;

import co.edu.udea.evdo.bl.TemaBL;
import co.edu.udea.evdo.dto.Tema;
import java.util.Collection;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Jonathan
 */
@Path("temas")
public class TemaService {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Tema> getTemas(){
        return TemaBL.getInstance().getTemas();
    }
    
    @Path("poblar")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public void poblarTemas(){
        TemaBL.getInstance().poblarTemas();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Tema addTema(Tema tema){
        return TemaBL.getInstance().addTema(tema);
    }
}
