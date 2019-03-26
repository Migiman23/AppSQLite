package com.example.sqliteprueba1.Utilidades;

public class Constantes {
    //Constantes
    public static final String TABLA_USUARIOS="usuarios";
    public static final String CAMPO_NOMBRE="Nombre";
    public static final String CAMPO_ID="id";
    public static final String CAMPO_TELEFONO="Telefono";
    public static final String CREATE_USER_TABLE="CREATE TABLE "+TABLA_USUARIOS+" ("+CAMPO_ID+" INTEGER, "+
                                                                                    CAMPO_NOMBRE+" TEXT,"+CAMPO_TELEFONO+" TEXT)";

}
