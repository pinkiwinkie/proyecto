package com.proyectofinal.proyecto.service;

import com.proyectofinal.proyecto.repository.OficioRepository;
import com.proyectofinal.proyecto.repository.model.Oficio;
import com.proyectofinal.proyecto.repository.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class OficioService {
    @Autowired
    public OficioRepository repository;
    public List<Oficio> getAllOficios() throws SQLException {
        return repository.getAllOficios();
    }
}
