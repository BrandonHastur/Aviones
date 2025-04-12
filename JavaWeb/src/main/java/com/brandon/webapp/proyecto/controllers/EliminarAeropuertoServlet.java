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
import java.util.Optional;

@WebServlet("/aeropuertos/eliminar")
public class EliminarAeropuertoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection con = (Connection) req.getAttribute("conn");
        AeropuertoRepository repo = new AeropuertoRepository(con);

        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }

        if (id > 0) {
            Optional<Aeropuerto> aeropuerto = repo.buscarPorId(id);
            if (aeropuerto.isPresent()) {
                repo.eliminar(id);
                resp.sendRedirect(req.getContextPath() + "/aeropuertos/listar");
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Aeropuerto no encontrado");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido");
        }
    }
}
