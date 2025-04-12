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


@WebServlet("/aeropuertos/alta")
public class RegistrarAeropuertoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/altaAeropuerto.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection con = (Connection) req.getAttribute("conn");
        AeropuertoRepository repository = new AeropuertoRepository(con);

        String nombre = req.getParameter("nombre");
        String codigo = req.getParameter("codigo");
        String latitud = req.getParameter("latitud");
        String longitud = req.getParameter("longitud");
        String pais = req.getParameter("pais");
        String estatusStr = req.getParameter("estatus");

        // Validaciones mínimas
        if (nombre == null || codigo == null || latitud == null || longitud == null || pais == null || estatusStr == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Todos los campos son obligatorios.");
            return;
        }

        int estatusInt = Integer.parseInt(estatusStr);
        Estatus estatus = estatusInt == 1 ? Estatus.DISPONIBLE : Estatus.NO_DISPONIBLE;

        Aeropuerto aeropuerto = new Aeropuerto();
        aeropuerto.setId(0L); // Nuevo registro
        aeropuerto.setNombre(nombre);
        aeropuerto.setCodigo(codigo);
        aeropuerto.setLatitud(latitud);
        aeropuerto.setLongitud(longitud);
        aeropuerto.setPais(pais);
        aeropuerto.setEstatus(estatus);

        // Guardar en la BD
        repository.crear(aeropuerto);

        // Redirigir
        resp.sendRedirect(req.getContextPath() + "/aeropuertos/listar");
    }



//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        //que el boton redirige aqui
//        getServletContext().getRequestDispatcher("/altaAeropuerto.jsp").forward(req,resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Connection con = (Connection) req.getAttribute("conn");
//        AeropuertoRepository repository = new AeropuertoRepository(con);
//        String nombre = req.getParameter("nombre");
//        String codigo = req.getParameter("codigo");
//        String latitud = req.getParameter("latitud");
//        String longitud = req.getParameter("longitud");
//        String pais = req.getParameter("pais");
//        String estatus = req.getParameter("estatus");
//
////        Aeropuerto aeropuerto = new Aeropuerto(0L, nombre,codigo, latitud, longitud, pais,
////                Integer.parseInt(estatus) == 1 ? Estatus.DISPONIBLE : Estatus.NO_DISPONIBLE);
////        System.out.println(aeropuerto);
////        repository.crear(aeropuerto);
//        resp.sendRedirect(req.getContextPath() + "/aeropuertos/listar");
//    }
}
