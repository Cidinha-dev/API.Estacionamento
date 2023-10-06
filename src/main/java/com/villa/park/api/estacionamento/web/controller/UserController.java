package com.villa.park.api.estacionamento.web.controller;

import com.villa.park.api.estacionamento.entity.User;
import com.villa.park.api.estacionamento.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/usuarios")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User usuario){
        User user = userService.salvar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id){
        User user = userService.buscarPorId(id);
        return ResponseEntity.ok(user);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<User> updatePassword(@PathVariable Long id, @RequestBody User usuario){
        User user = userService.editarSenha(id ,usuario.getPassword());
        return ResponseEntity.ok(user);
    }
    @GetMapping
    public ResponseEntity<List<User>> getAll(){
        List<User> users = userService.listarTodos();
        return ResponseEntity.ok(users);
    }




}
