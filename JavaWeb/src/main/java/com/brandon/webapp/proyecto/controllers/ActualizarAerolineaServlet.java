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
import java.util.Optional;

@WebServlet("/aerolineas/actualizar")
public class ActualizarAerolineaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection con = (Connection) req.getAttribute("conn");
        AerolineaRepository repository = new AerolineaRepository(con);

        long id = 0;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "formato de ID no válido");
        }

        if (id > 0) {
            Optional<Aerolinea> aerolinea = repository.buscarPorId(id);
            if (aerolinea.isPresent()) {
                req.setAttribute("aerolinea", aerolinea.get());
                getServletContext().getRequestDispatcher("/editarAerolinea.jsp").forward(req, resp);
                System.out.println("ID recibido: " + id);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No existe la aerolínea en la base de datos");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido o nulo");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection con = (Connection) req.getAttribute("conn");
        AerolineaRepository repository = new AerolineaRepository(con);

        Long id = Long.parseLong(req.getParameter("id"));
        String nombre = req.getParameter("nombre");
        String iata = req.getParameter("iata");
        String pais = req.getParameter("pais");
        String fundacion = req.getParameter("fundacion");
        int estatusInt = Integer.parseInt(req.getParameter("estatus"));

        Aerolinea aerolinea = new Aerolinea();
        aerolinea.setId(id);
        aerolinea.setNombre(nombre);
        aerolinea.setIata(iata);
        aerolinea.setPais(pais);
        aerolinea.setFechaFundacion(java.time.LocalDate.parse(fundacion));
        aerolinea.setEstatus(estatusInt == 1 ? Estatus.DISPONIBLE : Estatus.NO_DISPONIBLE);

        repository.actualizar(aerolinea);
        resp.sendRedirect(req.getContextPath() + "/aerolineas/listar");

    }

}
