/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.services;

import co.edu.udea.evdo.bl.ComentarioBL;
import co.edu.udea.evdo.dto.Comentario;
import co.edu.udea.evdo.dto.Correo;
import co.edu.udea.exception.OrgSistemasSecurityException;
import java.io.Serializable;
import java.util.Collection;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Jonathan
 */
@Path("/comentarios")
public class ComentarioService implements Serializable{
    
    @POST
    @Path("/obtener")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Comentario> getComentarios(Comentario comentario){
        return ComentarioBL.getInstance().getComentarios(comentario);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Comentario addComentario(Comentario comentario) throws OrgSistemasSecurityException{
        return ComentarioBL.getInstance().addComentario(comentario);
    }
    
    @Path("notificar")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String notificar(Correo inCorreo) throws OrgSistemasSecurityException{
        return ComentarioBL.getInstance().notificar(inCorreo);
    }
}
