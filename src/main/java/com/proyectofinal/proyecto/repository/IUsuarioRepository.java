package com.proyectofinal.proyecto.repository;

import com.proyectofinal.proyecto.repository.model.Oficio;
import com.proyectofinal.proyecto.repository.model.Usuario;
import java.sql.SQLException;
import java.util.List;

public interface IUsuarioRepository {
    boolean addUsuario(Usuario usuario) throws SQLException;
    int updateUsuario(Usuario usuario) throws SQLException;
    int deleteUsuario(int id) throws SQLException;
    List<Usuario> getAllUsuarios() throws SQLException;
    List<Oficio> getAllOficios() throws SQLException;
    //obtenerImagenOficio
}
