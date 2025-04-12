package com.brandon.webapp.proyecto.controllers;

import com.brandon.webapp.proyecto.models.entities.Estatus;
import com.brandon.webapp.proyecto.models.entities.Vuelo;
import com.brandon.webapp.proyecto.repositories.AeropuertoRepository;
import com.brandon.webapp.proyecto.repositories.AvionRepository;
import com.brandon.webapp.proyecto.repositories.VueloRepository;
import jakarta.ejb.Local;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@WebServlet("/vuelos/alta")
public class RegistrarVueloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection con = (Connection) req.getAttribute("conn");

        AvionRepository avionRepo = new AvionRepository(con);
        AeropuertoRepository aeropuertoRepo = new AeropuertoRepository(con);

        req.setAttribute("aviones", avionRepo.listar());
        req.setAttribute("aeropuertos", aeropuertoRepo.listar());

        getServletContext().getRequestDispatcher("/altaVuelo.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection con = (Connection) req.getAttribute("conn");
        VueloRepository vueloRepo = new VueloRepository(con);
        AvionRepository avionRepo = new AvionRepository(con);
        AeropuertoRepository aeropuertoRepo = new AeropuertoRepository(con);

        String codigo = req.getParameter("codigo");
        Long idAvion = Long.parseLong(req.getParameter("idAvion"));
        Long idOrigen = Long.parseLong(req.getParameter("idOrigen"));
        Long idDestino = Long.parseLong(req.getParameter("idDestino"));
        int estatus = Integer.parseInt(req.getParameter("estatus"));
        String fechaSalida = req.getParameter("fechaSalida");

        LocalDate fecha;
        try{
            fecha = LocalDate.parse(fechaSalida, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }catch (DateTimeParseException e){
            fecha = null;
        }

        Vuelo vuelo = new Vuelo();
        vuelo.setCodigoVuelo(codigo);
        vuelo.setAvion(avionRepo.buscarPorId(idAvion).orElse(null));
        vuelo.setOrigen(aeropuertoRepo.buscarPorId(idOrigen).orElse(null));
        vuelo.setDestino(aeropuertoRepo.buscarPorId(idDestino).orElse(null));
        vuelo.setFechaPrimerVuelo(fecha);
        vuelo.setEstatus(estatus == 1 ? Estatus.DISPONIBLE : Estatus.NO_DISPONIBLE);

        vueloRepo.crear(vuelo);
        resp.sendRedirect(req.getContextPath() + "/vuelos/listar");
    }
}
