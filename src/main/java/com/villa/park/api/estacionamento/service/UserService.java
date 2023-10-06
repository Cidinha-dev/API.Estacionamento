package com.villa.park.api.estacionamento.service;

import com.villa.park.api.estacionamento.entity.User;
import com.villa.park.api.estacionamento.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    public final UserRepository userRepository;

    @Transactional
    public User salvar(User user) {
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User buscarPorId(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Usuario não encontrado")
        );
    }

    @Transactional
    public User editarSenha(Long id, String password) {
        User usuario = buscarPorId(id);
        usuario.setPassword(password);
        return usuario;
    }

    @Transactional(readOnly = true)
    public List<User> listarTodos() {
        return userRepository.findAll();
    }
}
