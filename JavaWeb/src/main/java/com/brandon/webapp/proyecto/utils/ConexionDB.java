package com.brandon.webapp.proyecto.utils;

import java.sql.Connection; //representa una conexión activa con una base de datos.
import java.sql.DriverManager; //se usa para obtener la conexión.
import java.sql.SQLException; //para capturar errores que ocurren al conectarse.

//1️⃣ Define cómo se conecta tu aplicación con la base de datos Oracle.
//Es un Singleton simple que retorna una conexión con DriverManager.
//Este archivo describe:
//Cómo se conecta a Oracle.
//Cómo puedes cambiar usuario/contraseña o base de datos.

public class ConexionDB {
    private static String url = "jdbc:oracle:thin:@//127.0.0.1:1521/orcl";
    private static String user = "admin";
    private static String password = "admin";

    public static Connection getInstance(){
        try{
            return DriverManager.getConnection(url,user,password);
        }catch (SQLException e){
            throw new RuntimeException("Error en la conexion a base de datos");
        }
    }

}
