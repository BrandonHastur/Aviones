package com.brandon.webapp.proyecto.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

//6️⃣ Una interfaz genérica para definir operaciones comunes de repositorios.
//Este archivo describe:
//Qué métodos están estandarizados en todos los repos (CRUD).
//Cómo AerolineaRepository los implementa.

public interface IRepository<T> {
    List<T> listar();
    Optional<T> buscarPorId(Long id);
    void crear(T elemento);
    void actualizar(T elemento);
    void eliminar(Long id);
    T getItem(ResultSet rs) throws SQLException;

}
