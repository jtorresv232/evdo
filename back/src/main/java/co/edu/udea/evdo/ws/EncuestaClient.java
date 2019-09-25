    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.ws;

import co.edu.udea.evdo.dto.Encuesta;
import co.edu.udea.evdo.dto.Pregunta;
import co.edu.udea.evdo.dto.Resultados;
import co.edu.udea.evdo.dto.Tema;
import co.edu.udea.evdo.dto.TotalTema;

import java.util.Collection;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
/**
 *
 * @author Jonathan
 */
public class EncuestaClient {
    private static final String REST_URL
            = "http://172.19.0.203:29000/encuesta/webapp/";
        
    private Client client = ClientBuilder.newClient(); 
    
    public Resultados[] getResultados(){
        System.out.println(MediaType.APPLICATION_JSON);
        try{
            WebTarget target = client.target(REST_URL + "resultados");
            
        Collection<Resultados> resultados = target.request(MediaType.APPLICATION_JSON)
                .get(new GenericType<Collection<Resultados>>(){});
            System.out.println("punto 2");
        return resultados.toArray(new Resultados[resultados.size()]);
        }catch(Exception e){
            System.err.println("hola" + e);
        }
        System.out.println("returning null");
        return null;
    }
    
    public double getAverage(Resultados[] resultados){
        double sum = 0;
        double avg;
        for(Resultados resu:resultados){
            sum += resu.getValor();
        }
        avg = sum / resultados.length;
        return avg;
    }
    
    public double getStdDev(Resultados[] resultados, double avg){
        double sum = 0;
        if(resultados.length > 1){
        for(Resultados resu: resultados){
            sum = sum + (resu.getValor()-avg)*(resu.getValor()-avg);
        }
            return Math.sqrt(sum / (resultados.length-1));
        }
        return sum;
    }
    
    
    public List<Tema> obtenerTemas(){
        return null;
    }
    
    public List<Encuesta> obtenerEncuestas(){
        return null;
    }
    
    public List<Pregunta> obtenerPregutnas(){
        return null;
    }
}
