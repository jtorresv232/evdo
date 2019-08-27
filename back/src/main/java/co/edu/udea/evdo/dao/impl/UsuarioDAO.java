/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.dao.impl;

import co.edu.udea.evdo.dao.ConnectionPool;
import co.edu.udea.evdo.dto.Usuario;
import co.edu.udea.evdo.exceptions.EvdoSQLException;
import co.edu.udea.evdo.properties.Properties;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import oracle.jdbc.OracleTypes;
import org.apache.log4j.Logger;

/**
 *
 * @author Jonathan
 */
public class UsuarioDAO extends ConnectionPool{
    static final Logger logger = Logger.getLogger(UsuarioDAO.class);
    
    public Collection<Usuario> getAllUsuarios() throws EvdoSQLException {
        CallableStatement ps = null;
        ResultSet rs = null;
        Collection<Usuario> listaUsuarios = new LinkedList<>();
        Usuario usuario;
        try{
            ps = getConn().prepareCall(Properties.getInstance().getEvaluacionProperties().getString("usuario.obtener"));
            rs = ps.executeQuery();
            if(rs != null) {
                while(rs.next()) {
                    usuario = new Usuario();
                    usuario.setCedula(rs.getString("CEDULA"));
                    usuario.setNombre(rs.getString("NOMBRE"));
                    usuario.setCorreo(rs.getString("CORREO"));
                    usuario.setDirección(rs.getString("DIRECCION"));
                    usuario.setFacultad(rs.getLong("FACULTAD"));
                    usuario.setRol(rs.getString("ROL"));
                    usuario.setTelefono(rs.getString("TELEFONO"));
                    listaUsuarios.add(usuario);
                }
            }
        }catch(SQLException e){
            logger.error(e);
            throw new EvdoSQLException();
        }finally{
            close(ps);
            logger.debug("Se han consltado todos los usuarios");
        }
        return listaUsuarios;
    }
    
    public Usuario addUsuario(Usuario usuario) throws EvdoSQLException {
        CallableStatement ps = null;
        Usuario nuevoUsuario = null;
        ResultSet rs = null;
        try{
            ps = getConn().prepareCall(Properties.getInstance().getEvaluacionProperties().getString("usuario.agregar"));
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getCedula());
            ps.setString(3, usuario.getTelefono());
            ps.setString(4, usuario.getDirección());
            ps.setString(5, usuario.getCorreo());
            ps.setString(6, usuario.getRol());
            ps.setLong(7, usuario.getFacultad());
            ps.registerOutParameter(8, OracleTypes.CURSOR);
            ps.executeQuery();
            rs = (ResultSet) ps.getObject(8);
            if (rs != null) {
                rs.next();
                nuevoUsuario = new Usuario();
                nuevoUsuario.setCedula(rs.getString("CEDULA"));
                nuevoUsuario.setNombre(rs.getString("NOMBRE"));
                nuevoUsuario.setCorreo(rs.getString("CORREO"));
                nuevoUsuario.setDirección(rs.getString("DIRECCION"));
                nuevoUsuario.setFacultad(rs.getLong("FACULTAD"));
                nuevoUsuario.setRol(rs.getString("ROL"));
                nuevoUsuario.setTelefono(rs.getString("TELEFONO"));
            }
        }catch(SQLException e){
            logger.error(e);
            throw new EvdoSQLException();
        }finally{
            close(ps);
            logger.debug("Se ha agregado el usuario con cedula " + usuario.getCedula());
        }
        return nuevoUsuario;
    }
    
    public Usuario findUser(String cedula) throws EvdoSQLException {
        CallableStatement ps = null;
        ResultSet rs = null;
        Usuario usuario = null;
        try{
            ps = getConn().prepareCall(Properties.getInstance().getEvaluacionProperties().getString("usuario.buscar"));
            ps.setString(1, cedula);
            rs = ps.executeQuery();
            if(rs != null) {
                rs.next();
                usuario = new Usuario();
                usuario.setCedula(rs.getString("CEDULA"));
                usuario.setNombre(rs.getString("NOMBRE"));
                usuario.setCorreo(rs.getString("CORREO"));
                usuario.setDirección(rs.getString("DIRECCION"));
                usuario.setFacultad(rs.getLong("FACULTAD"));
                usuario.setRol(rs.getString("ROL"));
                usuario.setTelefono(rs.getString("TELEFONO"));
            }
        }catch(SQLException e){
            logger.debug(e);
            throw new EvdoSQLException();
        }catch(Exception e) {
            logger.debug(e);
        }finally{
            close(ps);
            logger.debug("Se ha consultado el usuario identificado con cedula " + cedula);
        }
        return usuario;
    }
    
    public void deleteUsuario(String cedula) {
        CallableStatement ps = null;
        try{
            ps = getConn().prepareCall(Properties.getInstance().getEvaluacionProperties().getString("usuario.borrar"));
            ps.setString(1, cedula);
            ps.executeQuery();
        }catch(Exception e){
            logger.debug(e);
        }finally {
            close(ps);
        }
    }
}
