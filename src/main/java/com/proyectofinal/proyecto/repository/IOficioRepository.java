package com.proyectofinal.proyecto.repository;

import com.proyectofinal.proyecto.repository.model.Oficio;

import java.sql.SQLException;
import java.util.List;

public interface IOficioRepository {
    List<Oficio> getAllOficios() throws SQLException;
    //obtenerImagenOficio
}
