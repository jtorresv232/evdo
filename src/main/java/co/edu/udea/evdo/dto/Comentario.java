/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.dto;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Jonathan
 */
public class Comentario implements Serializable{
    private int codigo;
    private String autor;
    private String email_autor;
    private Date fecha_comentario;
    private String comentario;
    private int semestre;
    private int materia;
    private int grupo;
    private String cedula;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEmail_autor() {
        return email_autor;
    }

    public void setEmail_autor(String email_autor) {
        this.email_autor = email_autor;
    }

    public Date getFecha_comentario() {
        return fecha_comentario;
    }

    public void setFecha_comentario(Date fecha_comentario) {
        this.fecha_comentario = fecha_comentario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public int getMateria() {
        return materia;
    }

    public void setMateria(int materia) {
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
