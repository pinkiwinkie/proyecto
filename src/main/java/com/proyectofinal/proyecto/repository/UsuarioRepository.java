package com.proyectofinal.proyecto.repository;

import com.proyectofinal.proyecto.repository.model.Conector;
import com.proyectofinal.proyecto.repository.model.Usuario;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioRepository implements IUsuarioRepository{

    @Override
    public boolean addUsuario(Usuario usuario) {
        boolean insert = false;
        DataSource dataSource = Conector.getMySql();
        String query = "{ call crear_usuario(?,?,?,?,?) };";
        try(Connection connection = dataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall(query)
        ){
            callableStatement.setInt(2,usuario.getId());
            callableStatement.setString(3, usuario.getName());
            callableStatement.setString(4, usuario.getLastName());
            callableStatement.setInt(5,usuario.getIdOficio());
            insert = callableStatement.executeUpdate() == 1;
        }catch (SQLException e) { e.printStackTrace(); }
        return insert;
    }

    @Override
    public boolean updateUsuario(Usuario usuario) {
        return false;
    }

    @Override
    public boolean deleteUsuario(int id) {
        return false;
    }

    @Override
    public Usuario getUsuarioById(int id) {
        return null;
    }

    @Override
    public List<Usuario> getAllUsuarios() throws SQLException {
        ArrayList<Usuario> usuariosDB = new ArrayList<>();
        String query = "SELECT * FROM Usuario";

        try(Connection connection = Conector.getMySql().getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query)){

            while(rs.next()){
                usuariosDB.add(Usuario.builder().id(rs.getInt(1)).name(rs.getString(2)).lastName(rs.getString(3)).idOficio(rs.getInt(4)).build());
            }
        }

        return usuariosDB;
    }
}
