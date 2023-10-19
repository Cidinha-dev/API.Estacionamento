package com.villa.park.api.estacionamento.web.dto;

import lombok.*;



@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class UsuarioResponseDto {

    private Long id;
    private String username;
    private String role;
}