package br.com.powerprogramers.atendimento.domain.dto;

import org.springframework.validation.annotation.Validated;

@Validated
public record AvaliacaoResponseDto(String idUnidade, String idMedico, Integer nota, String comentario) {
}
