/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.exceptions;

import java.sql.SQLException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author USER
 */
@Provider
public class EvdoSQLExceptionMapper implements ExceptionMapper<SQLException>{

    @Override
    public Response toResponse(SQLException e) {
        ErrorMessage error = new ErrorMessage("Ha ocurrido un error consultando la base de datos", 500);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(error)
                .build();
    }
    
}
