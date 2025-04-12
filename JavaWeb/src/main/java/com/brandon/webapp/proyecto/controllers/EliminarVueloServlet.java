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
import java.util.Optional;

@WebServlet("/vuelos/eliminar")
public class EliminarVueloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection con = (Connection) req.getAttribute("conn");
        VueloRepository repo = new VueloRepository(con);

        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }

        if (id > 0) {
            Optional<Vuelo> vuelo = repo.buscarPorId(id);
            if (vuelo.isPresent()) {
                repo.eliminar(id);
                resp.sendRedirect(req.getContextPath() + "/vuelos/listar");
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Vuelo no encontrado");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido");
        }
    }
}
