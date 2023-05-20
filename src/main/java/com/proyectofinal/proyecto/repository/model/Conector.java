package com.proyectofinal.proyecto.repository.model;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;

public class Conector {
    public static DataSource getMySql(){
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        return mysqlDataSource;
    }
}
