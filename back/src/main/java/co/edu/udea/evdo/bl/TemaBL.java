/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.bl;

import co.edu.udea.evdo.dao.impl.TemaDAO;
import co.edu.udea.evdo.dto.Tema;
import co.edu.udea.evdo.ws.EncuestaClient;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Jonathan
 */
public class TemaBL implements Serializable {

    private static TemaBL singletonInstance = new TemaBL();

    public TemaBL() {
        // empty constructor
    }

    public static TemaBL getInstance() {
        synchronized (TemaBL.class) {
            if (singletonInstance == null) {
                singletonInstance = new TemaBL();
            }
        }
        return singletonInstance;
    }

    public static Collection<Tema> getTemas() {
        return obtenerTemaDAO().getTemas();

    }

    public Tema addTema(Tema tema) {
        return obtenerTemaDAO().addTema(tema);
    }

    public boolean temaExiste(int tema) {
        return obtenerTemaDAO().temaExiste(tema);
    }

    public void poblarTemas() {
        List<Tema> temas = new EncuestaClient().obtenerTemas();
        Iterator<Tema> iterator = temas.iterator();
        Tema tema;
        while (iterator.hasNext()) {
            tema = iterator.next();
            addTema(tema);
        }

    }

    private static TemaDAO obtenerTemaDAO() {
        return new TemaDAO();
    }

}
