package com.devpro.mscadastro.controller;

import com.devpro.mscadastro.dto.UsuarioDto;
import com.devpro.mscadastro.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private UsuarioService usuarioService;

    @PostMapping("/cadastro")
    public String cadastrarUsuario(@RequestBody UsuarioDto usuarioDto) {
        return usuarioService.cadastrarUsuario(usuarioDto);
    }

    @DeleteMapping("/delete/{id}")
    public String deletarUsuario(@PathVariable Long id) {
        return usuarioService.deletarUsuario(id);
    }
}
