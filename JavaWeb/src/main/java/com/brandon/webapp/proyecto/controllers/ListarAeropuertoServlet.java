package com.brandon.webapp.proyecto.controllers;

import com.brandon.webapp.proyecto.models.entities.Aeropuerto;
import com.brandon.webapp.proyecto.repositories.AeropuertoRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/aeropuertos/listar")
public class ListarAeropuertoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection con = (Connection) req.getAttribute("conn");//Recuperamos el objeto base de datos y lo casteamos tipo connection
        AeropuertoRepository repository = new AeropuertoRepository(con);
        List<Aeropuerto> aeropuertos = repository.listar();
        req.setAttribute("aeropuertos",aeropuertos);
        getServletContext().getRequestDispatcher("/Aeropuerto.jsp").forward(req,resp);
    }
}
