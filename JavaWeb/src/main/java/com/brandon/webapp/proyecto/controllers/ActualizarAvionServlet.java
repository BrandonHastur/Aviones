package com.brandon.webapp.proyecto.controllers;

import com.brandon.webapp.proyecto.models.entities.Aerolinea;
import com.brandon.webapp.proyecto.models.entities.Avion;
import com.brandon.webapp.proyecto.models.entities.Estatus;
import com.brandon.webapp.proyecto.repositories.AerolineaRepository;
import com.brandon.webapp.proyecto.repositories.AvionRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@WebServlet("/aviones/actualizar")
public class ActualizarAvionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection con = (Connection) req.getAttribute("conn");
        AvionRepository avionRepo = new AvionRepository(con);
        AerolineaRepository aerolineaRepo = new AerolineaRepository(con);

        long id = 0;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Formato de ID no válido");
            return;
        }

        if (id > 0) {
            Optional<Avion> avionOpt = avionRepo.buscarPorId(id);
            if (avionOpt.isPresent()) {
                Avion avion = avionOpt.get();
                List<Aerolinea> aerolineas = aerolineaRepo.listar();

                req.setAttribute("avion", avion);
                req.setAttribute("aerolineas", aerolineas);

                getServletContext().getRequestDispatcher("/editarAvion.jsp").forward(req, resp);
                System.out.println("ID recibido: " + id);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No existe el avión en la base de datos");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido o nulo");
        }
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
        String capacidad = req.getParameter("capacidad");
        String fechaPrimerVuelo = req.getParameter("fechaPrimerVuelo");
        int estatusInt = Integer.parseInt(req.getParameter("estatus"));
        Long aerolineaId = Long.parseLong(req.getParameter("aerolinea"));

        Optional<Aerolinea> optionalAerolinea = aerolineaRepo.buscarPorId(aerolineaId);
        if (optionalAerolinea.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Aerolínea no encontrada");
            return;
        }

        Aerolinea aerolinea = optionalAerolinea.get();

        Avion avion = new Avion();
        avion.setId(id);
        avion.setNumRegistro(numRegistro);
        avion.setTipo(tipo);
        avion.setCodigoModelo(codigoModelo);
        avion.setCapacidad(Integer.parseInt(capacidad));
        avion.setFechaPrimerVuelo(java.time.LocalDate.parse(fechaPrimerVuelo));
        avion.setEstatus(estatusInt == 1 ? Estatus.DISPONIBLE : Estatus.NO_DISPONIBLE);
        avion.setAerolinea(aerolinea);

        avionRepo.actualizar(avion);
        resp.sendRedirect(req.getContextPath() + "/aviones/listar");
    }
}
