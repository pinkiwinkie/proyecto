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

        DataSource dataSource = Conector.getMySql();
        String query = "{ call crear_usuario(?,?,?,?,?) };";
        Usuario user;
        try (Connection connection = dataSource.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query)
        ) {
            callableStatement.registerOutParameter(1,Types.INTEGER);
//            callableStatement.setInt(2, usuario.getId());
            callableStatement.setNull(2, Types.INTEGER);
            callableStatement.setString(3, usuario.getName());
            callableStatement.setString(4, usuario.getLastName());
            callableStatement.setInt(5, usuario.getIdOficio());
            callableStatement.execute();

            usuario.setId(callableStatement.getInt(1));

            user = Usuario.builder().id(usuario.getId()).name(usuario.getName()).
                    lastName(usuario.getLastName()).idOficio(usuario.getIdOficio()).build();
        }
        return user;
    }

    @Override
    public Usuario updateUsuario(Usuario usuario)  throws SQLException{
        DataSource dataSource = Conector.getMySql();
        String query = "{ ? = call actualizar_usuario(?,?,?,?)}";
        Usuario user;
        try (Connection connection = dataSource.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query)
        ) {
            callableStatement.registerOutParameter(1, Types.INTEGER);
            callableStatement.setInt(2, usuario.getId());
            callableStatement.setString(3, usuario.getName());
            callableStatement.setString(4, usuario.getLastName());
            callableStatement.setInt(5, usuario.getIdOficio());
            callableStatement.execute();
            user = Usuario.builder().id(callableStatement.getInt(1)).name(usuario.getName()).
                    lastName(usuario.getLastName()).idOficio(usuario.getIdOficio()).build();
        }
        return user;
    }

    @Override
    public Usuario deleteUsuario(int id)  throws SQLException{
        DataSource dataSource = Conector.getMySql();
        String query = "{ ? = call eliminar_usuario(?) }";
        Usuario user;
        try (Connection connection = dataSource.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query)
        ) {
            callableStatement.registerOutParameter(1, Types.INTEGER);
            callableStatement.setInt(2, id);
            user = getUserById(id);
            callableStatement.execute();
        }
        return user;
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

    private Usuario getUserById(int id)throws SQLException{
        Usuario usuario = null;
        DataSource ds = Conector.getMySql();
        String query = "SELECT * FROM Usuario where idUsuario = ?";
        try (Connection connection = ds.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                usuario = Usuario.builder().id(resultSet.getInt(1)).name(resultSet.getString(2)).
                        lastName(resultSet.getString(3)).idOficio(resultSet.getInt(4)).build();
            }
        }
        return usuario;
    }
}
