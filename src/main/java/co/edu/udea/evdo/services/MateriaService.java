/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.services;

import co.edu.udea.evdo.bl.MateriaBL;
import co.edu.udea.evdo.dto.Materia;
import java.io.Serializable;
import java.util.Collection;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Jonathan
 */
@Path("/materias")
public class MateriaService implements Serializable{
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Materia> obtenerMaterias(@QueryParam("semestre") long semestre, @QueryParam("programa") long programa){
        return MateriaBL.getInstance().getMaterias(semestre, programa);
    }
}
