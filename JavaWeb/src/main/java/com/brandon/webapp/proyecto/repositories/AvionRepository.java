package com.brandon.webapp.proyecto.repositories;

import com.brandon.webapp.proyecto.models.entities.Aerolinea;
import com.brandon.webapp.proyecto.models.entities.Avion;
import com.brandon.webapp.proyecto.models.entities.Estatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AvionRepository implements IRepository<Avion> {
    private Connection conn;

    public AvionRepository(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Avion> listar() {
        List<Avion> aviones = new ArrayList<>();
        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM AVION")
        ){
            while(rs.next()){
                aviones.add(getItem(rs));
            }
        }catch(Exception e){
            throw new RuntimeException(e);
        }
        return aviones;
    }

    @Override
    public Optional<Avion> buscarPorId(Long id) {
        Avion avion = null;
        try{
            PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM AVION WHERE ID_AVION = ?");
            stmt.setLong(1,id);
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    avion = this.getItem(rs);
                }
            }
        }catch(SQLException e){
            throw new RuntimeException(e.getMessage());
        }
        return Optional.ofNullable(avion);
    }

    @Override
    public void crear(Avion elemento) {
        String sql = "{call INSERTAR_ACTUALIZAR_AVION(?,?,?,?,?,?,?,?)};";
        try(CallableStatement stmt = conn.prepareCall(sql)){
            stmt.setLong(1, 0L);
            stmt.setString(2, elemento.getNumRegistro());
            stmt.setString(3, elemento.getTipo());
            stmt.setInt(4, elemento.getEstatus() == Estatus.DISPONIBLE ? 1 : 2);
            stmt.setString(5, elemento.getCodigoModelo());
            stmt.setInt(6, elemento.getCapacidad());
            stmt.setDate(7, Date.valueOf(elemento.getFechaPrimerVuelo()));
            stmt.setLong(8, elemento.getAerolinea().getId());

//            stmt.setLong(1,0L);
//            stmt.setString(2,elemento.getNumRegistro());//ESTE DEBERIA SER TIPO INT O STRING? EN DB es number
//            stmt.setString(3,elemento.getTipo());
//            stmt.setString(5,elemento.getCodigoModelo());
//            stmt.setInt(5,elemento.getCapacidad());
//            stmt.setDate(6,Date.valueOf(elemento.getFechaPrimerVuelo()));
//            stmt.setInt(4,elemento.getEstatus() == Estatus.DISPONIBLE ? 1:2);
//            stmt.setLong(5,elemento.getAerolinea().getId());
            stmt.executeUpdate();

        }catch (SQLException e){
            e.getMessage();
        }
    }

    @Override
    public void actualizar(Avion elemento) {
        String sql = "{call INSERTAR_ACTUALIZAR_AVION(?,?,?,?,?,?,?,?)}";
        try(CallableStatement stmt = conn.prepareCall(sql)){
            stmt.setLong(1, elemento.getId());
            stmt.setString(2, elemento.getNumRegistro());
            stmt.setString(3, elemento.getTipo());
            stmt.setString(5, elemento.getCodigoModelo());
            stmt.setInt(6, elemento.getCapacidad());
            stmt.setDate(7, Date.valueOf(elemento.getFechaPrimerVuelo()));
            stmt.setInt(4, elemento.getEstatus() == Estatus.DISPONIBLE ? 1 : 2);
            stmt.setLong(8, elemento.getAerolinea().getId());
            stmt.executeUpdate();

        }catch (SQLException e){
            e.getMessage();
        }
    }

    @Override
    public void eliminar(Long id) {
        try{
            String sql = " {call ELIMINAR_REGISTRO_AVION(?) }";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setLong(1,id);
            stmt.executeUpdate();
        }catch(SQLException e){
            e.getMessage();
        }
    }


    @Override
    public Avion getItem(ResultSet rs) throws SQLException {
        Avion avion = new Avion();
        avion.setId(rs.getLong("ID_AVION"));
        avion.setNumRegistro(rs.getString("NUM_REGISTRO"));
        avion.setTipo(rs.getString("TIPO"));
        avion.setCodigoModelo(rs.getString("CODIGO_MODELO"));
        avion.setCapacidad(rs.getInt("CAPACIDAD"));
        avion.setFechaPrimerVuelo(rs.getDate("FECHA_PRIMER_VUELO").toLocalDate());
        avion.setEstatus(rs.getInt("ID_ESTATUS") == 1 ? Estatus.DISPONIBLE : Estatus.NO_DISPONIBLE);
        Aerolinea aerolinea = new Aerolinea();
        AerolineaRepository aerolineaRepository = new AerolineaRepository(conn);
        aerolinea = aerolineaRepository.buscarPorId(rs.getLong("ID_AEROLINEA")).get();
        avion.setAerolinea(aerolinea);
        return avion;
    }

}
