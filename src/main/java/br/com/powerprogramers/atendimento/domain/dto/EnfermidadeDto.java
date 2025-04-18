package br.com.powerprogramers.atendimento.domain.dto;

import org.springframework.validation.annotation.Validated;

@Validated
public record EnfermidadeDto(String id, String comentario) {
}
