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
public class TotalTema implements Serializable{
    private int codigo;
    private int codigo_tema;
    private String evaluacion;
    private double media;
    private double desviacion;
    private double coeficiente_desv;
    private long semestre;
    private long materia;
    private int grupo;
    private String cedula;
    private String tema;

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }
    
    

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo_tema() {
        return codigo_tema;
    }

    public void setCodigo_tema(int codigo_tema) {
        this.codigo_tema = codigo_tema;
    }

    public String getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(String evaluacion) {
        this.evaluacion = evaluacion;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }

    public double getDesviacion() {
        return desviacion;
    }

    public void setDesviacion(double desviacion) {
        this.desviacion = desviacion;
    }

    public double getCoeficiente_desv() {
        return coeficiente_desv;
    }

    public void setCoeficiente_desv(double coeficiente_desv) {
        this.coeficiente_desv = coeficiente_desv;
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
    
    
}
