package br.com.powerprogramers.atendimento.domain.dto;

import lombok.Builder;

@Builder
public record FinalizarAtendimentoRequestDto(String idAtendimento, String comentario) {
}
