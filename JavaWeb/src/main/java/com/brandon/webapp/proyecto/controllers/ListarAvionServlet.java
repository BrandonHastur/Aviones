package com.brandon.webapp.proyecto.controllers;

import com.brandon.webapp.proyecto.models.entities.Avion;
import com.brandon.webapp.proyecto.repositories.AvionRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/aviones/listar")
public class ListarAvionServlet extends HttpServlet {
    @Override //Este igual se ejecuta automaticamente al entrar a la URL
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection con = (Connection) req.getAttribute("conn");//Recuperamos el objeto base de datos y lo casteamos tipo connection
        AvionRepository repository = new AvionRepository(con);
        List<Avion> aviones = repository.listar();
        req.setAttribute("aviones",aviones);
        getServletContext().getRequestDispatcher("/Avion.jsp").forward(req,resp);
    }

}
