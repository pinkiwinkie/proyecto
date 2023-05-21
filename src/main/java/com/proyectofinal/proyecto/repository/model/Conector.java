package com.proyectofinal.proyecto.repository.model;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;

public class Conector {
    public static DataSource getMySql(){
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
//        mysqlDataSource.setUrl("jdbc:mysql://192.168.1.25:3306/java");
//        mysqlDataSource.setUser("helena");
//        mysqlDataSource.setPassword("1234");
        mysqlDataSource.setUrl("jdbc:mysql://localhost:3306/java");
        mysqlDataSource.setUser("root");
//        mysqlDataSource.setPassword("1234");
        return mysqlDataSource;
    }
}
