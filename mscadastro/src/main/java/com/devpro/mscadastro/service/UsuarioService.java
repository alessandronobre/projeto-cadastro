package com.devpro.mscadastro.service;

import com.devpro.mscadastro.dto.EmailDto;
import com.devpro.mscadastro.dto.UsuarioDto;
import com.devpro.mscadastro.mapper.UsuarioMapper;
import com.devpro.mscadastro.model.Usuario;
import com.devpro.mscadastro.producer.EmailRequestProducer;
import com.devpro.mscadastro.repository.UsuarioRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;
    private UsuarioMapper usuarioMapper;
    private EmailRequestProducer emailRequestProducer;

    @Transactional
    public String cadastrarUsuario(UsuarioDto usuarioDto) {
        Usuario usuario = usuarioMapper.converteToUsuario(usuarioDto);
        usuarioRepository.save(usuario);

        EmailDto email = new EmailDto();
        email.setTitulo("BEM VINDO !!!");
        email.setConteudo("Olá " + usuarioDto.getNome() + ", Seja bem vindo ao Projeto Kafka");
        email.setDestinatario(usuarioDto.getEmail());

        try {
            emailRequestProducer.sendMessage(email);
            return "Usuario cadastrado com sucesso !";

        } catch (JsonProcessingException e) {
            log.info("Erro ao tentar enviar mensagem para a fila: " + e.getMessage());
            return "Ocorreu um erro ao enviar o email para o usuario..";
        }
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
