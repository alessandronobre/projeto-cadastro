package com.devpro.mscadastro.producer;

import com.devpro.mscadastro.dto.EmailDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmailRequestProducer {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value(value = "${topics.email.request.topic}")
    private String topicEmail;

    public void sendMessage(EmailDto emailDto) throws JsonProcessingException {
        String email = objectMapper.writeValueAsString(emailDto);
        kafkaTemplate.send(topicEmail, email);
    }
}
