/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.services;

import co.edu.udea.evdo.bl.EncuestaBL;
import co.edu.udea.evdo.dto.Encuesta;
import java.io.Serializable;
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
@Path("encuestas")
public class EncuestaService implements Serializable{
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Encuesta> getEncuestas(){
        return EncuestaBL.getInstance().getEncuestas();
    }
        
    @Path("poblar")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String poblarEncuestas(){
        return EncuestaBL.getInstance().poblarEncuestas();        
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Encuesta addEncuesta(Encuesta encuesta){
        return EncuestaBL.getInstance().addEncuesta(encuesta);
    }
}
