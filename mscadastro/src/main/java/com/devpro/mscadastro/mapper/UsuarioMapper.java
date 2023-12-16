package com.devpro.mscadastro.mapper;

import com.devpro.mscadastro.dto.UsuarioDto;
import com.devpro.mscadastro.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public Usuario converteToUsuario(UsuarioDto usuarioDto) {
        Usuario usuario = new Usuario();
        usuario.setId(usuarioDto.getId());
        usuario.setNome(usuarioDto.getNome());
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setSenha(usuarioDto.getSenha());
        return usuario;
    }

    public Usuario converteToDto(Usuario usuario) {
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setId(usuario.getId());
        usuarioDto.setNome(usuario.getNome());
        usuarioDto.setEmail(usuario.getEmail());
        usuarioDto.setSenha(usuario.getSenha());
        return usuario;
    }
}
