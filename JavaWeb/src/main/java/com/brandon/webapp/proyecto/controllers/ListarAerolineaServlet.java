package com.brandon.webapp.proyecto.controllers;

import com.brandon.webapp.proyecto.models.entities.Aerolinea;
import com.brandon.webapp.proyecto.repositories.AerolineaRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

//3️⃣ Maneja la lógica para la ruta /aerolineas/listar.
//Este archivo describe:
//Cómo se usa el request/response.
//Cómo se llama al repositorio.
//Cómo se reenvía a un JSP.

@WebServlet("/aerolineas/listar") //Al entrar a la URL se ejecuta toda la clase
public class ListarAerolineaServlet extends HttpServlet {

    @Override //Este igual se ejecuta automaticamente al entrar a la URL
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection con = (Connection) req.getAttribute("conn");//Recuperamos el objeto base de datos y lo casteamos tipo connection
        AerolineaRepository repository = new AerolineaRepository(con);
        List<Aerolinea> aerolineas = repository.listar();
        req.setAttribute("aerolineas",aerolineas);
        getServletContext().getRequestDispatcher("/Aerolinea.jsp").forward(req,resp);
    }

}
