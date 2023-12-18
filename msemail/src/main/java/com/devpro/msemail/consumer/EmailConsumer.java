package com.devpro.msemail.consumer;

import com.devpro.msemail.dto.EmailDto;
import com.devpro.msemail.service.EmailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class EmailConsumer {

    private ObjectMapper objectMapper;
    private EmailService emailService;

    @KafkaListener(topics = "${topics.email.request.topic}", groupId = "emails")
    public void enviaEmailBoasVindasToUsuario(String mensagem) throws JsonProcessingException {
        EmailDto email = objectMapper.readValue(mensagem, EmailDto.class);

        try {
            emailService.sendEmail(email);
        } catch (MessagingException e) {
            log.info("Erro ao enviar email para: " + email.getDestinatario() + " ...");
            throw new RuntimeException(e);
        }

    }
}
