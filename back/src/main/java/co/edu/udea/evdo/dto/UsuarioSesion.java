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
public class UsuarioSesion 
{
    private String userName;
    private String ccid;
    private String displayName;
    private String email;    
    private String pNombre;
    private String sNombre;
    private String pApellido;
    private String sApellido;
    private String tipoIdentificacion;
    private String grupos;    

    private int udeacontinenteresid;
    private String udeacontinenteresidtxt;
    private int udeadepartamentoresid;
    private String udeadepartamentoresidtxt;
    private int udeamunicipioresid;
    private String udeamunicipioresidtxt;
    private int udeapaisresid;
    private String udeapaisresidtxt;
    private String udeatelefono1;
    private String udeatelefono2;
    private Date udeafechanacimiento;
    private String udeadireccionresid;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCcid() {
        return ccid;
    }

    public void setCcid(String ccid) {
        this.ccid = ccid;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getpNombre() {
        return pNombre;
    }

    public void setpNombre(String pNombre) {
        this.pNombre = pNombre;
    }

    public String getsNombre() {
        return sNombre;
    }

    public void setsNombre(String sNombre) {
        this.sNombre = sNombre;
    }

    public String getpApellido() {
        return pApellido;
    }

    public void setpApellido(String pApellido) {
        this.pApellido = pApellido;
    }

    public String getsApellido() {
        return sApellido;
    }

    public void setsApellido(String sApellido) {
        this.sApellido = sApellido;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getGrupos() {
        return grupos;
    }

    public void setGrupos(String grupos) {
        this.grupos = grupos;
    }

    public int getUdeacontinenteresid() {
        return udeacontinenteresid;
    }

    public void setUdeacontinenteresid(int udeacontinenteresid) {
        this.udeacontinenteresid = udeacontinenteresid;
    }

    public String getUdeacontinenteresidtxt() {
        return udeacontinenteresidtxt;
    }

    public void setUdeacontinenteresidtxt(String udeacontinenteresidtxt) {
        this.udeacontinenteresidtxt = udeacontinenteresidtxt;
    }

    public int getUdeadepartamentoresid() {
        return udeadepartamentoresid;
    }

    public void setUdeadepartamentoresid(int udeadepartamentoresid) {
        this.udeadepartamentoresid = udeadepartamentoresid;
    }

    public String getUdeadepartamentoresidtxt() {
        return udeadepartamentoresidtxt;
    }

    public void setUdeadepartamentoresidtxt(String udeadepartamentoresidtxt) {
        this.udeadepartamentoresidtxt = udeadepartamentoresidtxt;
    }

    public int getUdeamunicipioresid() {
        return udeamunicipioresid;
    }

    public void setUdeamunicipioresid(int udeamunicipioresid) {
        this.udeamunicipioresid = udeamunicipioresid;
    }

    public String getUdeamunicipioresidtxt() {
        return udeamunicipioresidtxt;
    }

    public void setUdeamunicipioresidtxt(String udeamunicipioresidtxt) {
        this.udeamunicipioresidtxt = udeamunicipioresidtxt;
    }

    public int getUdeapaisresid() {
        return udeapaisresid;
    }

    public void setUdeapaisresid(int udeapaisresid) {
        this.udeapaisresid = udeapaisresid;
    }

    public String getUdeapaisresidtxt() {
        return udeapaisresidtxt;
    }

    public void setUdeapaisresidtxt(String udeapaisresidtxt) {
        this.udeapaisresidtxt = udeapaisresidtxt;
    }

    public String getUdeatelefono1() {
        return udeatelefono1;
    }

    public void setUdeatelefono1(String udeatelefono1) {
        this.udeatelefono1 = udeatelefono1;
    }

    public String getUdeatelefono2() {
        return udeatelefono2;
    }

    public void setUdeatelefono2(String udeatelefono2) {
        this.udeatelefono2 = udeatelefono2;
    }

    public Date getUdeafechanacimiento() {
        return udeafechanacimiento;
    }

    public void setUdeafechanacimiento(Date udeafechanacimiento) {
        this.udeafechanacimiento = udeafechanacimiento;
    }

    public String getUdeadireccionresid() {
        return udeadireccionresid;
    }

    public void setUdeadireccionresid(String udeadireccionresid) {
        this.udeadireccionresid = udeadireccionresid;
    }
}
