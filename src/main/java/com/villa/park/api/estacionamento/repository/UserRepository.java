package com.villa.park.api.estacionamento.repository;

import com.villa.park.api.estacionamento.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}