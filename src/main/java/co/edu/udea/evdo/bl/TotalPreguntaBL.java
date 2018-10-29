/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.bl;

import co.edu.udea.encuesta.dto.Resultados;
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
import java.util.List;
import java.util.ListIterator;
import org.apache.commons.collections.IteratorUtils;

/**
 *
 * @author Jonathan
 */
public class TotalPreguntaBL implements Serializable{
    private static TotalPreguntaBL singletonInstance = new TotalPreguntaBL();
    private String metadato;
    private int pregunta;

    public TotalPreguntaBL() {
    }
        
        public static TotalPreguntaBL getInstance() {
        synchronized (TotalPreguntaBL.class) {
            if (singletonInstance == null) { 
                singletonInstance = new TotalPreguntaBL();
            }
        }
        return singletonInstance;
    }
        
        public Collection<TotalPregunta> getTotalPreguntas(){
            return obtenerTotalPreguntaDAO().getTotalPreguntas();
        
        }
        
        public TotalPregunta addTotalPregunta(TotalPregunta totalPregunta){
            return obtenerTotalPreguntaDAO().addTotalPregunta(totalPregunta);
        }
        
        public void calcularTotalPregunta(){
            EncuestaClient cliente = new EncuestaClient();
            Resultados[] resultados = cliente.getResultados();
            Collection<Asignacion> asignaciones = new AsignacionService().getAsignaciones();
            Collection<Pregunta> preguntas = new PreguntaService().getPreguntas();
            TotalPregunta total;
            Asignacion asignacion;
            Iterator<Asignacion> iteratorAsig = asignaciones.iterator();
            ListIterator<Pregunta> iteratorPreg = IteratorUtils.toListIterator(preguntas.iterator());
            Resultados[] result;
            double promedio = 0;
            double desviacion = 0;
            double coeficiente_desv = 0;
            
            while(iteratorAsig.hasNext()){
                asignacion = iteratorAsig.next();
                this.metadato = String.valueOf(asignacion.getSemestre())+"-"
                            +String.valueOf(asignacion.getMateria())+"-"
                            +String.valueOf(asignacion.getGrupo())+"-"
                            +asignacion.getCedula();
                result = Arrays.stream(resultados).filter(
                            x -> x.getMetadato().equalsIgnoreCase(this.metadato))
                            .toArray(Resultados[]::new);
                while(iteratorPreg.hasNext() && result.length > 0){
                    this.pregunta = iteratorPreg.next().getNumero();
                    Resultados[] res = Arrays.stream(result).filter( x ->
                             x.getNumero()== this.pregunta)
                            .toArray(Resultados[]::new);
                    promedio = cliente.getAverage(res);
                    desviacion = cliente.getStdDev(res, promedio);
                    coeficiente_desv = desviacion / Math.abs(promedio);
                    total = new TotalPregunta();
                    total.setCedula(asignacion.getCedula());
                    total.setCoeficiente_desv(coeficiente_desv);
                    total.setDesviacion(desviacion);
                    total.setEvaluacion("eval20162");
                    total.setGrupo(asignacion.getGrupo());
                    total.setMateria(asignacion.getMateria());
                    total.setMedia(promedio);
                    total.setNumero(this.pregunta);
                    total.setSemestre(asignacion.getSemestre());
                    addTotalPregunta(total);
                }
                //reset iterator preguntas
                while(iteratorPreg.hasPrevious()){
                    iteratorPreg.previous();
                }
            }
        }
        
        public void obtenerTotales(){
            Resultados[] resultados = new EncuestaClient().getResultados();
            Collection<Pregunta> preguntas = new PreguntaService().getPreguntas();
            
        }
        
        private TotalPreguntaDAO obtenerTotalPreguntaDAO() {
        TotalPreguntaDAO DAO = new TotalPreguntaDAO();
        return DAO;
    }

}
