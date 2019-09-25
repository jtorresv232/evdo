/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.exceptions;

import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;

/**
 *
 * @author Jonathan
 */
public class EvdoSQLException extends SQLSyntaxErrorException{
    private static final long serialVersionUID = 1L;
    
    public EvdoSQLException() {
        super("Ha ocurrido un error consultando la base de datos");
    }
}
