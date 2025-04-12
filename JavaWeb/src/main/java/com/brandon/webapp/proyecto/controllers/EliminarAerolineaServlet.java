package com.brandon.webapp.proyecto.controllers;

import com.brandon.webapp.proyecto.models.entities.Aerolinea;
import com.brandon.webapp.proyecto.models.entities.Vuelo;
import com.brandon.webapp.proyecto.repositories.AerolineaRepository;
import com.brandon.webapp.proyecto.repositories.VueloRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/aerolineas/eliminar")
public class EliminarAerolineaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection con = (Connection) req.getAttribute("conn");
        AerolineaRepository repo = new AerolineaRepository(con);

        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }

        if (id > 0) {
            Optional<Aerolinea> aerolinea = repo.buscarPorId(id);
            if (aerolinea.isPresent()) {
                repo.eliminar(id);
                resp.sendRedirect(req.getContextPath() + "/aerolineas/listar");
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Aerolinea no encontrada");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválida");
        }
    }
}
