package com.brandon.webapp.proyecto.controllers;

import com.brandon.webapp.proyecto.models.entities.*;
import com.brandon.webapp.proyecto.repositories.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.Optional;

@WebServlet("/aviones/alta")
public class RegistrarAvionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection con = (Connection) req.getAttribute("conn");
        AvionRepository avionRepo = new AvionRepository(con);
        AerolineaRepository aerolineaRepo = new AerolineaRepository(con);

//        Long idAvion;
//        try {
//            idAvion = Long.parseLong(req.getParameter("id"));
//        } catch (NumberFormatException e) {
//            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido.");
//            return;
//        }
//
//        Optional<Avion> avion = avionRepo.buscarPorId(idAvion);
//        if (avion.isPresent()) {
//            req.setAttribute("avion", avion.get());
//            req.setAttribute("aerolineas", aerolineaRepo.listar());
//            getServletContext().getRequestDispatcher("/altaAvion.jsp").forward(req, resp);
//        } else {
//            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Avión no encontrado.");
//        }

        req.setAttribute("aviones", avionRepo.listar());
        req.setAttribute("aerolineas", aerolineaRepo.listar());
        getServletContext().getRequestDispatcher("/altaAvion.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection con = (Connection) req.getAttribute("conn");
        AvionRepository avionRepo = new AvionRepository(con);
        AerolineaRepository aerolineaRepo = new AerolineaRepository(con);

        Long id = Long.parseLong(req.getParameter("id"));
        String numRegistro = req.getParameter("numRegistro");
        String tipo = req.getParameter("tipo");
        String codigoModelo = req.getParameter("codigoModelo");
        int capacidad = Integer.parseInt(req.getParameter("capacidad"));
        LocalDate fechaPrimerVuelo = LocalDate.parse(req.getParameter("fechaPrimerVuelo"));
        int estatus = Integer.parseInt(req.getParameter("estatus"));
        long aerolinea = Long.parseLong(req.getParameter("aerolinea"));

        Estatus idEstatus = (estatus == 1) ? Estatus.DISPONIBLE : Estatus.NO_DISPONIBLE;
        Aerolinea idSerolinea = aerolineaRepo.buscarPorId(aerolinea).orElse(null);

        Avion avion = new Avion(id, numRegistro, tipo, codigoModelo, capacidad, fechaPrimerVuelo, idEstatus, idSerolinea);
        avionRepo.crear(avion);

        resp.sendRedirect(req.getContextPath() + "/aviones/listar");
    }
}
