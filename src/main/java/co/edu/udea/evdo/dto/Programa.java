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
public class Programa implements Serializable{
    private long programa;
    private String nombrePrograma;
    private String tipoPrograma;
    private String sede;
    private long facultad;
    private String nombreFacultad;
    private long creditosGrado;
    private long versionActual;
    private String versiones;
    private String estado;

    public long getPrograma() {
        return programa;
    }

    public void setPrograma(long programa) {
        this.programa = programa;
    }

    public String getNombrePrograma() {
        return nombrePrograma;
    }

    public void setNombrePrograma(String nombrePrograma) {
        this.nombrePrograma = nombrePrograma;
    }

    public String getTipoPrograma() {
        return tipoPrograma;
    }

    public void setTipoPrograma(String tipoPrograma) {
        this.tipoPrograma = tipoPrograma;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public long getFacultad() {
        return facultad;
    }

    public void setFacultad(long facultad) {
        this.facultad = facultad;
    }

    public String getNombreFacultad() {
        return nombreFacultad;
    }

    public void setNombreFacultad(String nombreFacultad) {
        this.nombreFacultad = nombreFacultad;
    }

    public long getCreditosGrado() {
        return creditosGrado;
    }

    public void setCreditosGrado(long creditosGrado) {
        this.creditosGrado = creditosGrado;
    }

    public long getVersionActual() {
        return versionActual;
    }

    public void setVersionActual(long versionActual) {
        this.versionActual = versionActual;
    }

    public String getVersiones() {
        return versiones;
    }

    public void setVersiones(String versiones) {
        this.versiones = versiones;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
