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
    private String prof_compartido;
    private String prof_catedra;
    private long num_catedra;
    private double porcentaje;
    private String tipo_periodo;
    private String encuesta;
    private Date fecha_enc_inicio;
    private Date fecha_enc_final;
    private int estudiantes;
    private int encuestados;
    private String nombre_materia;
    private String nombre_docente;
    private long programa;

    public long getPrograma() {
        return programa;
    }

    public void setPrograma(long programa) {
        this.programa = programa;
    }
    

    public String getNombre_materia() {
        return nombre_materia;
    }

    public void setNombre_materia(String nombre_materia) {
        this.nombre_materia = nombre_materia;
    }

    public String getNombre_docente() {
        return nombre_docente;
    }

    public void setNombre_docente(String nombre_docente) {
        this.nombre_docente = nombre_docente;
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

    public String getProf_compartido() {
        return prof_compartido;
    }

    public void setProf_compartido(String prof_compartido) {
        this.prof_compartido = prof_compartido;
    }

    public String getProf_catedra() {
        return prof_catedra;
    }

    public void setProf_catedra(String prof_catedra) {
        this.prof_catedra = prof_catedra;
    }

    public long getNum_catedra() {
        return num_catedra;
    }

    public void setNum_catedra(long num_catedra) {
        this.num_catedra = num_catedra;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getTipo_periodo() {
        return tipo_periodo;
    }

    public void setTipo_periodo(String tipo_periodo) {
        this.tipo_periodo = tipo_periodo;
    }

    public String getEncuesta() {
        return encuesta;
    }

    public void setEncuesta(String encuesta) {
        this.encuesta = encuesta;
    }

    public Date getFecha_enc_inicio() {
        return fecha_enc_inicio;
    }

    public void setFecha_enc_inicio(Date fecha_enc_inicio) {
        this.fecha_enc_inicio = fecha_enc_inicio;
    }

    public Date getFecha_enc_final() {
        return fecha_enc_final;
    }

    public void setFecha_enc_final(Date fecha_enc_final) {
        this.fecha_enc_final = fecha_enc_final;
    }
    
    
}
