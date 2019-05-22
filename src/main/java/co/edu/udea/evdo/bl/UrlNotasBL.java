/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.bl;

import co.edu.udea.evdo.dao.impl.UrlNotasDAO;
import co.edu.udea.evdo.dto.UrlNotas;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author JonathanTorresVelez
 */
public class UrlNotasBL implements Serializable{
    private static UrlNotasBL singletonInstance = new UrlNotasBL();
    
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
}
