package com.brandon.webapp.proyecto.controllers;

import com.brandon.webapp.proyecto.models.entities.Avion;
import com.brandon.webapp.proyecto.repositories.AvionRepository;
import java.sql.Connection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/aviones/eliminar")
public class EliminarAvionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection con = (Connection) req.getAttribute("conn");
        AvionRepository repo = new AvionRepository(con);

        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }

        if (id > 0) {
            Optional<Avion> avion = repo.buscarPorId(id);
            if (avion.isPresent()) {
                repo.eliminar(id);
                resp.sendRedirect(req.getContextPath() + "/aviones/listar");
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Avión no encontrado");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido");
        }
    }
}
