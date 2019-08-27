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
public class MateriaMares {
    long programa;
    String nombrePrograma;
    long version;
    String indicadorVersionActual;
    long materia;
    String nombreMateria;
    String tipoMateria;
    int consecutivoMateria;
    String nombreBancoElectivas;
    int nivel;

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

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public String getIndicadorVersionActual() {
        return indicadorVersionActual;
    }

    public void setIndicadorVersionActual(String indicadorVersionActual) {
        this.indicadorVersionActual = indicadorVersionActual;
    }

    public long getMateria() {
        return materia;
    }

    public void setMateria(long materia) {
        this.materia = materia;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public String getTipoMateria() {
        return tipoMateria;
    }

    public void setTipoMateria(String tipoMateria) {
        this.tipoMateria = tipoMateria;
    }

    public int getConsecutivoMateria() {
        return consecutivoMateria;
    }

    public void setConsecutivoMateria(int consecutivoMateria) {
        this.consecutivoMateria = consecutivoMateria;
    }

    public String getNombreBancoElectivas() {
        return nombreBancoElectivas;
    }

    public void setNombreBancoElectivas(String nombreBancoElectivas) {
        this.nombreBancoElectivas = nombreBancoElectivas;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    
    
}
