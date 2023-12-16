package com.devpro.mscadastro.service;

import com.devpro.mscadastro.dto.UsuarioDto;
import com.devpro.mscadastro.mapper.UsuarioMapper;
import com.devpro.mscadastro.model.Usuario;
import com.devpro.mscadastro.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;
    private UsuarioMapper usuarioMapper;

    public String cadastrarUsuario(UsuarioDto usuarioDto) {
        Usuario usuario = usuarioMapper.converteToUsuario(usuarioDto);
        usuarioRepository.save(usuario);
        return "Usuario cadastrado com sucesso !";
    }

    public String deletarUsuario(Long id) {
        if (buscarUsuarioPorId(id).isPresent()) {
            usuarioRepository.deleteById(id);
            return "Usuario deletado com sucesso !";
        }
        return "Usuario não encontrado";
    }

    private Optional<Usuario> buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }
}
