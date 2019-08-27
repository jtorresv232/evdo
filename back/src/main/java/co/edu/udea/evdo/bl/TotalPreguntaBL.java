/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.bl;

import co.edu.udea.evdo.dto.Resultados;
import co.edu.udea.evdo.dao.impl.TotalPreguntaDAO;
import co.edu.udea.evdo.dto.Asignacion;
import co.edu.udea.evdo.dto.Pregunta;
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
 * @author Jonathan
 */
public class TotalPreguntaBL implements Serializable {

    static final Logger logger = Logger.getLogger(TotalPreguntaBL.class);

    private static TotalPreguntaBL singletonInstance = new TotalPreguntaBL();
    private String metadato;

    public TotalPreguntaBL() {
        // empty constructor
    }

    public static TotalPreguntaBL getInstance() {
        synchronized (TotalPreguntaBL.class) {
            if (singletonInstance == null) {
                singletonInstance = new TotalPreguntaBL();
            }
        }
        return singletonInstance;
    }

    public Collection<TotalPregunta> getTotalPreguntas() {
        return obtenerTotalPreguntaDAO().getTotalPreguntas();
    }
    
    public Collection<TotalPregunta> getTotalPreguntasPorPrograma(long programa) {
        return obtenerTotalPreguntaDAO().getTotalesPorPrograma(programa);
    }
    
    public Collection<TotalPregunta> getTotalPreguntasPorDocente (String cedula) {
        return obtenerTotalPreguntaDAO().getTotalesPorDocente(cedula);
    }

    public TotalPregunta addTotalPregunta(TotalPregunta totalPregunta) {
        return obtenerTotalPreguntaDAO().addTotalPregunta(totalPregunta);
    }

    public void calcularTotalPregunta() {
        logger.debug("1");
        EncuestaClient cliente = new EncuestaClient();
        Resultados[] resultados = cliente.getResultados();
        Collection<Asignacion> asignaciones = (Collection<Asignacion>)new AsignacionService().getAsignaciones(1, 5700, 0, new Asignacion()).getEntity();
        Collection<Pregunta> preguntas = (Collection<Pregunta>) new PreguntaService().getPreguntas().getEntity();
        TotalPregunta total;
        Asignacion asignacion;
        Iterator<Asignacion> iteratorAsig = asignaciones.iterator();
        ListIterator<Pregunta> iteratorPreg = IteratorUtils.toListIterator(preguntas.iterator());
        Resultados[] result;
        double promedio = 0;
        double desviacion = 0;
        double coeficienteDesv = 0;
            System.out.println("asignaciones: " + asignaciones.size());
            System.out.println("preguntas: " + preguntas.size());
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
            System.out.println("COUNT:  " + i);
            System.out.println("METADATO:  " + this.metadato);
            while (iteratorPreg.hasNext() && result.length > 0) {
                int pregunta_numero = iteratorPreg.next().getNumero();
                System.out.println("PREGUNTA: " + pregunta_numero);
                Resultados[] res = Arrays.stream(result).filter(x
                        -> x.getNumero() == pregunta_numero)
                        .toArray(Resultados[]::new);
                promedio = cliente.getAverage(res);
                System.out.println("pre promedio: " + promedio);
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
                //total.setNumero(pregunta);
                total.setSemestre(asignacion.getSemestre());
                //addTotalPregunta(total);
                }
            }
            //reset iterator preguntas
            while (iteratorPreg.hasPrevious()) {
                iteratorPreg.previous();
            }
        }
    }

    public void obtenerTotales() {
        Resultados[] resultados = new EncuestaClient().getResultados();
        Collection<Pregunta> preguntas = (Collection<Pregunta>) new PreguntaService().getPreguntas();
        logger.debug(resultados.length);
        logger.debug(preguntas.size());
    }

    private TotalPreguntaDAO obtenerTotalPreguntaDAO() {
        return new TotalPreguntaDAO();
    }

}
