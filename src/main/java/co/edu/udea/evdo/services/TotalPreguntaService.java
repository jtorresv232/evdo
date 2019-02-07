/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.services;

import co.edu.udea.evdo.bl.TotalPreguntaBL;
import co.edu.udea.evdo.dto.TotalPregunta;
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
@Path("totalPreguntas")
public class TotalPreguntaService implements Serializable{
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<TotalPregunta> getTotalPreguntas(){
        return TotalPreguntaBL.getInstance().getTotalPreguntas();
    }
    
    @Path("calcular")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String calcularTotal(){
        TotalPreguntaBL.getInstance().calcularTotalPregunta();
        return "aprobado";
    }
    
    @Path("por-programa/{programa}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Collection<TotalPregunta> getTotalPreguntasPorPrograma(@PathParam("programa") long programa) {
        return TotalPreguntaBL.getInstance().getTotalPreguntasPorPrograma(programa);
    }
    
    @Path("por-docente/{cedula}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Collection<TotalPregunta> getTotalPreguntasPorDocente(@PathParam("cedula") String cedula) {
        return TotalPreguntaBL.getInstance().getTotalPreguntasPorDocente(cedula);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public TotalPregunta addTotalPregunta(TotalPregunta total){
        return TotalPreguntaBL.getInstance().addTotalPregunta(total);
    }
}
