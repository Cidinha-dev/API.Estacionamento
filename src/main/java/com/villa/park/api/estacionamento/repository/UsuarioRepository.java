package com.villa.park.api.estacionamento.repository;


import com.villa.park.api.estacionamento.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}