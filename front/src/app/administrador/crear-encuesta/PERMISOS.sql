-- Permisos sobre tablas para USER en sistema de encuestas de moises

GRANT UPDATE, SELECT, INSERT, DELETE ON MOIS_TEMAENCUESTAS TO USER;
GRANT UPDATE, SELECT, INSERT, DELETE ON MOIS_BANCOPREGUNTAENCUESTAS TO USER;
GRANT UPDATE, SELECT, INSERT, DELETE ON MOIS_ROLPREGUNTAENCUESTAS TO USER;
GRANT UPDATE, SELECT, INSERT, DELETE ON MOIS_LISTAENCUESTAS TO USER;
GRANT UPDATE, SELECT, INSERT, DELETE ON MOIS_OPCIONENCUESTAS TO USER;
GRANT UPDATE, SELECT, INSERT, DELETE ON MOIS_DERIVADAENCUESTAS TO USER;
GRANT UPDATE, SELECT, INSERT, DELETE ON MOIS_PREGUNTAENCUESTAS TO USER;
GRANT UPDATE, SELECT, INSERT, DELETE ON MOIS_SECCIONESENCUESTAS TO USER;
GRANT UPDATE, SELECT, INSERT, DELETE ON MOIS_RESPUESTAENCUESTAS TO USER;
GRANT UPDATE, SELECT, INSERT, DELETE ON MOIS_ROLPUNTOENCUESTAS TO USER;
GRANT UPDATE, SELECT, INSERT, DELETE ON MOIS_PUNTOAPLICACIONENCUESTAS TO USER;
GRANT UPDATE, SELECT, INSERT, DELETE ON MOIS_ENCUESTAS TO USER;
GRANT UPDATE, SELECT, INSERT, DELETE ON MOIS_POSIBLESENCUESTADOS TO USER;
GRANT UPDATE, SELECT, INSERT, DELETE ON MOIS_HISTORICOENCUESTAS TO USER;
GRANT UPDATE, SELECT, INSERT, DELETE ON MOIS_EJECUTAENCUESTA TO USER;
GRANT UPDATE, SELECT, INSERT, DELETE ON MOIS_RELACIONSECRETAENCUESTAS TO USER;
GRANT UPDATE, SELECT, INSERT, DELETE ON MOIS_CONTROLSECRETOENCUESTAS TO USER;

-- Permisos de ejecución

GRANT EXECUTE ON P_ENCU_SEL_ENCUESTAS_X_ROL TO USER;
GRANT EXECUTE ON P_ENCU_INS_PREGUNTA TO USER;
GRANT EXECUTE ON P_ENCU_SEL_OPC_X_PREGS TO USER;
GRANT EXECUTE ON P_ENCU_SEL_TEMAS_X_ROL TO USER;
GRANT EXECUTE ON P_ENCU_INS_OPCION_PREGUNTA TO USER;
GRANT EXECUTE ON P_ENCU_INS_PUNTO TO USER;
GRANT EXECUTE ON P_ENCU_UPD_PUNTO TO USER;
GRANT EXECUTE ON P_ENCU_INS_ENCUESTA TO USER;
GRANT EXECUTE ON P_ENCU_INS_PREG_ENCUESTA TO USER;
GRANT EXECUTE ON P_ENCU_SEL_PREGUNTAS_X_ROL TO USER;
GRANT EXECUTE ON P_ENCU_DEL_PREG_ENCUESTA TO USER;
GRANT EXECUTE ON P_ENCU_DEL_ENCUESTA TO USER;
GRANT EXECUTE ON P_ENCU_DEL_OPCION TO USER;
GRANT EXECUTE ON P_ENCU_UPD_OPCION TO USER;
GRANT EXECUTE ON P_ENCU_SEL_RESPUESTAS_ENCUESTA TO USER;
GRANT EXECUTE ON P_ENCU_SEL_COUNT_ENCUESTADOS TO USER;
GRANT EXECUTE ON P_ENCU_DEL_PREGUNTA TO USER;
GRANT EXECUTE ON P_ENCU_UPD_PREGUNTA TO USER;
GRANT EXECUTE ON P_ENCU_SEL_ENCUESTAS_PERSONA TO USER;


-- Secuencias

GRANT SELECT ON AUTO TO USER;
GRANT SELECT ON ENCU_SEQ_PREGUNTA TO USER;
GRANT SELECT ON ENCU_SEQ_SECCION TO USER;

--UDTs

GRANT USAGE ON TYPE OPS$MOISES.MOIS_METADATO_ENCUESTAS TO USER;