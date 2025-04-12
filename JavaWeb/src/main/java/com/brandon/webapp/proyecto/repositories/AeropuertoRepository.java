package com.brandon.webapp.proyecto.repositories;

import com.brandon.webapp.proyecto.models.entities.Aeropuerto;
import com.brandon.webapp.proyecto.models.entities.Estatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AeropuertoRepository implements IRepository<Aeropuerto> {
    private Connection conn;

    public AeropuertoRepository(Connection conn){
        this.conn = conn;
    }

    @Override
    public List<Aeropuerto> listar() {
        List<Aeropuerto> aeropuertos = new ArrayList<>();
        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM AEROPUERTO")
        ){
            while(rs.next()){
                aeropuertos.add(getItem(rs));
            }
        }catch(Exception e){
            throw new RuntimeException(e);
        }
        return aeropuertos;
    }

    @Override
    public Optional<Aeropuerto> buscarPorId(Long id) {
        Aeropuerto aeropuerto = null;
        try{
            PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM AEROPUERTO WHERE ID_AEROPUERTO = ?");
            stmt.setLong(1,id);
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    aeropuerto = this.getItem(rs);
                }
            }
        }catch(SQLException e){
            throw new RuntimeException(e.getMessage());
        }
        return Optional.ofNullable(aeropuerto);
    }

    @Override
    public void crear(Aeropuerto elemento) {
        String sql = "{call INSERTAR_ACTUALIZAR_AEROPUERTO(?,?,?,?,?,?,?)}";//numero de parametros
        try(CallableStatement stmt = conn.prepareCall(sql)){
            stmt.setNull(1, Types.NULL);
            stmt.setString(2,elemento.getNombre());
            stmt.setString(3,elemento.getCodigo());
            stmt.setString(4,elemento.getLatitud());
            stmt.setString(5,elemento.getLongitud());
            stmt.setString(6,elemento.getPais());
            stmt.setInt(7,elemento.getEstatus() == Estatus.DISPONIBLE ? 1:2);
            stmt.executeUpdate();

        }catch (SQLException e){
            e.getMessage();
        }
    }

    @Override
    public void actualizar(Aeropuerto elemento) {
        String sql = "{call INSERTAR_ACTUALIZAR_AEROPUERTO(?,?,?,?,?,?,?)}";//numero de parametros
        try(CallableStatement stmt = conn.prepareCall(sql)){
            stmt.setNull(1, Types.NULL);
            stmt.setString(2,elemento.getNombre());
            stmt.setString(3,elemento.getCodigo());
            stmt.setString(4,elemento.getLatitud());
            stmt.setString(5,elemento.getLongitud());
            stmt.setString(6,elemento.getPais());
            stmt.setInt(7,elemento.getEstatus() == Estatus.DISPONIBLE ? 1:2);
            stmt.executeUpdate();

        }catch (SQLException e){
            e.getMessage();
        }
    }

    @Override
    public void eliminar(Long id) {
        try{
            String sql = " {call ELIMINAR_REGISTRO_AEROPUERTO(?) }";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setLong(1,id);
            stmt.executeUpdate();
        }catch(SQLException e){
            e.getMessage();
        }
    }

    @Override
    public Aeropuerto getItem(ResultSet rs) throws SQLException {
        Aeropuerto aeropuerto = new Aeropuerto();
        aeropuerto.setId(rs.getLong("ID_AEROPUERTO"));
        aeropuerto.setNombre(rs.getString("NOMBRE"));
        aeropuerto.setCodigo(rs.getString("CODIGO"));
        aeropuerto.setLatitud(rs.getString("LATITUD"));
        aeropuerto.setLongitud(rs.getString("LONGITUD"));
        aeropuerto.setPais(rs.getString("PAIS"));
        aeropuerto.setEstatus(rs.getInt("ID_ESTATUS") == 1 ? Estatus.DISPONIBLE : Estatus.NO_DISPONIBLE);
        return aeropuerto;

    }

}
