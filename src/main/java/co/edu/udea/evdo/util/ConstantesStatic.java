package co.edu.udea.evdo.util;

public class ConstantesStatic {

    // Variables del filtro que maneja en sesion.
    public static final String SESION_FILTRO_USERNAME = "userName";
    public static final String SESION_FILTRO_CCID = "ccid";
    public static final String SESION_FILTRO_DISPLAYNAME = "displayName";
    public static final String SESION_FILTRO_EMAIL = "mail";
    public static final String SESION_FILTRO_PNOMBRE = "pnombre";
    public static final String SESION_FILTRO_SNOMBRE = "snombre";
    public static final String SESION_FILTRO_PAPELLIDO = "papellido";
    public static final String SESION_FILTRO_SAPELLIDO = "sapellido";       
    public static final String SESION_FILTRO_TIPO_IDENTIFICACION = "tipoIdentificacion";
    public static final String SESION_FILTRO_GRUPO = "grupos";
    public static final String SESION_FILTRO_ROLES_APLICACION = "rolesAplicacion";
    
    public static final String SESION_FILTRO_ID_CONTINENTE = "udeacontinenteresid";
    public static final String SESION_FILTRO_NOMBRE_CONTINENTE = "udeacontinenteresidtxt";
    public static final String SESION_FILTRO_ID_DEPARTAMENTO = "udeadepartamentoresid";
    public static final String SESION_FILTRO_NOMBRE_DEPARTAMENTO = "udeadepartamentoresidtxt";
    public static final String SESION_FILTRO_ID_MUNICIPIO = "udeamunicipioresid";
    public static final String SESION_FILTRO_NOMBRE_MUNICIPIO = "udeamunicipioresidtxt";
    public static final String SESION_FILTRO_ID_PAIS = "udeapaisresid";
    public static final String SESION_FILTRO_NOMBRE_PAIS = "udeapaisresidtxt";
    public static final String SESION_FILTRO_TELEFONO1 = "udeatelefono1";
    public static final String SESION_FILTRO_TELEFONO2 = "udeatelefono2";
    public static final String SESION_FILTRO_FECHA_NACIMIENTO = "udeafechanacimiento";
    public static final String SESION_FILTRO_DIRECCION_RESIDENCIA = "udeadireccionresid";
    
    public static final String ERROR_MUA = "Ocurrió un error obteniendo la información del usuario del Portal utilizando los servicios web del MUA. Por favor contacte al administrador del sistema";

    public static final String ROL_NINGUNO = "NINGUNO";

    
    public static final String GRUPO_ESTUDIANTE = "UdeAEstudiantes";
    public static final String GRUPO_PROFESOR = "UdeAProfesores";
    

    // Error obteniendo datos de la tabla TipoValores
    public static final String ERROR_TIPO_VALORES = "Error obteniendo datos de Tipo Valores";
    public static final long USER_INVALID = -1L;
    public static final boolean DEVELOPMENT = true;

    // Roles de los usuarios
    public static final String PROPERTIES_ORGSISTEMAS = "co.edu.udea.sigp.ppt.orgSistemas";
    public static final String PROPERTIES_STATEMENTS = "co.edu.udea.sigp.ppt.statements";
    public static final String PROPERTIES_MENSAJESCORREO = "co.edu.udea.sigp.ppt.mensajesCorreo";

    public static final String VARIABLE_SESION_TIPO_PROCEDIMIENTO = "tipoProcedimiento";

    public static final String VARIABLE_USUARIO_SAVED = "userName_Saved";
       
    public static final String VARIABLE_SESION_URL = "sesPath";
    public static final String VARIABLE_ROLMENU = "tipo";
    public static final String VARIABLE_ROLMENU_AMP = "amp;tipo";
    public static final String VARIABLE_ACCION = "accion";
    public static final String VARIABLE_ROL = "rolesAplicacion";

    public static final String VARIABLE_ROL_SAVED = "rolesSaved";

    // Constantes de los servicios web de la U de A
    //
    // MARES
    //
    public final static String WS_MARES_CONSULTAR_PERSONA = "consultapersonamares";
    public final static String WS_MARES_CONSULTAR_FACULTADES = "consultafacultadesmares";
    public final static String WS_MARES_CONSULTAR_PROGRAMAS = "consultaprogramasmares";
    public final static String WS_MARES_MATERIAS_PROGRAMA_VERSION = "consultarmateriasprogramaversion";
    public final static String WS_MARES_CONSULTAR_PROGRAMAS_DE_ROL = "consultarprogramasderol";
    public final static String WS_MARES_CONSULTAR_MATERIAS_ESTUDIANTES = "consultamateriasestudiantemares";

    //
    // SIPE
    //
    public final static String WS_SIPE_CONSULTA_EMPLEADOS = "consultaempleadossipe";
    
    
    // Datos para el envio de correos
     public static String CORREO_PRUEBAS_GSIS = "gestion.sibu";
     public static final String ASUNTO_CORREO = "Información importante de SIGP";
     public static final String NOMBRE_SERVICIO_CORREO = "INFORMACIONSIBU"; 

}
