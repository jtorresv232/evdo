/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.bl;

import co.edu.udea.evdo.dao.impl.GrupoDAO;
import co.edu.udea.evdo.dto.Grupo;
import co.edu.udea.evdo.dto.GrupoXMateria;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author Jonathan
 */
public class GrupoBL implements Serializable {

    private static GrupoBL singletonInstance = new GrupoBL();

    public GrupoBL() {
        // empty constructor
    }

    public static GrupoBL getInstance() {
        synchronized (GrupoBL.class) {
            if (singletonInstance == null) {
                singletonInstance = new GrupoBL();
            }
        }
        return singletonInstance;
    }

    public Collection<Grupo> getGrupos() {
        return obtenerGrupoDAO().getGrupos();
    }

    public Grupo addGrupo(Grupo grupo) {
        return obtenerGrupoDAO().addGrupo(grupo);
    }
    
    public Collection<GrupoXMateria> getGruposXMateria(long semestre, long materia){
        return obtenerGrupoDAO().getGruposXMateria(semestre, materia);
    }

    private GrupoDAO obtenerGrupoDAO() {
        return new GrupoDAO();
    }

}
