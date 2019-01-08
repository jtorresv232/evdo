/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.dto;

import java.sql.Date;

/**
 *
 * @author Jonathan
 */
public class Asignacion {

    private long semestre;
    private long materia;
    private int grupo;
    private String cedula;
    private String profCompartido;
    private String profCatedra;
    private long numCatedra;
    private double porcentaje;
    private String tipoPeriodo;
    private String encuesta;
    private Date fechaEncInicio;
    private Date fechaEncFinal;
    private int estudiantes;
    private int encuestados;
    private String nombreMateria;
    private String nombreDocente;
    private long programa;
    private int total;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public long getPrograma() {
        return programa;
    }

    public void setPrograma(long programa) {
        this.programa = programa;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public String getNombreDocente() {
        return nombreDocente;
    }

    public void setNombreDocente(String nombreDocente) {
        this.nombreDocente = nombreDocente;
    }

    public int getEncuestados() {
        return encuestados;
    }

    public void setEncuestados(int encuestados) {
        this.encuestados = encuestados;
    }

    public int getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(int estudiantes) {
        this.estudiantes = estudiantes;
    }

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

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getProfCompartido() {
        return profCompartido;
    }

    public void setProfCompartido(String profCompartido) {
        this.profCompartido = profCompartido;
    }

    public String getProfCatedra() {
        return profCatedra;
    }

    public void setProfCatedra(String profCatedra) {
        this.profCatedra = profCatedra;
    }

    public long getNumCatedra() {
        return numCatedra;
    }

    public void setNumCatedra(long numCatedra) {
        this.numCatedra = numCatedra;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getTipoPeriodo() {
        return tipoPeriodo;
    }

    public void setTipoPeriodo(String tipoPeriodo) {
        this.tipoPeriodo = tipoPeriodo;
    }

    public String getEncuesta() {
        return encuesta;
    }

    public void setEncuesta(String encuesta) {
        this.encuesta = encuesta;
    }

    public Date getFechaEncInicio() {
        return fechaEncInicio;
    }

    public void setFechaEncInicio(Date fechaEncInicio) {
        this.fechaEncInicio = fechaEncInicio;
    }

    public Date getFechaEncFinal() {
        return fechaEncFinal;
    }

    public void setFechaEncFinal(Date fechaEncFinal) {
        this.fechaEncFinal = fechaEncFinal;
    }

}
