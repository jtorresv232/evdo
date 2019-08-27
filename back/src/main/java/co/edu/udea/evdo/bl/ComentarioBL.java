/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.bl;

import co.edu.udea.evdo.dao.impl.ComentarioDAO;
import co.edu.udea.evdo.dao.impl.FacultadDAO;
import co.edu.udea.evdo.dto.Comentario;
import co.edu.udea.evdo.dto.Correo;
import co.edu.udea.evdo.util.InfoCorreo;
import co.edu.udea.exception.OrgSistemasSecurityException;
import java.io.Serializable;
import java.time.Clock;
import java.util.Collection;
import org.apache.log4j.Logger;

/**
 *
 * @author Jonathan
 */
public class ComentarioBL implements Serializable {

    private static ComentarioBL singletonInstance = new ComentarioBL();
    static final Logger logger = Logger.getLogger(ComentarioBL.class);

    public ComentarioBL() {
        // empty constructor
    }

    public static ComentarioBL getInstance() {
        synchronized (ComentarioBL.class) {
            if (singletonInstance == null) {
                singletonInstance = new ComentarioBL();
            }
        }
        return singletonInstance;
    }

    public Collection<Comentario> getComentarios(Comentario comentario) {
        return obtenerComentarioDAO().getComentarios(comentario);

    }

    public Comentario addComentario(Comentario comentario) throws OrgSistemasSecurityException {
        return obtenerComentarioDAO().addComentario(comentario);
    }

    public String notificar(Correo inCorreo) throws OrgSistemasSecurityException {
        this.logger.debug("envio");
        try {
            InfoCorreo infoCorreo = new InfoCorreo();
            Correo correo = infoCorreo.enviarNotificacion(inCorreo);
            infoCorreo.enviar(correo, "EVALDOCOBSRESULTADOS");
            System.out.println("envio de correo");
        } catch (Exception e) {
            this.logger.debug(e);
            return "no enviado";
        }
        return "enviado";
    }

    private ComentarioDAO obtenerComentarioDAO() {
        return new ComentarioDAO();
    }

}
