/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.services;

import co.edu.udea.evdo.bl.ProgramaBL;
import co.edu.udea.evdo.dto.Programa;
import java.io.Serializable;
import java.util.Collection;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Jonathan
 */
@Path("programas")
public class ProgramaService implements Serializable{
    
    @Path("poblar")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String poblarProgramas(){
        ProgramaBL.getInstance().poblarProgramaes();
        return "aprobado";
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Programa> getProgramas(){
        return ProgramaBL.getInstance().getProgramas();
    }
    
    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Programa getProgramaPorId(@PathParam("id") long programa){
        return ProgramaBL.getInstance().getProgramaPorId(programa);
    }

    @Path("numeros")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Programa getNumeros(@QueryParam("programa")long programa){
        return ProgramaBL.getInstance().getNumeros(programa);
    }
    
}
