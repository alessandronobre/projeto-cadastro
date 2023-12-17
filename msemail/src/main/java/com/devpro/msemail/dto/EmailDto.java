package com.devpro.msemail.dto;

import lombok.Data;

@Data
public class EmailDto {

    private String titulo;
    private String conteudo;
    private String destinatario;
}
