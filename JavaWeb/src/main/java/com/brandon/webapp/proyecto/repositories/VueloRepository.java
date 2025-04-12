package com.brandon.webapp.proyecto.repositories;

import com.brandon.webapp.proyecto.models.entities.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VueloRepository implements  IRepository<Vuelo>{
    private Connection conn;

    public VueloRepository(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Vuelo> listar() {
        List<Vuelo> vuelos = new ArrayList<>();
        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM VUELO")
        ){
            while(rs.next()){
                vuelos.add(getItem(rs));
            }
        }catch(Exception e){
            throw new RuntimeException(e);
        }
        return vuelos;
    }

    @Override
    public Optional<Vuelo> buscarPorId(Long id) {
        Vuelo vuelo = null;
        try{
            PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM VUELO WHERE ID_VUELO = ?");
            stmt.setLong(1,id);
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    vuelo = this.getItem(rs);
                }
            }
        }catch(SQLException e){
            throw new RuntimeException(e.getMessage());
        }
        return Optional.ofNullable(vuelo);
    }

    @Override
    public void crear(Vuelo elemento) {
        String sql = "{call INSERTAR_ACTUALIZAR_VUELO(?,?,?,?,?,?,?)}";
        try(CallableStatement stmt = conn.prepareCall(sql)){
            stmt.setNull(1,Types.NULL);
            stmt.setString(2,elemento.getCodigoVuelo());
            stmt.setLong(3, elemento.getAvion().getId());
            stmt.setLong(4, elemento.getOrigen().getId());
            stmt.setLong(5, elemento.getDestino().getId());
            stmt.setDate(6,Date.valueOf(elemento.getFechaPrimerVuelo()));
            stmt.setInt(7,elemento.getEstatus() == Estatus.DISPONIBLE ? 1:2);

            stmt.executeUpdate();
        }catch (SQLException e){

            e.getMessage();
        }
    }

    @Override
    public void actualizar(Vuelo elemento) {
        String sql = "{call INSERTAR_ACTUALIZAR_VUELO(?,?,?,?,?,?,?)}";
        try(CallableStatement stmt = conn.prepareCall(sql)){
            stmt.setLong(1,0L);
            stmt.setString(2,elemento.getCodigoVuelo());
            Avion avion = new Avion();
            AvionRepository avionRepository = new AvionRepository(conn);
            stmt.setLong(3, elemento.getAvion().getId());
            stmt.setLong(5, elemento.getOrigen().getId());
            stmt.setLong(7, elemento.getDestino().getId());

            stmt.setDate(4,Date.valueOf(elemento.getFechaPrimerVuelo()));
            stmt.setInt(3,elemento.getEstatus() == Estatus.DISPONIBLE ? 1:2);
            stmt.executeUpdate();

        }catch (SQLException e){
            e.getMessage();
        }
    }

    @Override
    public void eliminar(Long id) {
        try{
            String sql = " {call ELIMINAR_REGISTRO_VUELO(?) }";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setLong(1,id);
            stmt.executeUpdate();
        }catch(SQLException e){
            e.getMessage();
        }
    }

    @Override
    public Vuelo getItem(ResultSet rs) throws SQLException {
        Vuelo vuelo = new Vuelo();
        vuelo.setId(rs.getLong("ID_VUELO"));
        vuelo.setCodigoVuelo(rs.getString("CODIGO_VUELO"));

        Avion avion = null;
        AvionRepository avionRepository = new AvionRepository(conn);
        avion = avionRepository.buscarPorId(rs.getLong("ID_AVION")).get();
        vuelo.setAvion(avion);

        Aeropuerto aeropuertoOrigen = null;
        AeropuertoRepository aeropuertoRepository = new AeropuertoRepository(conn);
        aeropuertoOrigen = aeropuertoRepository.buscarPorId(rs.getLong("ID_ORIGEN")).get();
        vuelo.setOrigen(aeropuertoOrigen);

        Aeropuerto aeropuertoDestino = null;
        aeropuertoDestino = aeropuertoRepository.buscarPorId(rs.getLong("ID_DESTINO")).get();
        vuelo.setDestino(aeropuertoDestino);

        vuelo.setFechaPrimerVuelo(rs.getDate("FECHA_SALIDA").toLocalDate());
        vuelo.setEstatus(rs.getInt("ID_ESTATUS") == 1 ? Estatus.DISPONIBLE : Estatus.NO_DISPONIBLE);

        return vuelo;
    }
}
