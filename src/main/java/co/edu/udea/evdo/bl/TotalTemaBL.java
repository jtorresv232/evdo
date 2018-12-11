/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.bl;

import co.edu.udea.evdo.dto.Resultados;
import co.edu.udea.evdo.dao.impl.TotalTemaDAO;
import co.edu.udea.evdo.dto.Asignacion;
import co.edu.udea.evdo.dto.Tema;
import co.edu.udea.evdo.dto.TotalTema;
import co.edu.udea.evdo.services.AsignacionService;
import co.edu.udea.evdo.services.TemaService;
import co.edu.udea.evdo.ws.EncuestaClient;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;
import org.apache.commons.collections.IteratorUtils;
import java.util.Arrays;


/**
 *
 * @author Jonathan
 */
public class TotalTemaBL implements Serializable{
    private static TotalTemaBL singletonInstance = new TotalTemaBL();
    private String metadato;
    private int tema;

    public TotalTemaBL() {
    }
        
        public static TotalTemaBL getInstance() {
        synchronized (TotalTemaBL.class) {
            if (singletonInstance == null) { 
                singletonInstance = new TotalTemaBL();
            }
        }
        return singletonInstance;
    }
        
        public Collection<TotalTema> getTotalTemas(){
            return obtenerTotalTemaDAO().getTotalTemas();
        
        }
        
        public TotalTema addTotalTema(TotalTema totalTema){
            return obtenerTotalTemaDAO().addTotalTema(totalTema);
        }
        
        
        public void calcularTotalTema(){
            EncuestaClient cliente = new EncuestaClient();
            Resultados[] resultados = cliente.getResultados();
            Collection<Asignacion> asignaciones = new AsignacionService().getAsignaciones(1, 4, new Asignacion());
            Collection<Tema> preguntas = new TemaService().getTemas();
            TotalTema total;
            Asignacion asignacion;
            Iterator<Asignacion> iteratorAsig = asignaciones.iterator();
            ListIterator<Tema> iteratorTema = IteratorUtils.toListIterator(preguntas.iterator());
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
                while(iteratorTema.hasNext() && result.length > 0){
                    this.tema = iteratorTema.next().getTema();
                    Resultados[] res = Arrays.stream(result).filter( x ->
                             x.getTema()== this.tema)
                            .toArray(Resultados[]::new);
                    promedio = cliente.getAverage(res);
                    desviacion = cliente.getStdDev(res, promedio);
                    coeficiente_desv = desviacion / Math.abs(promedio);
                    total = new TotalTema();
                    total.setCedula(asignacion.getCedula());
                    total.setCoeficiente_desv(coeficiente_desv);
                    total.setDesviacion(desviacion);
                    total.setEvaluacion("eval20162");
                    total.setGrupo(asignacion.getGrupo());
                    total.setMateria(asignacion.getMateria());
                    total.setMedia(promedio);
                    total.setCodigo_tema(this.tema);
                    total.setSemestre(asignacion.getSemestre());
                    addTotalTema(total);
                }
                //reset iterator preguntas
                while(iteratorTema.hasPrevious()){
                    iteratorTema.previous();
                }
            }
        }
        
        private TotalTemaDAO obtenerTotalTemaDAO() {
        TotalTemaDAO DAO = new TotalTemaDAO();
        return DAO;
    }

}
