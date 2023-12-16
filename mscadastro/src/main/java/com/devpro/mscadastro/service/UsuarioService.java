package com.devpro.mscadastro.service;

import com.devpro.mscadastro.dto.UsuarioDto;
import com.devpro.mscadastro.mapper.UsuarioMapper;
import com.devpro.mscadastro.model.Usuario;
import com.devpro.mscadastro.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;
    private UsuarioMapper usuarioMapper;

    public void cadastrarUsuario(UsuarioDto usuarioDto) {
        Usuario usuario = usuarioMapper.converteToUsuario(usuarioDto);
        usuarioRepository.save(usuario);
    }

    public void deletarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
