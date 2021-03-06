/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.bl;

import co.edu.udea.evdo.dao.impl.AsignacionDAO;
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
import org.apache.log4j.Logger;

/**
 *
 * @author Jonathan
 */
public class TotalTemaBL implements Serializable {
    
    static final Logger logger = Logger.getLogger(TotalTemaBL.class);

    private static TotalTemaBL singletonInstance = new TotalTemaBL();
    private String metadato;
    private int tema;

    public TotalTemaBL() {
        // empty constructor
    }

    public static TotalTemaBL getInstance() {
        synchronized (TotalTemaBL.class) {
            if (singletonInstance == null) {
                singletonInstance = new TotalTemaBL();
            }
        }
        return singletonInstance;
    }

    public Collection<TotalTema> getTotalTemas() {
        return obtenerTotalTemaDAO().getTotalTemas();

    }

    public TotalTema addTotalTema(TotalTema totalTema) {
        return obtenerTotalTemaDAO().addTotalTema(totalTema);
    }
    
    public Collection<TotalTema> getTotalTemaPorPrograma(long programa) {
        return obtenerTotalTemaDAO().getTotalesPorPrograma(programa);
    }
    
    public Collection<TotalTema> getTotalTemaPorDocente(String cedula) {
        return obtenerTotalTemaDAO().getTotalesPorDocente(cedula);
    }

    public void calcularTotalTema() {
        try{
            EncuestaClient cliente = new EncuestaClient();
        Resultados[] resultados = cliente.getResultados();
        Collection<Asignacion> asignaciones = (Collection<Asignacion>)new AsignacionService().getAsignaciones(1, 5700, 0, 0, "ninguno", new Asignacion()).getEntity();
        Collection<Tema> preguntas = (Collection<Tema>)new TemaService().getTemas().getEntity();
        TotalTema total;
        Asignacion asignacion;
        Iterator<Asignacion> iteratorAsig = asignaciones.iterator();
        ListIterator<Tema> iteratorTema = IteratorUtils.toListIterator(preguntas.iterator());
        Resultados[] result;
        double promedio = 0;
        double desviacion = 0;
        double coeficienteDesv = 0;
            System.out.println("asignaciones: " + asignaciones.size());
            System.out.println("temas: " + preguntas.size());
        while (iteratorAsig.hasNext()) {
            asignacion = iteratorAsig.next();
            this.metadato = String.valueOf(asignacion.getSemestre()) + "-"
                    + asignacion.getMateria() + "-"
                    + asignacion.getGrupo() + "-"
                    + asignacion.getCedula();
            result = Arrays.stream(resultados).filter(
                    x -> x.getMetadato().equalsIgnoreCase(this.metadato))
                    .toArray(Resultados[]::new);
            while (iteratorTema.hasNext() && result.length > 0) {
                this.tema = iteratorTema.next().getTema();
                Resultados[] res = Arrays.stream(result).filter(x
                        -> x.getTema() == this.tema)
                        .toArray(Resultados[]::new);
                
                promedio = cliente.getAverage(res);
                System.out.println("promedio: " + promedio);
                if(!Double.isNaN(promedio) ) {
                  desviacion = cliente.getStdDev(res, promedio);
                coeficienteDesv = desviacion / Math.abs(promedio);
                total = new TotalTema();
                total.setCedula(asignacion.getCedula());
                total.setCoeficienteDesv(coeficienteDesv);
                total.setDesviacion(desviacion);
                total.setEvaluacion("eval20162");
                total.setGrupo(asignacion.getGrupo());
                total.setMateria(asignacion.getMateria());
                total.setMedia(promedio);
                total.setCodigoTema(this.tema);
                total.setSemestre(asignacion.getSemestre());
                addTotalTema(total);
                }
            }
            //reset iterator preguntas
            while (iteratorTema.hasPrevious()) {
                iteratorTema.previous();
            }
        }
        }catch(Exception e){
            logger.debug(e);
        }
    }

    private TotalTemaDAO obtenerTotalTemaDAO() {
        return new TotalTemaDAO();
    }

}
