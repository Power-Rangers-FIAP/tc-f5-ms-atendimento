package br.com.powerprogramers.atendimento.domain.dto;

import lombok.Builder;

@Builder
public record AvaliacaoRequestDto(String idAtendimento, Integer nota, String comentario) {
}
