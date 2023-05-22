package com.proyectofinal.proyecto.repository;

import com.proyectofinal.proyecto.repository.model.Conector;
import com.proyectofinal.proyecto.repository.model.Oficio;
import com.proyectofinal.proyecto.repository.model.Usuario;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OficioRepository implements IOficioRepository{
    @Override
    public List<Oficio> getAllOficios() throws SQLException {
        ArrayList<Oficio> oficiosDB = new ArrayList<>();
        String query = "{ ? = call obtener_oficios(?)}";

        try (Connection connection = Conector.getMySql().getConnection();
             Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(query)
        ) {
            while (rs.next()) {
               // oficiosDB.add();
            }
        }
        return oficiosDB;
    }
}
