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
public class Grupo implements Serializable{
    private long semestre;
    private long materia;
    private int grupo;
    private int numEstudiantes;

    public long getSemestre() {
        return semestre;
    }

    public void setSemestre(long semestre) {
        this.semestre = semestre;
    }

    public long getMateria() {
        return materia;
    }

    public void setMateria(long materia) {
        this.materia = materia;
    }

    public int getGrupo() {
        return grupo;
    }

    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }

    public int getNumEstudiantes() {
        return numEstudiantes;
    }

    public void setNumEstudiantes(int numEstudiantes) {
        this.numEstudiantes = numEstudiantes;
    }
    
}
