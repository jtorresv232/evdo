/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.services;

import co.edu.udea.evdo.bl.GrupoBL;
import co.edu.udea.evdo.dto.Grupo;
import java.io.Serializable;
import java.util.Collection;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Jonathan
 */
@Path("/grupos")
public class GrupoService implements Serializable{
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Grupo> getGrupos(){
        return GrupoBL.getInstance().getGrupos();
    }
}
