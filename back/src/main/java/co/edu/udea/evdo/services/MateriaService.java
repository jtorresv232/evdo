/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.services;

import co.edu.udea.evdo.bl.MateriaBL;
import co.edu.udea.evdo.dto.Materia;
import co.edu.udea.evdo.exceptions.DataNotFoundException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

/**
 *
 * @author Jonathan
 */
@Path("/materias")
public class MateriaService implements Serializable{
    
    @GET
    public Response obtenerMaterias(@QueryParam("semestre") long semestre, @QueryParam("programa") long programa) throws SQLException{
        GenericEntity<Collection<Materia>> entity;
        entity = new GenericEntity<Collection<Materia>> (MateriaBL.getInstance().getMaterias(semestre, programa)){};
        if(entity.getEntity().isEmpty()) {
            throw new DataNotFoundException("No se han encontrado materias en el programa con código " + programa + " en el semestre " + semestre);
        }
        return Response.ok()
                .entity(entity)
                .build();
    }
}
