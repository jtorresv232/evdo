/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.dto;

import java.io.Serializable;

/**
 *
 * @author Jonathan
 */
public class Materia implements Serializable{
    private int materia;
    private String nombreMateria;
    private GrupoXMateria[] grupos;

    public int getMateria() {
        return materia;
    }

    public void setMateria(int materia) {
        this.materia = materia;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public GrupoXMateria[] getGrupos() {
        return grupos;
    }

    public void setGrupos(GrupoXMateria[] grupos) {
        this.grupos = grupos;
    }
    
    
}
