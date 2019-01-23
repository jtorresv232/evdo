/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.evdo.util;

/**
 *
 * @author Jonathan
 */
import co.edu.udea.evdo.dto.UsuarioSesion;
import co.edu.udea.evdo.properties.Properties;
import java.sql.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

public class UtilidadesSesion 
{
    
    static final Logger logger = Logger.getLogger(UtilidadesSesion.class);
    
    public UsuarioSesion obtenerDatosUsuarioSesion(HttpServletRequest httpRequest) {

        UsuarioSesion usuarioSesion = new UsuarioSesion();

        this.logger.debug("Ingreso al método obtenerDatosUsuarioSesion");

        if (Properties.getInstance().getEvaluacionProperties().getString("FILTRO_ACTIVO").equals("false")) {

            this.logger.debug("FILTRO_ACTIVO = FALSE");

            Map<String, Object> usuarioFiltro = new HashMap<>();

            usuarioFiltro.put(ConstantesStatic.SESION_FILTRO_USERNAME, "david.devargas");
            usuarioFiltro.put(ConstantesStatic.SESION_FILTRO_CCID, "1035435193");
            usuarioFiltro.put(ConstantesStatic.SESION_FILTRO_DISPLAYNAME, "DAVID DE VARGAS");
            usuarioFiltro.put(ConstantesStatic.SESION_FILTRO_EMAIL, "desarrolloingenieria13@udea.edu.co");
            usuarioFiltro.put(ConstantesStatic.SESION_FILTRO_PNOMBRE, "DAVID");
            usuarioFiltro.put(ConstantesStatic.SESION_FILTRO_SNOMBRE, "");
            usuarioFiltro.put(ConstantesStatic.SESION_FILTRO_PAPELLIDO, "DE VARGAS");
            usuarioFiltro.put(ConstantesStatic.SESION_FILTRO_SAPELLIDO, "CUETER");
            usuarioFiltro.put(ConstantesStatic.SESION_FILTRO_GRUPO, "UdeAEmpleados");
            usuarioFiltro.put(ConstantesStatic.SESION_FILTRO_TIPO_IDENTIFICACION, "CC");

            usuarioSesion.setUserName((String) usuarioFiltro.get(ConstantesStatic.SESION_FILTRO_USERNAME));
            usuarioSesion.setCcid((String) usuarioFiltro.get(ConstantesStatic.SESION_FILTRO_CCID));
            usuarioSesion.setDisplayName((String) usuarioFiltro.get(ConstantesStatic.SESION_FILTRO_DISPLAYNAME));
            usuarioSesion.setEmail((String) usuarioFiltro.get(ConstantesStatic.SESION_FILTRO_EMAIL));
            usuarioSesion.setpNombre((String) usuarioFiltro.get(ConstantesStatic.SESION_FILTRO_PNOMBRE));
            usuarioSesion.setsNombre((String) usuarioFiltro.get(ConstantesStatic.SESION_FILTRO_SNOMBRE));
            usuarioSesion.setpApellido((String) usuarioFiltro.get(ConstantesStatic.SESION_FILTRO_PAPELLIDO));
            usuarioSesion.setsApellido((String) usuarioFiltro.get(ConstantesStatic.SESION_FILTRO_SAPELLIDO));
            usuarioSesion.setTipoIdentificacion((String) usuarioFiltro.get(ConstantesStatic.SESION_FILTRO_TIPO_IDENTIFICACION));
            usuarioSesion.setGrupos((String) usuarioFiltro.get((ConstantesStatic.SESION_FILTRO_GRUPO)));

            this.logger.debug(usuarioSesion.getCcid());
            this.logger.debug(usuarioSesion.getpNombre());
            this.logger.debug(usuarioSesion.getsNombre());
            this.logger.debug(usuarioSesion.getpApellido());
            this.logger.debug(usuarioSesion.getsApellido());
            this.logger.debug(usuarioSesion.getUserName());
            this.logger.debug(usuarioSesion.getTipoIdentificacion());
            this.logger.debug(usuarioSesion.getDisplayName());
            this.logger.debug(usuarioSesion.getGrupos());

        } else {
            try {

                this.logger.debug("FILTRO_ACTIVO = TRUE");

                HttpSession sesion = httpRequest.getSession();
                
                this.logger.debug("Obtuvo datos de httpRequest.getSession()");
                
                this.logger.debug("Escribiendo atributo dummy");
                sesion.setAttribute("DUMMY_SESSION", "Este es un ejemplo DUMMY");
                this.logger.debug("Obteniendo el atributo dummy");
                String datosDummy = (String) sesion.getAttribute("DUMMY_SESSION");
                this.logger.debug("Presentando el contenido del atributo dummy");
                this.logger.debug(datosDummy);
                this.logger.debug("heeeyyyyy");
                Enumeration e = (Enumeration) (sesion.getAttributeNames());
                while ( e.hasMoreElements())
        {
            Object tring;
            if((tring = e.nextElement())!=null)
            {
                this.logger.debug(sesion.getAttribute((String) tring));
            }

        }
                
                if (sesion.getAttribute("userDataUdeA") != null) {

                    this.logger.debug("El atributo userDataUdeA es diferente de null");

                    Map<String, Object> usuarioFiltro = (HashMap<String, Object>) sesion.getAttribute("userDataUdeA");

                    this.logger.debug("Obtuvo los datos desde el atributo userDataUdeA");

                    this.logger.debug((String) usuarioFiltro.get(ConstantesStatic.SESION_FILTRO_USERNAME));
                    this.logger.debug((String) usuarioFiltro.get(ConstantesStatic.SESION_FILTRO_CCID));
                    this.logger.debug((String) usuarioFiltro.get(ConstantesStatic.SESION_FILTRO_DISPLAYNAME));
                    this.logger.debug((String) usuarioFiltro.get(ConstantesStatic.SESION_FILTRO_EMAIL));
                    this.logger.debug((String) usuarioFiltro.get(ConstantesStatic.SESION_FILTRO_PNOMBRE));
                    this.logger.debug((String) usuarioFiltro.get(ConstantesStatic.SESION_FILTRO_SNOMBRE));
                    this.logger.debug((String) usuarioFiltro.get(ConstantesStatic.SESION_FILTRO_PAPELLIDO));
                    this.logger.debug((String) usuarioFiltro.get(ConstantesStatic.SESION_FILTRO_SAPELLIDO));

                    if (usuarioFiltro.get(ConstantesStatic.SESION_FILTRO_USERNAME) != null) {

                        this.logger.debug("SESION_FILTRO_USERNAME diferente de null");

                        usuarioSesion.setUserName((String) usuarioFiltro.get(ConstantesStatic.SESION_FILTRO_USERNAME));
                        usuarioSesion.setCcid((String) usuarioFiltro.get(ConstantesStatic.SESION_FILTRO_CCID));
                        usuarioSesion.setDisplayName((String) usuarioFiltro.get(ConstantesStatic.SESION_FILTRO_DISPLAYNAME));
                        usuarioSesion.setEmail((String) usuarioFiltro.get(ConstantesStatic.SESION_FILTRO_EMAIL));
                        usuarioSesion.setpNombre((String) usuarioFiltro.get(ConstantesStatic.SESION_FILTRO_PNOMBRE));
                        usuarioSesion.setsNombre((String) usuarioFiltro.get(ConstantesStatic.SESION_FILTRO_SNOMBRE));
                        usuarioSesion.setpApellido((String) usuarioFiltro.get(ConstantesStatic.SESION_FILTRO_PAPELLIDO));
                        usuarioSesion.setsApellido((String) usuarioFiltro.get(ConstantesStatic.SESION_FILTRO_SAPELLIDO));
                        usuarioSesion.setTipoIdentificacion((String) usuarioFiltro.get(ConstantesStatic.SESION_FILTRO_TIPO_IDENTIFICACION));

                        // TODO: Falta agregar el campo de Roles, que es de tipo RolesAplicacion que esta en el archivo de filtro 1.1.6
                        usuarioSesion.setUdeacontinenteresid((Integer) usuarioFiltro.get(ConstantesStatic.SESION_FILTRO_ID_CONTINENTE));
                        usuarioSesion.setUdeacontinenteresidtxt((String) usuarioFiltro.get(ConstantesStatic.SESION_FILTRO_NOMBRE_CONTINENTE));

                        usuarioSesion.setUdeadepartamentoresid((Integer) usuarioFiltro.get(ConstantesStatic.SESION_FILTRO_ID_DEPARTAMENTO));
                        usuarioSesion.setUdeadepartamentoresidtxt((String) usuarioFiltro.get(ConstantesStatic.SESION_FILTRO_NOMBRE_DEPARTAMENTO));

                        usuarioSesion.setUdeamunicipioresid((Integer) usuarioFiltro.get(ConstantesStatic.SESION_FILTRO_ID_MUNICIPIO));
                        usuarioSesion.setUdeamunicipioresidtxt((String) usuarioFiltro.get(ConstantesStatic.SESION_FILTRO_NOMBRE_MUNICIPIO));

                        usuarioSesion.setUdeapaisresid((Integer) usuarioFiltro.get(ConstantesStatic.SESION_FILTRO_ID_PAIS));
                        usuarioSesion.setUdeapaisresidtxt((String) usuarioFiltro.get(ConstantesStatic.SESION_FILTRO_NOMBRE_PAIS));

                        usuarioSesion.setUdeatelefono1((String) usuarioFiltro.get(ConstantesStatic.SESION_FILTRO_TELEFONO1));
                        usuarioSesion.setUdeatelefono2((String) usuarioFiltro.get(ConstantesStatic.SESION_FILTRO_TELEFONO2));
                        usuarioSesion.setUdeafechanacimiento((Date) usuarioFiltro.get(ConstantesStatic.SESION_FILTRO_FECHA_NACIMIENTO));
                        usuarioSesion.setUdeadireccionresid((String) usuarioFiltro.get((ConstantesStatic.SESION_FILTRO_DIRECCION_RESIDENCIA)));

                    } else {
                        this.logger.debug("SESION_FILTRO_USERNAME igual a null");
                    }

                } else {
                    this.logger.debug("El atributo userDataUdeA es igual a null");
                }

            } catch (Exception ex) {
                this.logger.error(ex.getMessage());
            }
        }

        return usuarioSesion;
    }
}