package com.brandon.webapp.proyecto.filter;

import com.brandon.webapp.proyecto.utils.ConexionDB; // Importa la clase utilitaria para obtener conexiones a la BD
//import jakarta.servlet.*; Este es lo mismo que lo sig --->
import jakarta.servlet.Filter;               // Interfaz base para crear filtros en Jakarta EE
import jakarta.servlet.FilterChain;         // Permite pasar la solicitud al siguiente filtro o servlet
import jakarta.servlet.ServletException;    // Excepción general para errores relacionados con Servlets
import jakarta.servlet.ServletRequest;      // Representa la solicitud entrante (puede convertirse en HttpServletRequest)
import jakarta.servlet.ServletResponse;     // Representa la respuesta hacia el cliente
import jakarta.servlet.annotation.WebFilter; // Anotación para registrar este filtro en lugar de usar web.xml
//-------
import java.io.IOException;
import java.sql.Connection;

//2️⃣ Se ejecuta antes de todo servlet (MVC Filtro Servlet).
//Aquí se crea la conexión y se guarda en el request para que los servlets la usen.
//Este archivo describe:
//Qué es un filtro y cómo intercepta solicitudes.
//Cómo se pasa la conexión al servlet.

@WebFilter("/*") //vamos a filtrar todas las conexiones
//Es decir esto se ejecuta siempre que ingresemos a una URL
public class ConexionFilter implements Filter {
    private Connection getConnection(){ //Private para uso dentro de esta clase
        return ConexionDB.getInstance(); //retornamos una instancia del objeto conexiondb
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Connection con = this.getConnection();
        servletRequest.setAttribute("conn", con);
        try {
            filterChain.doFilter(servletRequest, servletResponse); //mantener la conexion
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
