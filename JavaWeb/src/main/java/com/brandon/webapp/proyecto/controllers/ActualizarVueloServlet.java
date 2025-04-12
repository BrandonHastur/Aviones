package com.brandon.webapp.proyecto.controllers;

import com.brandon.webapp.proyecto.models.entities.Aeropuerto;
import com.brandon.webapp.proyecto.models.entities.Avion;
import com.brandon.webapp.proyecto.models.entities.Estatus;
import com.brandon.webapp.proyecto.models.entities.Vuelo;
import com.brandon.webapp.proyecto.repositories.AeropuertoRepository;
import com.brandon.webapp.proyecto.repositories.AvionRepository;
import com.brandon.webapp.proyecto.repositories.VueloRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@WebServlet("/vuelos/actualizar")
public class ActualizarVueloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection con = (Connection) req.getAttribute("conn");
        VueloRepository vueloRepo = new VueloRepository(con);
        AvionRepository avionRepo = new AvionRepository(con);
        AeropuertoRepository aeropuertoRepo = new AeropuertoRepository(con);

        long id = 0;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Formato de ID no válido");
            return;
        }

        if (id > 0) {
            Optional<Vuelo> vueloOpt = vueloRepo.buscarPorId(id);
            if (vueloOpt.isPresent()) {
                Vuelo vuelo = vueloOpt.get();
                List<Avion> aviones = avionRepo.listar();
                List<Aeropuerto> aeropuertos = aeropuertoRepo.listar();

                req.setAttribute("vuelo", vuelo);
                req.setAttribute("aviones", aviones);
                req.setAttribute("aeropuertos", aeropuertos);

                getServletContext().getRequestDispatcher("/editarVuelo.jsp").forward(req, resp);
                System.out.println("ID recibido: " + id);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No existe el vuelo en la base de datos");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido o nulo");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection con = (Connection) req.getAttribute("conn");
        VueloRepository vueloRepo = new VueloRepository(con);
        AvionRepository avionRepo = new AvionRepository(con);
        AeropuertoRepository aeropuertoRepo = new AeropuertoRepository(con);

        Long id = Long.parseLong(req.getParameter("id"));
        String codigoVuelo = req.getParameter("codigoVuelo");
        Long avionId = Long.parseLong(req.getParameter("idAvion"));
        Long origenId = Long.parseLong(req.getParameter("idOrigen"));
        Long destinoId = Long.parseLong(req.getParameter("idDestino"));
        String fechaSalida = req.getParameter("fechaSalida");
        int estatusInt = Integer.parseInt(req.getParameter("estatus"));

        Optional<Avion> optionalAvion = avionRepo.buscarPorId(avionId);
        if (optionalAvion.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Avión no encontrado");
            return;
        }

        Optional<Aeropuerto> optionalOrigen = aeropuertoRepo.buscarPorId(origenId);
        if (optionalOrigen.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Aeropuerto de origen no encontrado");
            return;
        }

        Optional<Aeropuerto> optionalDestino = aeropuertoRepo.buscarPorId(destinoId);
        if (optionalDestino.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Aeropuerto de destino no encontrado");
            return;
        }

        Vuelo vuelo = new Vuelo();
        vuelo.setId(id);
        vuelo.setCodigoVuelo(codigoVuelo);
        vuelo.setAvion(optionalAvion.get());
        vuelo.setOrigen(optionalOrigen.get());
        vuelo.setDestino(optionalDestino.get());
        vuelo.setFechaPrimerVuelo(LocalDate.parse(fechaSalida));
        vuelo.setEstatus(estatusInt == 1 ? Estatus.DISPONIBLE : Estatus.NO_DISPONIBLE);

        vueloRepo.actualizar(vuelo);
        resp.sendRedirect(req.getContextPath() + "/vuelos/listar");
    }
}