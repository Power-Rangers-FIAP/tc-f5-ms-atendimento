package br.com.powerprogramers.atendimento.domain.dto;

import org.springframework.validation.annotation.Validated;

@Validated
public record EspecialidadesResponseDto(String nome, String descricao) {
}
