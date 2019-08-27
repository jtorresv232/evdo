/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.bl;

import co.edu.udea.evdo.dto.UsuarioSesion;
import co.edu.udea.evdo.util.UtilidadesSesion;

import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 * Clase de la Lógica del negocio para Usuarios
 *
 * @author edevargas
 */
public class FiltroBL implements Serializable {
    
    
    static final Logger logger = Logger.getLogger(FiltroBL.class);

    private static final long serialVersionUID = 4044273845736534004L;

    private static FiltroBL singletonInstance = new FiltroBL();

    private FiltroBL() {
    }

    public static FiltroBL getInstance() {
        synchronized (FiltroBL.class) {
            if (singletonInstance == null) { // Double checked
                singletonInstance = new FiltroBL();
            }
        }
        return singletonInstance;
    }

    /**
     * Replacing the stream object with the same instance object, to maintain
     * singleton.
     *
     * @return
     */
    protected Object readResolve() {
        return getInstance();
    }

    /**
     * Método para obtener todas los usuarios
     *
     * @param httpRequest
     * @return
     */
    public static UsuarioSesion obtenerDatosUsuarioSesion(HttpServletRequest httpRequest)  {
        
        UsuarioSesion usuarioSesion = new UsuarioSesion();

        try {

            UtilidadesSesion utilidadesSesion = new UtilidadesSesion();

            usuarioSesion = utilidadesSesion.obtenerDatosUsuarioSesion(httpRequest);
            
        } catch (Exception ex) {
            FiltroBL.logger.error(ex);
        }

        return usuarioSesion;
    }

}