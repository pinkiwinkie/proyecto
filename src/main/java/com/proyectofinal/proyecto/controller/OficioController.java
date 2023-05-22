package com.proyectofinal.proyecto.controller;

import com.proyectofinal.proyecto.service.OficioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController //maneja las rutas
@RequestMapping("/apidb/")
public class OficioController {
    @Autowired
    public OficioService service;

    @GetMapping("/oficiosdb/")
    public ResponseEntity<?> getAllUsuarios(){
        try {
            return new ResponseEntity<>(service.getAllOficios(), HttpStatus.OK);
        } catch(SQLException e){
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
