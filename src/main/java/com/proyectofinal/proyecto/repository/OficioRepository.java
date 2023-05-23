package com.proyectofinal.proyecto.repository;

import com.proyectofinal.proyecto.repository.model.Conector;
import com.proyectofinal.proyecto.repository.model.Oficio;
import com.proyectofinal.proyecto.repository.model.Usuario;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OficioRepository implements IOficioRepository{
    @Override
    public List<Oficio> getAllOficios() throws SQLException {
        ArrayList<Oficio> oficiosDB = new ArrayList<>();
        String query = "{ call obtener_oficios(?)}";

        try (Connection connection = Conector.getMySql().getConnection();
             CallableStatement callableStatement = connection.prepareCall(query)
        ) {
            callableStatement.setNull(1,0);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                oficiosDB.add(Oficio.builder().id(rs.getInt(1)).description(rs.getString(2)).build());
            }
        }
        return oficiosDB;
    }
}
