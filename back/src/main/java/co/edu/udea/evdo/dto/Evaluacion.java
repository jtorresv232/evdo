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
public class Evaluacion implements Serializable{
    private String codigo;
    private int semestre;
    private double porcentaje;
    private double porcentajeprofesor;
    private boolean cargado;

    public boolean isCargado() {
        return cargado;
    }

    public void setCargado(boolean cargado) {
        this.cargado = cargado;
    }
    
    

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public double getPorcentajeprofesor() {
        return porcentajeprofesor;
    }

    public void setPorcentajeprofesor(double porcentajeprofesor) {
        this.porcentajeprofesor = porcentajeprofesor;
    }
    
    
}
