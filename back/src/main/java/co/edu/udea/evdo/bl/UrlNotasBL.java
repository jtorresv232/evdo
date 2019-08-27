/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.bl;

import co.edu.udea.evdo.dao.impl.UrlNotasDAO;
import co.edu.udea.evdo.dto.UrlNotas;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.eclipse.persistence.internal.jpa.parsing.jpql.antlr.JPQLParser;

/**
 *
 * @author JonathanTorresVelez
 */
public class UrlNotasBL implements Serializable{
    private static UrlNotasBL singletonInstance = new UrlNotasBL();
    public static String resultado;
    
    public UrlNotasBL() {
        // empty constructor
    }
    
    public static UrlNotasBL getInstance() {
        synchronized (UrlNotasBL.class) {
            if (singletonInstance == null) {
                singletonInstance = new UrlNotasBL();
            }
        }
        return singletonInstance;
    }
    
    private UrlNotasDAO obtenerUrlNotasDAO() {
        return new UrlNotasDAO();
    }
    
    public UrlNotas getDatos(long semestre, long materia, int grupo, String cedula){
        return obtenerUrlNotasDAO().getDatos(semestre, materia, grupo, cedula);
    }
    
    public Collection<UrlNotas> getDatosPorGrupo(long semestre, long materia, int grupo) {
        return obtenerUrlNotasDAO().getDatosPorGrupo(semestre, materia, grupo);
    }
    
    public String getDatosJsp(long semestre, long materia, int grupo) {
        Collection<UrlNotas> lista = obtenerUrlNotasDAO().getDatosPorGrupo(semestre, materia, grupo);
        String resultado = "[";
        for (UrlNotas item :lista) {
            resultado = resultado + "{\"cedula\":\"" + item.getCedula() + "\",\"encuesta\":\"" + item.getEncuesta() + 
                    "\",\"punto\":" + item.getPunto() + ",\"metadato\":\"" + item.getMetadato() +  "\"},";
        }
        if( resultado.length() > 1) {
            resultado = resultado.substring(0, resultado.length()-1);
        }
        resultado = resultado + "]";
        return resultado;
    }
    
    public String getAllData(String datos) {
        List<Long> semestres = new ArrayList<>();
        Collection<Long> materias = new ArrayList<>();
        Collection<Integer> grupos = new ArrayList<>();
        //Collection<String> profesores = new ArrayList<>();
        Collection<String> total = new ArrayList<>();
        Collection<String> asignacionesArray = Stream.of(datos.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
        asignacionesArray.stream()
                .forEach(asignacion -> {
                    Stream.of(asignacion.split("/"))
                            .forEach(element -> {
                                total.add(element);
                            });
                });
        
        AtomicInteger count = new AtomicInteger(1);
        
        total.forEach(element -> {
            int contador  = count.getAndIncrement();
            if(contador == 1) {semestres.add(Long.valueOf(element));}
            if(contador == 2) {materias.add(Long.valueOf(element));}
            if(contador == 3) {grupos.add(Integer.valueOf(element));count.set(1);}
            //if(contador == 4) {profesores.add(element); count.set(1);}
        });
        
        long[] semestresArray = semestres.stream().mapToLong(l -> l).toArray();
        long[] materiasArray = materias.stream().mapToLong(l -> l).toArray();
        int[] gruposArray = grupos.stream().mapToInt(l -> l).toArray();
       // String[] profesoresArray = new String[profesores.size()];
        //profesoresArray = profesores.toArray(profesoresArray);
        
        Stream.of(materias).forEach(element-> System.out.println(element));
        Collection<UrlNotas> lista = obtenerUrlNotasDAO().getTodos(semestresArray, materiasArray, gruposArray);
        
        resultado = "[";
        Collection<Object> listos = new ArrayList<>();
        
        if(!lista.isEmpty()){
            materias.stream().forEach(materia -> {
                if(!listos.contains(materia)){
                    listos.add(materia);
                    resultado = resultado.concat("{\"materia\":" + materia + ",");
                    resultado = resultado.concat("\"datos\":");
                    resultado = resultado.concat("[");
                    lista.stream().filter(x -> x.getMateria()== materia)
                            .forEach(ins -> {
                                resultado = resultado.concat("{\"cedula\":\"" + ins.getCedula() + "\"" + ",");
                                resultado = resultado.concat("\"punto\":" + ins.getPunto() + ",");
                                resultado = resultado.concat("\"metadato\":\"" + ins.getMetadato() + "\"" + ",");
                                resultado = resultado + "\"encuesta\":\"" + ins.getEncuesta() + "\"}" + ",";
                            });
                    resultado = resultado.substring(0, resultado.length()-1);
                    //resultado = resultado + "}";
                    resultado = resultado + "]";
                    resultado = resultado + "},";
                }
            });
                
        }else {
            return "[]";
        }
        resultado = resultado.substring(0, resultado.length()-1);
        resultado = resultado + "]";
        return resultado;
    }
}
