/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.util;

import co.edu.udea.evdo.dto.Correo;
import co.edu.udea.evdo.dto.ws.ResultadoCorreo;
import co.edu.udea.exception.OrgSistemasSecurityException;
import co.edu.udea.wsClient.OrgSistemasWebServiceClient;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Jonathan
 */
public class InfoCorreo {

    static final Logger logger = Logger.getLogger(InfoCorreo.class);

    public Correo enviarNotificacion(Correo inCorreo) {
        Correo correo = new Correo();
        correo.setDestinatario(inCorreo.getDestinatario());
        correo.setAsunto(inCorreo.getAsunto());
        correo.setCuerpo(inCorreo.getCuerpo());
        return correo;
    }

    public boolean enviar(Correo correo, String servicio) throws OrgSistemasSecurityException {
        try {
            OrgSistemasWebServiceClient wsClient = new OrgSistemasWebServiceClient(true);
            wsClient.addParam("destinatario", correo.getDestinatario());
            wsClient.addParam("encabezado", correo.getAsunto());
            wsClient.addParam("cuerpoHtml", correo.getCuerpo());
            wsClient.addParam("token", "5facbdd992ecd3e667df2b544e22a80a8274fd59");
            wsClient.addParam("servicio", "EVALDOCOBSRESULTADOS");

            List<ResultadoCorreo> lista = wsClient.obtenerBean(ConstantesStatic.WS_ENVIAR_CORREO, servicio, ResultadoCorreo.class);
            logger.debug(lista.size());
        } catch (Exception e) {
            logger.debug("Error al enviar el correo:  " + e);
            return false;
        }
        return true;
    }
}
