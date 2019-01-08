/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.bl;

import co.edu.udea.evdo.dao.impl.PreguntaDAO;
import co.edu.udea.evdo.dto.Pregunta;
import co.edu.udea.evdo.ws.EncuestaClient;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Jonathan
 */
public class PreguntaBL implements Serializable {

    private static PreguntaBL singletonInstance = new PreguntaBL();

    public PreguntaBL() {
        // empty constructor
    }

    public static PreguntaBL getInstance() {
        synchronized (PreguntaBL.class) {
            if (singletonInstance == null) {
                singletonInstance = new PreguntaBL();
            }
        }
        return singletonInstance;
    }

    public Collection<Pregunta> getPreguntas() {
        return obtenerPreguntaDAO().getPreguntas();

    }

    public Pregunta addPregunta(Pregunta pregunta) {
        return obtenerPreguntaDAO().addPregunta(pregunta);
    }

    public boolean preguntaExiste(int numero) {
        return obtenerPreguntaDAO().preguntaExiste(numero);
    }

    public void poblarPreguntas() {
        List<Pregunta> temas = new EncuestaClient().obtenerPregutnas();
        Iterator<Pregunta> iterator = temas.iterator();
        Pregunta pregunta;
        while (iterator.hasNext()) {
            pregunta = iterator.next();
            addPregunta(pregunta);
        }

    }

    private PreguntaDAO obtenerPreguntaDAO() {
        return new PreguntaDAO();
    }

}
