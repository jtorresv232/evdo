/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.services;

import co.edu.udea.evdo.bl.UsuarioBL;
import co.edu.udea.evdo.dto.Usuario;
import co.edu.udea.evdo.exceptions.DataNotFoundException;
import co.edu.udea.evdo.exceptions.EvdoSQLException;
import java.util.Collection;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Jonathan
 */
@Path("usuarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioService {
    
    @GET
    public Response getAllUsuarios() throws EvdoSQLException {
        GenericEntity<Collection<Usuario>> entity;
        entity = new GenericEntity<Collection<Usuario>> (UsuarioBL.getInstance().getAllUsuarios()){};
        if(entity.getEntity().isEmpty()) {
            throw new DataNotFoundException("No hay ningún usuario");
        }
        return Response.ok()
                .entity(entity)
                .build();
    }
    
    @POST
    public Response addUsuario(Usuario usuario) throws EvdoSQLException {
        GenericEntity<Usuario> entity;
        entity = new GenericEntity<Usuario> (UsuarioBL.getInstance().addUsuario(usuario)){};
        if(entity.getEntity() == null) {
            throw new DataNotFoundException("El usuario no pudo ser agregado");
        } else {
            return Response.ok()
                    .entity(entity)
                    .build();
        }
    }
    
    @Path("/buscar/{cedula}")
    @GET
    public Response findUsuario (@PathParam("cedula") String cedula) throws EvdoSQLException {
        GenericEntity<Usuario> entity;
        entity = new GenericEntity<Usuario> (UsuarioBL.getInstance().findUser(cedula)){};
        if (entity.getEntity() == null) {
            throw new DataNotFoundException("El usuario con cedula " + cedula + "no existe");
        }else {
            return Response.ok()
                    .entity(entity)
                    .build();
        }
    }
    
}
