package com.proyectofinal.proyecto.repository;

import com.proyectofinal.proyecto.repository.model.Usuario;

import java.sql.SQLException;
import java.util.List;

public interface IUsuarioRepository {
    boolean addUsuario(Usuario usuario) throws SQLException;
    boolean updateUsuario(Usuario usuario) throws SQLException;
    boolean deleteUsuario(int id) throws SQLException;
    Usuario getUsuarioById(int id) throws SQLException;
    List<Usuario> getAllUsuarios() throws SQLException;
}
