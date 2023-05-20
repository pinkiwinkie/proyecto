package com.proyectofinal.proyecto.controller;

import com.proyectofinal.proyecto.repository.model.Usuario;
import com.proyectofinal.proyecto.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/apidb")
public class UsuarioController {
    @Autowired
    public UsuarioService service;

    @GetMapping("/usuariosdb/")
    public ResponseEntity<?> getAllUsuarios(){
        try {
            return new ResponseEntity<>(service.getAllUsuarios(), HttpStatus.OK);
        } catch(SQLException e){
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/usuariosdb/")
    public ResponseEntity<?> addUsuario(@RequestBody Usuario usuario) {
        boolean b = service.addUsuario(usuario); //solo lo mandas
        if (!b)
            return new ResponseEntity<>("No se pudo insertar", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
