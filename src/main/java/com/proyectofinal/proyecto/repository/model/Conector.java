package com.proyectofinal.proyecto.repository.model;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Conector {
    public static DataSource getMySql(){
        Properties properties = new Properties();
        MysqlDataSource mysqlDataSource = null;

        try(FileInputStream fileInputStream = new FileInputStream("src/main/resources/application.properties")){
            properties.load(fileInputStream);
            mysqlDataSource = new MysqlDataSource();
            mysqlDataSource.setUrl(properties.getProperty("MYSQL_DB_URL"));
            mysqlDataSource.setUser(properties.getProperty("MYSQL_DB_USERNAME"));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        mysqlDataSource.setUrl("jdbc:mysql://192.168.1.25:3306/java");
//        mysqlDataSource.setUser("helena");
//        mysqlDataSource.setPassword("1234");
//        mysqlDataSource.setUrl("jdbc:mysql://localhost:3306/java");
//        mysqlDataSource.setUser("root");
//        mysqlDataSource.setPassword("");
        return mysqlDataSource;
    }
}
