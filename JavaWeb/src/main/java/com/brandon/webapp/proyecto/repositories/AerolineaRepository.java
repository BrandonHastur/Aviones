package com.brandon.webapp.proyecto.repositories;

import com.brandon.webapp.proyecto.models.entities.Aerolinea;
import com.brandon.webapp.proyecto.models.entities.Estatus;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//4️⃣ Esta clase habla directamente con la base de datos.
//Este archivo describe:
//Cómo se consultan, insertan, actualizan y eliminan aerolíneas.
//Cómo se transforma una fila (ResultSet) en un objeto Java (Aerolinea).

public class AerolineaRepository implements IRepository<Aerolinea> {
    private Connection conn;

    public AerolineaRepository(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Aerolinea> listar() {
        List<Aerolinea> aerolineas = new ArrayList<>();
        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM AEROLINEA")
        ){
            while(rs.next()){
                aerolineas.add(getItem(rs));
            }
        }catch(Exception e){
            throw new RuntimeException(e);
        }
        return aerolineas;
    }

    @Override
    public Optional<Aerolinea> buscarPorId(Long id) {
        Aerolinea aerolinea = null;
        try{
            PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM AEROLINEA WHERE ID_AEROLINEA = ?");
            stmt.setLong(1,id);
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    aerolinea = this.getItem(rs);
                }
            }
        }catch(SQLException e){
            throw new RuntimeException(e.getMessage());
        }
        return Optional.ofNullable(aerolinea);
    }

    @Override
    public void crear(Aerolinea elemento) {
        String sql = "{call INSERTAR_ACTUALIZAR_AEROLINEA(?,?,?,?,?,?)}";
        try(CallableStatement stmt = conn.prepareCall(sql)){
            stmt.setLong(1,Types.NULL);
            stmt.setString(2,elemento.getNombre());
            stmt.setString(3,elemento.getIata());
            stmt.setString(4,elemento.getPais());
            stmt.setDate(5,Date.valueOf(elemento.getFechaFundacion()));
            stmt.setInt(6,elemento.getEstatus() == Estatus.DISPONIBLE ? 1:2);
            stmt.executeUpdate();

        }catch (SQLException e){
            e.getMessage();
        }

    }

    @Override
    public void actualizar(Aerolinea elemento) {
        String sql = "{call INSERTAR_ACTUALIZAR_AEROLINEA(?,?,?,?,?,?)}";
        try(CallableStatement stmt = conn.prepareCall(sql)){
            stmt.setLong(1,elemento.getId());
            stmt.setString(2,elemento.getNombre());
            stmt.setString(3,elemento.getIata());
            stmt.setString(4,elemento.getPais());
            stmt.setDate(5,Date.valueOf(elemento.getFechaFundacion()));
            stmt.setInt(6,elemento.getEstatus() == Estatus.DISPONIBLE ? 1:2);
            System.out.println("Aerolinea: "+elemento);
            stmt.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
            e.getMessage();
        }

    }

    @Override
    public void eliminar(Long id) {
        try{
            String sql = " {call ELIMINAR_REGISTRO_AEROLINEA(?) }";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setLong(1,id);
            stmt.executeUpdate();
        }catch(SQLException e){
            e.getMessage();
        }
    }

    //devuelve un objeto tabla desde oracle ResultSet y transformamos a un objeto java cada elemento
    @Override
    public Aerolinea getItem(ResultSet rs) throws SQLException {
        Aerolinea aerolinea = new Aerolinea();
        aerolinea.setId(rs.getLong("ID_AEROLINEA"));
        aerolinea.setNombre(rs.getString("NOMBRE"));
        aerolinea.setIata(rs.getString("IATA"));
        aerolinea.setPais(rs.getString("PAIS"));
        aerolinea.setFechaFundacion(rs.getDate("FECHA_FUNDACION").toLocalDate());
        aerolinea.setEstatus(rs.getInt("ID_ESTATUS") == 1 ? Estatus.DISPONIBLE : Estatus.NO_DISPONIBLE);
        return aerolinea;

    }

}
