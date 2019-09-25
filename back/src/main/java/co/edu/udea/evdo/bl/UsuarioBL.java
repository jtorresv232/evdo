/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.bl;

import co.edu.udea.evdo.dao.impl.UsuarioDAO;
import co.edu.udea.evdo.dto.Usuario;
import co.edu.udea.evdo.exceptions.EvdoSQLException;
import co.edu.udea.evdo.ws.EncuestaClient;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Jonathan
 */
public class UsuarioBL implements Serializable {

    private static UsuarioBL singletonInstance = new UsuarioBL();

    public UsuarioBL() {
        // empty constructor
    }

    public static UsuarioBL getInstance() {
        synchronized (UsuarioBL.class) {
            if (singletonInstance == null) {
                singletonInstance = new UsuarioBL();
            }
        }
        return singletonInstance;
    }

    public Collection<Usuario> getAllUsuarios() throws EvdoSQLException {
        return obtenerUsuarioDAO().getAllUsuarios();
    }
    
    public Usuario addUsuario(Usuario usuario) throws EvdoSQLException {
        return obtenerUsuarioDAO().addUsuario(usuario);
    }
    
    public Usuario findUser(String cedula) throws EvdoSQLException {
        return obtenerUsuarioDAO().findUser(cedula);
    }
    
    public void deleteUusuario (String cedula) {
        obtenerUsuarioDAO().deleteUsuario(cedula);
    }
 
    private UsuarioDAO obtenerUsuarioDAO() {
        return new UsuarioDAO();
    }

}
