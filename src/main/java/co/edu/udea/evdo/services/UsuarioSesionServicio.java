/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.services;

import co.edu.udea.evdo.bl.FiltroBL;
import co.edu.udea.evdo.dto.UsuarioSesion;
import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/filtro")
public class UsuarioSesionServicio implements Serializable {

    /*private transient @Context HttpServletRequest  httpRequest;

    public HttpServletRequest getHttpRequest() {
        return httpRequest;
    }*/
        
    private final long serialVersionUID = -9066585482051897942L;
      
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UsuarioSesion obtenerDatosFiltro(@Context HttpServletRequest request) {
        System.out.println((String) request.getSession().getAttribute("userDataUdeA"));
        return FiltroBL.obtenerDatosUsuarioSesion(request);
    }       
}
