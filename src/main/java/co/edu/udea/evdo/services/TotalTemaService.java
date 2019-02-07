/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.services;

import co.edu.udea.evdo.bl.TotalTemaBL;
import co.edu.udea.evdo.dto.TotalTema;
import java.io.Serializable;
import java.util.Collection;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Jonathan
 */
@Path("totalTemas")
public class TotalTemaService implements Serializable{
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<TotalTema> getTotalTemas(){
        return TotalTemaBL.getInstance().getTotalTemas();
    }
    
    @Path("calcular")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String calcularTotal(){
        TotalTemaBL.getInstance().calcularTotalTema();
        return "aprobado";
    }
    
    @Path("por-programa/{programa}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Collection<TotalTema> getTotalTemaPorPrograma(@PathParam("programa") long programa) {
        return TotalTemaBL.getInstance().getTotalTemaPorPrograma(programa);
    }
    
    @Path("por-docente/{cedula}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Collection<TotalTema> getTotalTemaPorDocente(@PathParam("cedula") String cedula) {
        return TotalTemaBL.getInstance().getTotalTemaPorDocente(cedula);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public TotalTema addTotalTema(TotalTema totalTema){
        return TotalTemaBL.getInstance().addTotalTema(totalTema);
    }
}
