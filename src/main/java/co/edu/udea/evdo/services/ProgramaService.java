/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.services;

import co.edu.udea.evdo.bl.ProgramaBL;
import java.io.Serializable;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
}
