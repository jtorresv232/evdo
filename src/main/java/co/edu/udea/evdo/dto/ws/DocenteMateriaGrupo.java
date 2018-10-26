/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.dto.ws;

/**
 *
 * @author Jonathan
 */
public class DocenteMateriaGrupo {
    String identificacionDocente;
    String nombreDocente;
    double porcentajeAsignacion;
    String indicadorProfesoresComparten;
    String indicadorCatedra;
    long numeroContratoCatedra;
    String tipoPeriodo;
    String indicadorCoordinador;
    String numeroHorasAsignacion;
    int horasPlanTrabajo;
    int horasFueraPlanTrabajo;

    public String getIdentificacionDocente() {
        return identificacionDocente;
    }

    public void setIdentificacionDocente(String identificacionDocente) {
        this.identificacionDocente = identificacionDocente;
    }

    public String getNombreDocente() {
        return nombreDocente;
    }

    public void setNombreDocente(String nombreDocente) {
        this.nombreDocente = nombreDocente;
    }

    public double getPorcentajeAsignacion() {
        return porcentajeAsignacion;
    }

    public void setPorcentajeAsignacion(double porcentajeAsignacion) {
        this.porcentajeAsignacion = porcentajeAsignacion;
    }

    public String getIndicadorProfesoresComparten() {
        return indicadorProfesoresComparten;
    }

    public void setIndicadorProfesoresComparten(String indicadorProfesoresComparten) {
        this.indicadorProfesoresComparten = indicadorProfesoresComparten;
    }

    public String getIndicadorCatedra() {
        return indicadorCatedra;
    }

    public void setIndicadorCatedra(String indicadorCatedra) {
        this.indicadorCatedra = indicadorCatedra;
    }

    public long getNumeroContratoCatedra() {
        return numeroContratoCatedra;
    }

    public void setNumeroContratoCatedra(long numeroContratoCatedra) {
        this.numeroContratoCatedra = numeroContratoCatedra;
    }

    public String getTipoPeriodo() {
        return tipoPeriodo;
    }

    public void setTipoPeriodo(String tipoPeriodo) {
        this.tipoPeriodo = tipoPeriodo;
    }

    public String getIndicadorCoordinador() {
        return indicadorCoordinador;
    }

    public void setIndicadorCoordinador(String indicadorCoordinador) {
        this.indicadorCoordinador = indicadorCoordinador;
    }

    public String getNumeroHorasAsignacion() {
        return numeroHorasAsignacion;
    }

    public void setNumeroHorasAsignacion(String numeroHorasAsignacion) {
        this.numeroHorasAsignacion = numeroHorasAsignacion;
    }

    public int getHorasPlanTrabajo() {
        return horasPlanTrabajo;
    }

    public void setHorasPlanTrabajo(int horasPlanTrabajo) {
        this.horasPlanTrabajo = horasPlanTrabajo;
    }

    public int getHorasFueraPlanTrabajo() {
        return horasFueraPlanTrabajo;
    }

    public void setHorasFueraPlanTrabajo(int horasFueraPlanTrabajo) {
        this.horasFueraPlanTrabajo = horasFueraPlanTrabajo;
    }
    
    
}
