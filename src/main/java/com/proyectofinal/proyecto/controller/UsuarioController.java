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
        if (!b) //el condicional esta mal porque lo añadas o no siempre dice que no se pudo insertar
            return new ResponseEntity<>("No se pudo insertar", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(b, HttpStatus.OK);
    }

    @DeleteMapping("/usuariosdb/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable("id") int id){
        int i = service.deleteUsuario(id);
        if (i == 0)
            return new ResponseEntity<>("No se pudo eliminar", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(i,HttpStatus.OK);
    }

    @PutMapping("/usuariosdb/")
    public ResponseEntity<?> updateUsuario(@RequestBody Usuario usuario){
        int i = service.updateUsuario(usuario);
        if (i == 0)
            return new ResponseEntity<>("No se pudo actualizar", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(i, HttpStatus.OK);
    }
}
