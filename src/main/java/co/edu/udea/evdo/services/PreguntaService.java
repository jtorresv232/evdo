/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.services;

import co.edu.udea.evdo.bl.PreguntaBL;
import co.edu.udea.evdo.dto.Pregunta;
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
@Path("preguntas")
public class PreguntaService implements Serializable{
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Pregunta> getPreguntas(){
        return PreguntaBL.getInstance().getPreguntas();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Pregunta addPregunta(Pregunta pregunta){
        return PreguntaBL.getInstance().addPregunta(pregunta);
    }
    
    @Path("poblar")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String poblarPreguntas(){
        PreguntaBL.getInstance().poblarPreguntas();
        return "aprobado";
    }
}
