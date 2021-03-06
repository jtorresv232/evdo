/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.bl;

import co.edu.udea.evdo.dao.impl.EncuestaDAO;
import co.edu.udea.evdo.dto.Encuesta;
import co.edu.udea.evdo.ws.EncuestaClient;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Jonathan
 */
public class EncuestaBL implements Serializable {

    private static EncuestaBL singletonInstance = new EncuestaBL();

    public EncuestaBL() {
        // empty constructor
    }

    public static EncuestaBL getInstance() {
        synchronized (EncuestaBL.class) {
            if (singletonInstance == null) {
                singletonInstance = new EncuestaBL();
            }
        }
        return singletonInstance;
    }

    public Collection<Encuesta> getEncuestas() {
        return obtenerEncuestaDAO().getEncuestas();
    }

    public Encuesta addEncuesta(Encuesta encuesta) {
        return obtenerEncuestaDAO().addEncuesta(encuesta);
    }

    public String poblarEncuestas() {
        List<Encuesta> encuestas = new EncuestaClient().obtenerEncuestas();
        Iterator<Encuesta> iterator = encuestas.iterator();
        Encuesta encuesta;
        while (iterator.hasNext()) {
            encuesta = iterator.next();
            addEncuesta(encuesta);
        }
        return "aprobado";
    }

    private EncuestaDAO obtenerEncuestaDAO() {
        return new EncuestaDAO();
    }

}
