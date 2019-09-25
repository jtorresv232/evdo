/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.bl;

import co.edu.udea.evdo.dao.impl.PreguntaTotalDAO;
import co.edu.udea.evdo.dto.Asignacion;
import co.edu.udea.evdo.dto.Pregunta;
import co.edu.udea.evdo.dto.Resultados;
import co.edu.udea.evdo.dto.TotalPregunta;
import co.edu.udea.evdo.services.AsignacionService;
import co.edu.udea.evdo.services.PreguntaService;
import co.edu.udea.evdo.ws.EncuestaClient;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;
import org.apache.commons.collections.IteratorUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author JonathanTorresVelez
 */
public class PreguntaTotalBL implements Serializable{
    static final Logger logger = Logger.getLogger(PreguntaTotalBL.class);
    private static PreguntaTotalBL singletonInstance = new PreguntaTotalBL();
    private String metadato;
    
    public PreguntaTotalBL() {
        // empty constructor
    }
    
    public static PreguntaTotalBL getInstance() {
        synchronized (PreguntaTotalBL.class) {
            if( singletonInstance == null) {
                singletonInstance = new PreguntaTotalBL();
            }
        }
        return singletonInstance;
    }
    
    public void calcular() {
        EncuestaClient cliente = new EncuestaClient();
        Resultados[] resultados = cliente.getResultados();
        Collection<Asignacion> asignaciones = (Collection<Asignacion>)new AsignacionService().getAsignaciones(1, 5700, 0, 0, "ninguno", new Asignacion()).getEntity();
        Collection<Pregunta> preguntas = (Collection<Pregunta>) new PreguntaService().getPreguntas().getEntity();
        TotalPregunta total;
        Asignacion asignacion;
        Iterator<Asignacion> iteratorAsig = asignaciones.iterator();
        ListIterator<Pregunta> iteratorPreg = IteratorUtils.toListIterator(preguntas.iterator());
        Resultados[] result;
        double promedio = 0;
        double desviacion = 0;
        double coeficienteDesv = 0;
            logger.debug("asignaciones: " + asignaciones.size());
            logger.debug("preguntas: " + preguntas.size());
            int i = 0;
        while (iteratorAsig.hasNext()) {
            i++;
            asignacion = iteratorAsig.next();
            this.metadato = String.valueOf(asignacion.getSemestre()) + "-"
                    + asignacion.getMateria() + "-"
                    + asignacion.getGrupo() + "-"
                    + asignacion.getCedula();
            result = Arrays.stream(resultados).filter(
                    x -> x.getMetadato().equalsIgnoreCase(this.metadato))
                    .toArray(Resultados[]::new);
            logger.debug("COUNT:  " + i);
            logger.debug("METADATO:  " + this.metadato);
            while (iteratorPreg.hasNext() && result.length > 0) {
                int pregunta_numero = iteratorPreg.next().getNumero();
                logger.debug("PREGUNTA: " + pregunta_numero);
                Resultados[] res = Arrays.stream(result).filter(x
                        -> x.getNumero() == pregunta_numero)
                        .toArray(Resultados[]::new);
                promedio = cliente.getAverage(res);
                logger.debug("pre promedio: " + promedio);
                if(!Double.isNaN(promedio)) {
                    desviacion = cliente.getStdDev(res, promedio);
                coeficienteDesv = desviacion / Math.abs(promedio);
                total = new TotalPregunta(pregunta_numero);
                total.setCedula(asignacion.getCedula());
                total.setCoeficienteDesv(coeficienteDesv);
                total.setDesviacion(desviacion);
                total.setEvaluacion("eval20162");
                total.setGrupo(asignacion.getGrupo());
                total.setMateria(asignacion.getMateria());
                total.setMedia(promedio);
                total.setNumero(pregunta_numero);
                total.setSemestre(asignacion.getSemestre());
                addPreguntaTotal(total);
                }
            }
            //reset iterator preguntas
            while (iteratorPreg.hasPrevious()) {
                iteratorPreg.previous();
            }
        }
    }
    
    public TotalPregunta addPreguntaTotal(TotalPregunta totalPregunta) {
        return obtenerPreguntaTotalDAO().addTotal(totalPregunta);
    }
    
    private PreguntaTotalDAO obtenerPreguntaTotalDAO() {
        return new PreguntaTotalDAO();
    }
}
