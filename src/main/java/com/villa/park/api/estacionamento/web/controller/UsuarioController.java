package com.villa.park.api.estacionamento.web.controller;

import com.villa.park.api.estacionamento.entity.Usuario;
import com.villa.park.api.estacionamento.service.UsuarioService;
import com.villa.park.api.estacionamento.web.controller.mapper.UsuarioMapper;
import com.villa.park.api.estacionamento.web.dto.UsuarioCreateDto;
import com.villa.park.api.estacionamento.web.dto.UsuarioResponseDto;
import com.villa.park.api.estacionamento.web.dto.UsuarioSenhaDto;
import com.villa.park.api.estacionamento.web.exception.ErroMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Usuarios", description = "Está Api contém recursos para cadastrar, listar, atualizar usuários")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;


    @Operation(
            summary="Criar um novo usuário",description = "Recurso que criar um novo usuário",
            responses = {
                    @ApiResponse(responseCode = "201",description = "Usuário criado com sucesso",
                            content = @Content(mediaType="application/json",schema = @Schema(implementation=UsuarioResponseDto.class))),
                    @ApiResponse(responseCode = "409",description = "Usuário email já cadastrado do sistema",
                            content = @Content(mediaType="application/json",schema = @Schema(implementation = ErroMessage.class))),
                    @ApiResponse(responseCode = "422",description = "Dados de entrada inválidos recurso não processado",
                            content = @Content(mediaType="application/json",schema = @Schema(implementation = ErroMessage.class)))
            })
    @PostMapping
    public ResponseEntity<UsuarioResponseDto> create(@Valid @RequestBody UsuarioCreateDto createDto) {
        Usuario user = usuarioService.salvar(UsuarioMapper.toUsuario(createDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toDto(user));
    }

    @Operation(
            summary="Recuperar usuário pelo Id",description = "Recuperar usuário pelo Id",
            responses = {
                    @ApiResponse(responseCode = "200",description = "Recuperado com sucesso",
                            content = @Content(mediaType="application/json",schema = @Schema(implementation=UsuarioResponseDto.class))),
                    @ApiResponse(responseCode = "404",description = "Recurso não encontrado",
                            content = @Content(mediaType="application/json",schema = @Schema(implementation = ErroMessage.class)))
            })
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> getById(@PathVariable Long id) {
        Usuario user = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(UsuarioMapper.toDto(user));
    }

    @Operation(
            summary="Atualizar senha",description = "Atualizar senha",
            responses = {
                    @ApiResponse(responseCode = "204",description = "Senha atualizado com sucesso",
                            content = @Content(mediaType="application/json",schema = @Schema(implementation=Void.class))),
                    @ApiResponse(responseCode = "400",description = "Senha não confere ",
                            content = @Content(mediaType="application/json",schema = @Schema(implementation = ErroMessage.class))),
                    @ApiResponse(responseCode = "404",description = "Recurso não encontrado",
                            content = @Content(mediaType="application/json",schema = @Schema(implementation = ErroMessage.class))),
                    @ApiResponse(responseCode = "422",description = "Campos inválidos ou mal formatados",
                            content = @Content(mediaType="application/json",schema = @Schema(implementation = ErroMessage.class)))
            })
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @Valid @RequestBody UsuarioSenhaDto dto) {
        Usuario user = usuarioService.editarSenha(id, dto.getSenhaAtual(), dto.getNovaSenha(), dto.getConfirmaSenha());
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> getAll() {
        List<Usuario> users = usuarioService.buscarTodos();
        return ResponseEntity.ok(UsuarioMapper.toListDto(users));
    }
}
