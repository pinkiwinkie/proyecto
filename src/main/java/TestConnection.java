import com.proyectofinal.proyecto.repository.model.Conector;

import javax.sql.DataSource;
import java.sql.Connection;

public class TestConnection {
    public static void main(String[] args) {
        Connection connection;
        try{
            DataSource dataSource = Conector.getMySql();
            connection = dataSource.getConnection();
            if (connection != null)
                System.out.println("acceso exitoso");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
