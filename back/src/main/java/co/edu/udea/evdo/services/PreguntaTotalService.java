/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.services;

import co.edu.udea.evdo.bl.PreguntaTotalBL;
import co.edu.udea.evdo.exceptions.SuccessMessage;
import java.io.Serializable;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author JonathanTorresVelez
 */
@Path("totales")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PreguntaTotalService implements Serializable{
    
    @GET
    public Response calcular(){
        PreguntaTotalBL.getInstance().calcular();
        GenericEntity<SuccessMessage> entity;
        entity = new GenericEntity<SuccessMessage> (new SuccessMessage("Se han calculado todos los totales por pregunta")){};
        return Response.ok()
                .entity(entity)
                .build();
    }
}
