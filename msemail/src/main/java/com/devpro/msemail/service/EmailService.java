package com.devpro.msemail.service;

import com.devpro.msemail.dto.EmailDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendEmail(EmailDto email) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setSubject(email.getTitulo());
        mimeMessageHelper.setText(email.getConteudo(), true);
        mimeMessageHelper.setTo(email.getDestinatario());

        log.info("Enviando email para: " + email.getDestinatario() + " ...");
        mailSender.send(mimeMessage);
    }
}
