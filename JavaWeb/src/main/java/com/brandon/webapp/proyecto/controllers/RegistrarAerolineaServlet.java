package com.brandon.webapp.proyecto.controllers;

import com.brandon.webapp.proyecto.models.entities.Aerolinea;
import com.brandon.webapp.proyecto.models.entities.Estatus;
import com.brandon.webapp.proyecto.repositories.AerolineaRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;

@WebServlet("/aerolineas/alta")
public class RegistrarAerolineaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection con = (Connection) req.getAttribute("conn");
        AerolineaRepository repo = new AerolineaRepository(con);

        long id = 0L;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Formato de ID no válido");
            return;
        }

        if (id > 0) {
            Optional<Aerolinea> aerolinea = repo.buscarPorId(id);
            if (aerolinea.isPresent()) {
                req.setAttribute("aerolinea", aerolinea.get());
                getServletContext().getRequestDispatcher("/altaAerolinea.jsp").forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No existe la aerolínea en la base de datos");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Error el id es null");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection con = (Connection) req.getAttribute("conn");
        AerolineaRepository repo = new AerolineaRepository(con);

        Long id = Long.valueOf(req.getParameter("id"));
        String nombre = req.getParameter("nombre");
        String lata = req.getParameter("iata");
        String estatus = req.getParameter("estatus");
        String pais = req.getParameter("pais");
        String fundacion = req.getParameter("fundacion");
        LocalDate fecha = null;

        try {
            if (fundacion != null && !fundacion.isBlank()) {
                fecha = LocalDate.parse(fundacion);
                if (fecha.isAfter(LocalDate.now())) {
                    throw new IllegalArgumentException("La fecha de fundación no puede ser futura.");
                }
            }
        } catch (DateTimeParseException | IllegalArgumentException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/altaAerolinea.jsp").forward(req, resp);
            return;
        }

        Aerolinea aerolinea = new Aerolinea(id, nombre, lata,
                Integer.parseInt(estatus) == 1 ? Estatus.DISPONIBLE : Estatus.NO_DISPONIBLE, pais, fecha);
        repo.actualizar(aerolinea);
        resp.sendRedirect(req.getContextPath() + "/aerolineas/listar");
    }
}

