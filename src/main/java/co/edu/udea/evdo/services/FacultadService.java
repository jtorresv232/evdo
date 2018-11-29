/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.services;

import co.edu.udea.evdo.bl.FacultadBL;
import co.edu.udea.evdo.dto.Facultad;
import java.io.Serializable;
import java.util.Collection;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Jonathan
 */
@Path("facultades")
public class FacultadService implements Serializable{
    
    @Path("poblar")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String poblarFacultades(){
        FacultadBL.getInstance().poblarFacultades();
        return "aprobado";
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Facultad> getFacultades(){
        return FacultadBL.getInstance().getFacultades();
    }
    
    @PUT
    @Path("/{idFacultad}") 
    @Consumes(MediaType.APPLICATION_JSON)
    public String updateFacultad(Facultad facultad){
        System.out.println("servicio");
        return FacultadBL.getInstance().updateFacultad(facultad);
    }
}
