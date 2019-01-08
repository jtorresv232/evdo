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
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Jonathan
 */
public class EncuestaClient {
    private static final String REST_URL
            = "http://172.19.0.204:9085/encuesta/webapi/";
    
    private Client client = ClientBuilder.newClient();
    
    public Resultados[] getResultados(){
        Response serviceResponse = client.target(REST_URL + "resultados").
                    request(MediaType.APPLICATION_JSON).get(Response.class);
        
        List<Resultados> resultados = serviceResponse.readEntity(new GenericType<List<Resultados>>(){});
        return resultados.toArray(new Resultados[resultados.size()]);
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
        Response serviceResponse = client.target(REST_URL + "temas").
                    request(MediaType.APPLICATION_JSON).get(Response.class);
        return serviceResponse.readEntity(new GenericType<List<Tema>>(){});
    }
    
    public List<Encuesta> obtenerEncuestas(){
        Response serviceResponse = client.target(REST_URL + "encuestas").
                    request(MediaType.APPLICATION_JSON).get(Response.class);
        return serviceResponse.readEntity(new GenericType<List<Encuesta>>(){});
    }
    
    public List<Pregunta> obtenerPregutnas(){
        Response serviceResponse = client.target(REST_URL + "preguntas").
                    request(MediaType.APPLICATION_JSON).get(Response.class);
        return serviceResponse.readEntity(new GenericType<List<Pregunta>>(){});
    }
}
