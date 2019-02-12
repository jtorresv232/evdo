/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.services;

import co.edu.udea.evdo.bl.ComentarioBL;
import co.edu.udea.evdo.dto.Comentario;
import co.edu.udea.evdo.dto.Correo;
import co.edu.udea.evdo.exceptions.DataNotFoundException;
import co.edu.udea.evdo.exceptions.SuccessMessage;
import co.edu.udea.exception.OrgSistemasSecurityException;
import java.io.Serializable;
import java.util.Collection;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Jonathan
 */
@Path("/comentarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ComentarioService implements Serializable{
    
    @POST
    @Path("/obtener")
    public Response getComentarios(Comentario comentario){
        GenericEntity<Collection<Comentario>> entity;
        entity = new GenericEntity<Collection<Comentario>> (ComentarioBL.getInstance().getComentarios(comentario)){};
        if(entity.getEntity().isEmpty()) {
            throw new DataNotFoundException("No hay ningún comentario");
        }
        return Response.ok()
                .entity(entity)
                .build();
    }
    
    @POST
    public Response addComentario(Comentario comentario) throws OrgSistemasSecurityException{
        GenericEntity<Comentario> entity;
        entity = new GenericEntity<Comentario> (ComentarioBL.getInstance().addComentario(comentario)){};
        if(entity.getEntity() == null) {
            throw new DataNotFoundException("No se ha podido agregar ningún comentario");
        }
        return Response.ok()
                .entity(entity)
                .build();
    }
    
    @Path("/notificar")
    @POST
    public Response notificar(Correo inCorreo) throws OrgSistemasSecurityException{
        GenericEntity<SuccessMessage> entity;
        entity = new GenericEntity<SuccessMessage> (new SuccessMessage("Se enviado el correo a la siguiente dirección " + inCorreo.getDestinatario())){};
        return Response.ok()
                .entity(entity)
                .build();
    }
}
