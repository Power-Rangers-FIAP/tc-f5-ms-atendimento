package br.com.powerprogramers.atendimento.domain;

import lombok.Builder;

@Builder
public record Avaliacao(String idAtendimento, Integer nota, String comentario) {
}
