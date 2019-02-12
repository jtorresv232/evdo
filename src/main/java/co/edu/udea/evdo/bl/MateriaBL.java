/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.bl;

import co.edu.udea.evdo.dao.impl.MateriaDAO;
import co.edu.udea.evdo.dto.Grupo;
import co.edu.udea.evdo.dto.GrupoXMateria;
import co.edu.udea.evdo.dto.Materia;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;

/**
 *
 * @author Jonathan
 */
public class MateriaBL implements Serializable {

    private static MateriaBL singletonInstance = new MateriaBL();

    public MateriaBL() {
        // empty constructor
    }

    public static MateriaBL getInstance() {
        synchronized (MateriaBL.class) {
            if (singletonInstance == null) {
                singletonInstance = new MateriaBL();
            }
        }
        return singletonInstance;
    }

    public Collection<Materia> getMaterias(long semestre, long programa) throws SQLException {
        Collection<Materia> materias = obtenerMateriaDAO().getMaterias(semestre, programa);
        GrupoBL grupobl = new GrupoBL();
        for(Materia materia: materias){
            Collection<GrupoXMateria> gruposCollection = grupobl.getInstance().getGruposXMateria(semestre, materia.getMateria());
            GrupoXMateria[] grupos = gruposCollection.toArray(new GrupoXMateria[gruposCollection.size()]);
            materia.setGrupos(grupos);
        }
        return materias;
    }

    private MateriaDAO obtenerMateriaDAO() {
        return new MateriaDAO();
    }

}
