package com.villa.park.api.estacionamento.web.dto;


import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class UsuarioCreateDto {

    private String username;
    private String password;
}
