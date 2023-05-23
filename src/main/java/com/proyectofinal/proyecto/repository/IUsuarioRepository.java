package com.proyectofinal.proyecto.repository;

import com.proyectofinal.proyecto.repository.model.Oficio;
import com.proyectofinal.proyecto.repository.model.Usuario;
import java.sql.SQLException;
import java.util.List;

public interface IUsuarioRepository {
    Usuario addUsuario(Usuario usuario) throws SQLException;
    Usuario updateUsuario(Usuario usuario) throws SQLException;
    Usuario deleteUsuario(int id) throws SQLException;
    List<Usuario> getAllUsuarios() throws SQLException;
}
