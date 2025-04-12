package com.brandon.webapp.proyecto.controllers;

import com.brandon.webapp.proyecto.models.entities.Aeropuerto;
import com.brandon.webapp.proyecto.models.entities.Estatus;
import com.brandon.webapp.proyecto.repositories.AeropuertoRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/aeropuertos/actualizar")
public class ActualizarAeropuertoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection con = (Connection) req.getAttribute("conn");
        AeropuertoRepository repository = new AeropuertoRepository(con);

        long id = 0;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "formato de ID no válido");
        }

        if (id > 0) {
            Optional<Aeropuerto> aeropuerto = repository.buscarPorId(id);
            if (aeropuerto.isPresent()) {
                req.setAttribute("aeropuerto", aeropuerto.get());
                getServletContext().getRequestDispatcher("/editarAeropuerto.jsp").forward(req, resp);
                System.out.println("ID recibido: " + id);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No existe el aeropuerto en la base de datos");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido o nulo");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection con = (Connection) req.getAttribute("conn");
        AeropuertoRepository repository = new AeropuertoRepository(con);

        Long id = Long.parseLong(req.getParameter("id"));
        String nombre = req.getParameter("nombre");
        String codigo = req.getParameter("codigo");
        String latitud = req.getParameter("latitud");
        String longitud = req.getParameter("longitud");
        String pais = req.getParameter("pais");
        int estatusInt = Integer.parseInt(req.getParameter("estatus"));

        Aeropuerto aeropuerto = new Aeropuerto();
        aeropuerto.setId(id);
        aeropuerto.setNombre(nombre);
        aeropuerto.setCodigo(codigo);
        aeropuerto.setLatitud(latitud);
        aeropuerto.setLongitud(longitud);
        aeropuerto.setPais(pais);
        aeropuerto.setEstatus(estatusInt == 1 ? Estatus.DISPONIBLE : Estatus.NO_DISPONIBLE);

        repository.actualizar(aeropuerto);
        resp.sendRedirect(req.getContextPath() + "/aeropuertos/listar");

    }
}
