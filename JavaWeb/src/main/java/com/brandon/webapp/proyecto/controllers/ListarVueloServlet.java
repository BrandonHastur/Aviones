package com.brandon.webapp.proyecto.controllers;

import com.brandon.webapp.proyecto.models.entities.Vuelo;
import com.brandon.webapp.proyecto.repositories.VueloRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/vuelos/listar")
public class ListarVueloServlet extends HttpServlet {
    @Override //Este igual se ejecuta automaticamente al entrar a la URL
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection con = (Connection) req.getAttribute("conn");//Recuperamos el objeto base de datos y lo casteamos tipo connection
        VueloRepository repository = new VueloRepository(con);
        List<Vuelo> vuelos = repository.listar();
        req.setAttribute("vuelos",vuelos);
        getServletContext().getRequestDispatcher("/Vuelo.jsp").forward(req,resp);
    }
}
