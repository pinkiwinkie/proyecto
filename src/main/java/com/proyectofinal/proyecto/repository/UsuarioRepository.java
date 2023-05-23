package com.proyectofinal.proyecto.repository;

import com.proyectofinal.proyecto.repository.model.Conector;
import com.proyectofinal.proyecto.repository.model.Oficio;
import com.proyectofinal.proyecto.repository.model.Usuario;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioRepository implements IUsuarioRepository {

    @Override
    public Usuario addUsuario(Usuario usuario) throws SQLException{
        boolean insert = false;
        DataSource dataSource = Conector.getMySql();
        String query = "{ call crear_usuario(?,?,?,?,?) };";
        try (Connection connection = dataSource.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query)
        ) {
            callableStatement.registerOutParameter(1,Types.INTEGER);
            callableStatement.setInt(2, usuario.getId());
            callableStatement.setString(3, usuario.getName());
            callableStatement.setString(4, usuario.getLastName());
            callableStatement.setInt(5, usuario.getIdOficio());
            insert = callableStatement.executeUpdate() == 1;
        }
        return insert;
    }

    @Override
    public Usuario updateUsuario(Usuario usuario)  throws SQLException{
        int actualizado = 0;
        DataSource dataSource = Conector.getMySql();
        String query = "{ ? = call actualizar_usuario(?,?,?,?)}";
        try (Connection connection = dataSource.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query)
        ) {
            callableStatement.registerOutParameter(1, Types.INTEGER);
            callableStatement.setInt(2, usuario.getId());
            callableStatement.setString(3, usuario.getName());
            callableStatement.setString(4, usuario.getLastName());
            callableStatement.setInt(5, usuario.getIdOficio());
            actualizado = callableStatement.executeUpdate();
        }
        return actualizado;
    }

    @Override
    public Usuario deleteUsuario(int id)  throws SQLException{
        int deleted = 0;

        DataSource dataSource = Conector.getMySql();
        String query = "{ ? = call eliminar_usuario(?) }";
        try (Connection connection = dataSource.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query)
        ) {
            callableStatement.registerOutParameter(1, Types.INTEGER);
            callableStatement.setInt(2, id);
            deleted = callableStatement.executeUpdate();
        }
        return deleted;
    }

    @Override
    public List<Usuario> getAllUsuarios() throws SQLException {
        ArrayList<Usuario> usuariosDB = new ArrayList<>();
        String query = "{ call obtener_usuarios() }";

        try (Connection connection = Conector.getMySql().getConnection();
             CallableStatement callableStatement = connection.prepareCall(query)
        ) {
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                usuariosDB.add(Usuario.builder().id(rs.getInt(1)).name(rs.getString(2)).lastName(rs.getString(3)).idOficio(rs.getInt(4)).build());
            }
        }
        return usuariosDB;
    }
}
